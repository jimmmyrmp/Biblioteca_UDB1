package biblioteca.udb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionUsuarios {
    
    public boolean agregarUsuario(String nombreUsuario, String contraseña, String email, String privilegio) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return false;
        }
        
        try {
            String sql = "INSERT INTO usuarios (nombre_usuario, contraseña, email, privilegio) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            stmt.setString(3, email);
            stmt.setString(4, privilegio);
            
            int resultado = stmt.executeUpdate();
            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            
            return resultado > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }
    
    public boolean cambiarContraseña(String nombreUsuario, String contraseñaActual, String contraseñaNueva) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return false;
        }
        
        try {
            String sqlVerificar = "SELECT contraseña FROM usuarios WHERE nombre_usuario = ?";
            PreparedStatement stmtVerificar = conexion.prepareStatement(sqlVerificar);
            stmtVerificar.setString(1, nombreUsuario);
            
            ResultSet rs = stmtVerificar.executeQuery();
            
            if (rs.next()) {
                String contraseñaGuardada = rs.getString("contraseña");
                
                if (contraseñaGuardada.equals(contraseñaActual)) {
                    String sqlActualizar = "UPDATE usuarios SET contraseña = ? WHERE nombre_usuario = ?";
                    PreparedStatement stmtActualizar = conexion.prepareStatement(sqlActualizar);
                    
                    stmtActualizar.setString(1, contraseñaNueva);
                    stmtActualizar.setString(2, nombreUsuario);
                    
                    int resultado = stmtActualizar.executeUpdate();
                    stmtActualizar.close();
                    stmtVerificar.close();
                    ConexionBD.cerrarConexion(conexion);
                    
                    return resultado > 0;
                } else {
                    System.out.println("Contraseña actual incorrecta");
                    stmtVerificar.close();
                    ConexionBD.cerrarConexion(conexion);
                    return false;
                }
            } else {
                System.out.println("Usuario no encontrado");
                stmtVerificar.close();
                ConexionBD.cerrarConexion(conexion);
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cambiar contraseña: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }
    
    public boolean actualizarContraseña(String nombreUsuario, String contraseñaNueva) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return false;
        }
        
        try {
            String sql = "UPDATE usuarios SET contraseña = ? WHERE nombre_usuario = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            
            stmt.setString(1, contraseñaNueva);
            stmt.setString(2, nombreUsuario);
            
            int resultado = stmt.executeUpdate();
            stmt.close();
            ConexionBD.cerrarConexion(conexion);
            
            return resultado > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar contraseña: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return false;
        }
    }
    
    public Usuario obtenerUsuario(String nombreUsuario) {
        Connection conexion = ConexionBD.conectar();
        
        if (conexion == null) {
            return null;
        }
        
        try {
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contraseña"),
                    rs.getString("email"),
                    rs.getString("privilegio")
                );
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return usuario;
            } else {
                stmt.close();
                ConexionBD.cerrarConexion(conexion);
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
            ConexionBD.cerrarConexion(conexion);
            return null;
        }
    }
}