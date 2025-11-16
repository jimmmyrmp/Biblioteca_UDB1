package biblioteca.udb;

import java.sql.*;

public class ConfiguracionDAO {
    
    private Connection conexion;
    
    public ConfiguracionDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public Configuracion obtenerConfiguracion(String parametro) {
        Configuracion config = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT parametro, valor, descripcion FROM configuracion WHERE parametro = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, parametro);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                config = new Configuracion();
                config.setParametro(rs.getString("parametro"));
                config.setValor(rs.getString("valor"));
                config.setDescripcion(rs.getString("descripcion"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        return config;
    }
    
    public boolean actualizarConfiguracion(String parametro, String nuevoValor) {
        PreparedStatement pstmt = null;
        
        try {
            String sql = "UPDATE configuracion SET valor = ? WHERE parametro = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, nuevoValor);
            pstmt.setString(2, parametro);
            
            int filasAfectadas = pstmt.executeUpdate();
            
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    public int obtenerMaxEjemplares() {
        Configuracion config = obtenerConfiguracion("MAX_EJEMPLARES_PRESTAMO");
        if (config != null) {
            return config.getValorEntero();
        }
        return 3; // Valor por defecto
    }
}
