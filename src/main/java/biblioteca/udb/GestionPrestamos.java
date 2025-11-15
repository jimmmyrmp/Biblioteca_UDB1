/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.udb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class GestionPrestamos {
    
    // Método para verificar si un usuario tiene mora
    public boolean usuarioTieneMora(int idUsuario) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return true; // Si no hay conexión, no permitir préstamo
        }
        
        try {
            String sql = "SELECT COUNT(*) as total FROM prestamos " +
                        "WHERE id_usuario = ? AND estado = 'MORA'";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int total = rs.getInt("total");
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return total > 0; // Retorna true si tiene mora
            }
            
            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al verificar mora: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return true; // Por seguridad, no permitir préstamo si hay error
        }
    }
    
    // Método para verificar si un ejemplar está disponible
    public boolean ejemplarDisponible(String codigoEjemplar) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return false;
        }
        
        try {
            String sql = "SELECT disponible FROM ejemplares WHERE codigo = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, codigoEjemplar);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int disponible = rs.getInt("disponible");
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return disponible == 1; // Retorna true si está disponible
            }
            
            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }
    
    // Método para realizar un préstamo
    public boolean realizarPrestamo(int idUsuario, String codigoEjemplar, int diasPrestamo) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return false;
        }
        
        // Validar que el usuario no tenga mora
        if (usuarioTieneMora(idUsuario)) {
            System.out.println("El usuario tiene mora pendiente. No se puede realizar el préstamo.");
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
        
        // Validar que el ejemplar esté disponible
        if (!ejemplarDisponible(codigoEjemplar)) {
            System.out.println("El ejemplar no está disponible.");
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
        
        try {
            // Calcular fechas
            Date fechaPrestamo = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaPrestamo);
            calendar.add(Calendar.DAY_OF_MONTH, diasPrestamo);
            Date fechaDevolucion = calendar.getTime();
            
            // Insertar el préstamo
            String sqlPrestamo = "INSERT INTO prestamos (id_usuario, codigo_ejemplar, fecha_prestamo, fecha_devolucion, estado) " +
                                "VALUES (?, ?, ?, ?, 'ACTIVO')";
            PreparedStatement stmtPrestamo = conexion.prepareStatement(sqlPrestamo);
            
            stmtPrestamo.setInt(1, idUsuario);
            stmtPrestamo.setString(2, codigoEjemplar);
            stmtPrestamo.setDate(3, new java.sql.Date(fechaPrestamo.getTime()));
            stmtPrestamo.setDate(4, new java.sql.Date(fechaDevolucion.getTime()));
            
            int resultado = stmtPrestamo.executeUpdate();
            
            if (resultado > 0) {
                // Actualizar disponibilidad del ejemplar
                String sqlEjemplar = "UPDATE ejemplares SET disponible = 0 WHERE codigo = ?";
                PreparedStatement stmtEjemplar = conexion.prepareStatement(sqlEjemplar);
                stmtEjemplar.setString(1, codigoEjemplar);
                stmtEjemplar.executeUpdate();
                stmtEjemplar.close();
                
                System.out.println("Préstamo realizado exitosamente");
                stmtPrestamo.close();
                ConexionBD.cerrarConexion(conexion);
                return true;
            }
            
            stmtPrestamo.close();
            ConexionBD.cerrarConexion(conexion);
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al realizar préstamo: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }
    
    // Método para obtener información de un préstamo activo por usuario
    public Prestamo obtenerPrestamoActivo(int idUsuario, String codigoEjemplar) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return null;
        }
        
        try {
            String sql = "SELECT * FROM prestamos WHERE id_usuario = ? AND codigo_ejemplar = ? AND estado = 'ACTIVO'";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setString(2, codigoEjemplar);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Prestamo prestamo = new Prestamo(
                    rs.getInt("id_prestamo"),
                    rs.getInt("id_usuario"),
                    rs.getString("codigo_ejemplar"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_devolucion"),
                    rs.getString("estado")
                );
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return prestamo;
            }
            
            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            return null;
            
        } catch (SQLException e) {
            System.out.println("Error al obtener préstamo: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return null;
        }
    }
}