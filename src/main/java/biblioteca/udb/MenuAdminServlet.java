package biblioteca.udb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import biblioteca.udb.ConexionBD;

@WebServlet(name = "MenuAdminServlet", urlPatterns = {"/menu-admin"})
public class MenuAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        loadStatistics(request);
        request.getRequestDispatcher("/WEB-INF/jsp/menu-admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void loadStatistics(HttpServletRequest request) {
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.conectar();
            
            int totalEjemplares = contarEjemplares(conexion);
            request.setAttribute("totalEjemplares", totalEjemplares);
            
            int totalUsuarios = contarUsuarios(conexion);
            request.setAttribute("totalUsuarios", totalUsuarios);
            
            int prestamosActivos = contarPrestamosActivos(conexion);
            request.setAttribute("prestamosActivos", prestamosActivos);
            
            int prestamosVencidos = contarPrestamosVencidos(conexion);
            request.setAttribute("prestamosVencidos", prestamosVencidos);
            
            int maxEjemplaresPrestamo = obtenerMaxEjemplaresPrestamo(conexion);
            request.setAttribute("maxEjemplaresPrestamo", maxEjemplaresPrestamo);
            
            request.setAttribute("versionSistema", "Biblioteca UDB v2.1");
            request.setAttribute("ultimaActualizacion", "15 Nov 2024");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            setDefaultStatistics(request);
        } finally {
            if (conexion != null) {
                ConexionBD.cerrarConexion(conexion);
            }
        }
    }

    private int contarEjemplares(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM ejemplares";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    private int contarUsuarios(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM usuarios";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    private int contarPrestamosActivos(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM prestamos WHERE fecha_devolucion IS NULL AND fecha_limite > CURDATE()";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    private int contarPrestamosVencidos(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM prestamos WHERE fecha_devolucion IS NULL AND fecha_limite < CURDATE()";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt("total") : 0;
        }
    }

    private int obtenerMaxEjemplaresPrestamo(Connection conexion) throws SQLException {
        String sql = "SELECT valor_entero FROM configuracion_ejemplares WHERE clave = 'MAX_EJEMPLARES_PRESTAMO'";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getInt("valor_entero") : 3;
        }
    }

    private void setDefaultStatistics(HttpServletRequest request) {
        request.setAttribute("totalEjemplares", 0);
        request.setAttribute("totalUsuarios", 0);
        request.setAttribute("prestamosActivos", 0);
        request.setAttribute("prestamosVencidos", 0);
        request.setAttribute("maxEjemplaresPrestamo", 3);
        request.setAttribute("versionSistema", "Biblioteca UDB v2.1");
        request.setAttribute("ultimaActualizacion", "15 Nov 2024");
    }
}