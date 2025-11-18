/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.udb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author gris_
 */
public class GestionDevoluciones {
    

    // Método para verificar si un ejemplar está prestado a un usuario y activo
    public boolean ejemplarPrestadoAUsuario(int idUsuario, String codigoEjemplar) {
        Connection conexion = ConexionBD.conectar();

        if (conexion == null) {
            return false;
        }

        try {
            String sql = "SELECT COUNT(*) as total FROM prestamos WHERE id_usuario = ? AND codigo_ejemplar = ? AND estado = 'ACTIVO'";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setString(2, codigoEjemplar);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return total > 0;
            }

            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            return false;

        } catch (SQLException e) {
            System.out.println("Error al verificar préstamo activo: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }

    // Método para obtener la fecha de devolución y la fecha de préstamo del préstamo activo
    private Prestamo obtenerPrestamoActivo(int idUsuario, String codigoEjemplar) {
        Connection conexion = ConexionBD.conectar();

        if (conexion == null) {
            return null;
        }

        try {
            String sql = "SELECT id_prestamo, fecha_prestamo, fecha_devolucion FROM prestamos WHERE id_usuario = ? AND codigo_ejemplar = ? AND estado = 'ACTIVO'";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setString(2, codigoEjemplar);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                Date fechaPrestamoSql = rs.getDate("fecha_prestamo");
                Date fechaDevolucionSql = rs.getDate("fecha_devolucion");

                Prestamo prestamo = new Prestamo();
                prestamo.idPrestamo = idPrestamo;
                prestamo.fechaPrestamo = fechaPrestamoSql.toLocalDate();
                prestamo.fechaDevolucion = fechaDevolucionSql.toLocalDate();

                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return prestamo;
            }

            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            return null;

        } catch (SQLException e) {
            System.out.println("Error al obtener préstamo activo: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return null;
        }
    }

    // Método para calcular días de mora (retorna 0 si no hay mora)
    public int calcularDiasMora(int idUsuario, String codigoEjemplar) {
        Prestamo prestamo = obtenerPrestamoActivo(idUsuario, codigoEjemplar);

        if (prestamo == null) {
            return 0;
        }

        LocalDate fechaLimite = prestamo.fechaDevolucion;
        LocalDate fechaActual = LocalDate.now();

        if (fechaActual.isAfter(fechaLimite)) {
            return (int) ChronoUnit.DAYS.between(fechaLimite, fechaActual);
        } else {
            return 0;
        }
    }

    // Retorna la tarifa diaria de mora (puede ser dinámica o fija)
    public double obtenerTarifaMoraDiaria() {
        // Ejemplo con tarifa fija, puedes cargarla de BD o archivo de configuración
        return 2.0;
    }

    // Método para registrar la devolución (actualiza estado y ejemplar disponible)
    public boolean realizarDevolucion(int idUsuario, String codigoEjemplar) {
        Connection conexion = ConexionBD.conectar();

        if (conexion == null) {
            return false;
        }

        Prestamo prestamo = obtenerPrestamoActivo(idUsuario, codigoEjemplar);
        if (prestamo == null) {
            System.out.println("No existe préstamo activo para este usuario y ejemplar.");
            ConexionBD.cerrarConexion(conexion);
            return false;
        }

        try {
            // Cambiar estado del préstamo a 'DEVUELTO'
            String sqlActualizarPrestamo = "UPDATE prestamos SET estado = 'DEVUELTO' WHERE id_prestamo = ?";
            PreparedStatement stmtPrestamo = conexion.prepareStatement(sqlActualizarPrestamo);
            stmtPrestamo.setInt(1, prestamo.idPrestamo);
            int filasActualizadas = stmtPrestamo.executeUpdate();
            stmtPrestamo.close();

            if (filasActualizadas == 0) {
                ConexionBD.cerrarConexion(conexion);
                return false;
            }

            // Actualizar ejemplar a disponible = 1
            String sqlEjemplar = "UPDATE ejemplares SET disponible = 1 WHERE codigo = ?";
            PreparedStatement stmtEjemplar = conexion.prepareStatement(sqlEjemplar);
            stmtEjemplar.setString(1, codigoEjemplar);
            int filasEjemplar = stmtEjemplar.executeUpdate();
            stmtEjemplar.close();

            ConexionBD.cerrarConexion(conexion);

            return filasEjemplar > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al registrar devolución: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }

    // Clase interna para manejar datos del préstamo
    private static class Prestamo {
        int idPrestamo;
        LocalDate fechaPrestamo;
        LocalDate fechaDevolucion;
    }
}


