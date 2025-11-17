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
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexion = ConexionBD.conectar();
            
                    if (conexion == null) {
            return true; // Si no hay conexión, no permitir préstamo
        }
                    
            String sql = "SELECT COUNT(*) as total FROM prestamos " +
                        "WHERE id_usuario = ? AND estado = 'MORA'";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                int total = rs.getInt("total"); 
                return total > 0; // Retorna true si tiene mora
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al verificar mora: " + e.getMessage());
            return true; // Por seguridad, no permitir préstamo si hay error
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para verificar si un ejemplar está disponible
    public boolean ejemplarDisponible(String codigoEjemplar) {
        
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexion = ConexionBD.conectar();
            
            if (conexion == null) {
            return false;
        }
            
            String sql = "SELECT disponible FROM ejemplares WHERE codigo = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, codigoEjemplar);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                int disponible = rs.getInt("disponible");
                return disponible == 1; // Retorna true si está disponible
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
    }
    }
    
    // Método para realizar un préstamo
    public boolean realizarPrestamo(int idUsuario, String codigoEjemplar, int diasPrestamo) {
        
        Connection conexion = null;
        PreparedStatement stmtPrestamo = null;
        PreparedStatement stmtEjemplar = null;
        
        try {
            
        conexion = ConexionBD.conectar();
        
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
            
            // Calcular fechas
            Date fechaPrestamo = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaPrestamo);
            calendar.add(Calendar.DAY_OF_MONTH, diasPrestamo);
            Date fechaDevolucion = calendar.getTime();
            
            // Insertar el préstamo
            String sqlPrestamo = "INSERT INTO prestamos (id_usuario, codigo_ejemplar, fecha_prestamo, fecha_devolucion, estado) " +
                                "VALUES (?, ?, ?, ?, 'ACTIVO')";
            stmtPrestamo = conexion.prepareStatement(sqlPrestamo);
            
            stmtPrestamo.setInt(1, idUsuario);
            stmtPrestamo.setString(2, codigoEjemplar);
            stmtPrestamo.setDate(3, new java.sql.Date(fechaPrestamo.getTime()));
            stmtPrestamo.setDate(4, new java.sql.Date(fechaDevolucion.getTime()));
            
            int resultado = stmtPrestamo.executeUpdate();
            
            if (resultado > 0) {
                // Actualizar disponibilidad del ejemplar
                String sqlEjemplar = "UPDATE ejemplares SET disponible = 0 WHERE codigo = ?";
                stmtEjemplar = conexion.prepareStatement(sqlEjemplar);
                stmtEjemplar.setString(1, codigoEjemplar);
                stmtEjemplar.executeUpdate();
                
                System.out.println("Préstamo realizado exitosamente");
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al realizar préstamo: " + e.getMessage());
            return false;
        } finally {
            try{
                if (stmtPrestamo != null) stmtPrestamo.close();
                if (stmtEjemplar != null) stmtEjemplar.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para obtener información de un préstamo activo por usuario
    public Prestamo obtenerPrestamoActivo(int idUsuario, String codigoEjemplar) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
        conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return null;
        }
            
            String sql = "SELECT * FROM prestamos WHERE id_usuario = ? AND codigo_ejemplar = ? AND estado = 'ACTIVO'";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setString(2, codigoEjemplar);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Prestamo prestamo = new Prestamo(
                    rs.getInt("id_prestamo"),
                    rs.getInt("id_usuario"),
                    rs.getString("codigo_ejemplar"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_devolucion"),
                    rs.getString("estado")
                );
 
                return prestamo;
            }
            
            return null;
            
        } catch (SQLException e) {
            System.out.println("Error al obtener préstamo: " + e.getMessage());
            return null;
        } finally {
            try{
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}