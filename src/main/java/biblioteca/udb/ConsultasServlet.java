/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.udb;

import biblioteca.udb.ejemplares.ejemplares;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultasServlet", urlPatterns = {"/consultas"})
public class ConsultasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            accion = "inicio";
        }
        
        switch(accion) {
            case "buscar":
                buscarEjemplares(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/jsp/consultas.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Busca ejemplares según los criterios proporcionados
     */
    private void buscarEjemplares(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String tipo = request.getParameter("tipo");
        
        List<ejemplares> listaEjemplares = new ArrayList<>();
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.conectar();
            
            // Construir la consulta SQL dinámicamente según los criterios
            StringBuilder sql = new StringBuilder(
                "SELECT codigo, titulo, autor, año, ubicacion, disponible, tipo FROM ejemplares WHERE 1=1"
            );
            
            if (titulo != null && !titulo.isEmpty()) {
                sql.append(" AND titulo LIKE ?");
            }
            if (autor != null && !autor.isEmpty()) {
                sql.append(" AND autor LIKE ?");
            }
            if (tipo != null && !tipo.isEmpty()) {
                sql.append(" AND tipo = ?");
            }
            
            sql.append(" ORDER BY titulo");
            
            PreparedStatement pstmt = conexion.prepareStatement(sql.toString());
            
            // Establecer parámetros
            int indice = 1;
            if (titulo != null && !titulo.isEmpty()) {
                pstmt.setString(indice++, "%" + titulo + "%");
            }
            if (autor != null && !autor.isEmpty()) {
                pstmt.setString(indice++, "%" + autor + "%");
            }
            if (tipo != null && !tipo.isEmpty()) {
                pstmt.setString(indice++, tipo);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ejemplares ej = new ejemplares();
                ej.setCodigo(rs.getString("codigo"));
                ej.setTitulo(rs.getString("titulo"));
                ej.setAutor(rs.getString("autor"));
                ej.setAño(rs.getInt("año"));
                ej.setUbicacion(rs.getString("ubicacion"));
                ej.setDisponible(rs.getBoolean("disponible"));
                ej.setTipo(rs.getString("tipo"));
                
                listaEjemplares.add(ej);
            }
            
            System.out.println("✓ Búsqueda completada: " + listaEjemplares.size() + " resultados");
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
        
        request.setAttribute("ejemplares", listaEjemplares);
        request.getRequestDispatcher("/WEB-INF/jsp/consultas.jsp").forward(request, response);
    }
}