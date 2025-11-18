package biblioteca.udb;

import biblioteca.udb.ejemplares.ejemplares;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ejemplar1 {
    
    private Connection conexion;
    
    public Ejemplar1(Connection conexion) {
        this.conexion = conexion;
    }
    
    public List<ejemplares> buscarEjemplares(String criterio) {
        List<ejemplares> listaEjemplares = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT codigo, titulo, autor, año, ubicacion, disponible, tipo " +
                        "FROM ejemplares " +
                        "WHERE titulo LIKE ? OR autor LIKE ? OR codigo LIKE ? " +
                        "ORDER BY titulo";
            
            pstmt = conexion.prepareStatement(sql);
            String busqueda = "%" + criterio + "%";
            pstmt.setString(1, busqueda);
            pstmt.setString(2, busqueda);
            pstmt.setString(3, busqueda);
            
            rs = pstmt.executeQuery();
            
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
            
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar ejemplares: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return listaEjemplares;
    }
    
    public List<ejemplares> obtenerTodos() {
        List<ejemplares> listaEjemplares = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT codigo, titulo, autor, año, ubicacion, disponible, tipo " +
                        "FROM ejemplares ORDER BY titulo";
            
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sql);
            
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
            
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar: " + e.getMessage());
            }
        }
        
        return listaEjemplares;
    }
    
    public int[] obtenerEstadisticas() {
        int[] stats = new int[3]; // [total, disponibles, prestados]
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT COUNT(*) as total, " +
                        "SUM(CASE WHEN disponible = 1 THEN 1 ELSE 0 END) as disponibles, " +
                        "SUM(CASE WHEN disponible = 0 THEN 1 ELSE 0 END) as prestados " +
                        "FROM ejemplares";
            
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                stats[0] = rs.getInt("total");
                stats[1] = rs.getInt("disponibles");
                stats[2] = rs.getInt("prestados");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        return stats;
    }
}