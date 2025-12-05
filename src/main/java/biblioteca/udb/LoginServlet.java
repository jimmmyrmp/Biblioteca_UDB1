package biblioteca.udb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si ya está logueado, redirigir al inicio
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("idUsuario") != null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        
        // Mostrar formulario de login
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        
        String mensaje = "";
        String tipoMensaje = "";
        
        // Mantener el usuario en el formulario en caso de error
        request.setAttribute("usuarioTexto", usuario);
        
        if (usuario == null || usuario.isBlank() 
                || contrasena == null || contrasena.isBlank()) {
            
            mensaje = "Usuario y contraseña son obligatorios.";
            tipoMensaje = "error";
            
        } else {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
                // Obtener conexión a la base de datos
                conn = ConexionBD.conectar();
                
                // Consulta para verificar credenciales
                String sql = "SELECT id_usuario, usuario, nombre, rol FROM usuarios "
                           + "WHERE usuario = ? AND contrasena = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, usuario.trim());
                pstmt.setString(2, contrasena.trim()); // En producción usar hash
                
                rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    // Login exitoso
                    int idUsuario = rs.getInt("id_usuario");
                    String nombreUsuario = rs.getString("nombre");
                    String rol = rs.getString("rol");
                    
            
                    HttpSession session = request.getSession();
                    session.setAttribute("idUsuario", idUsuario);
                    session.setAttribute("usuario", usuario);
                    session.setAttribute("nombre", nombreUsuario);
                    session.setAttribute("rol", rol);
                    
                   
                    session.setMaxInactiveInterval(30 * 60);
                    
                    mensaje = "Bienvenido, " + nombreUsuario + "!";
                    tipoMensaje = "ok";
                    
                    // Redirigir según el rol (si es necesario)
                    String destino = determinarDestinoSegunRol(rol);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("tipoMensaje", tipoMensaje);
                    
                    // Usar forward o redirect según sea necesario
                    response.sendRedirect(request.getContextPath() + destino);
                    return;
                    
                } else {
                    // Credenciales incorrectas
                    mensaje = "Usuario o contraseña incorrectos.";
                    tipoMensaje = "error";
                }
                
            } catch (Exception ex) {
                mensaje = "Error en el sistema: " + ex.getMessage();
                tipoMensaje = "error";
                ex.printStackTrace();
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        // Si llegamos aquí, hubo error o no se redirigió
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);
        
        // Volver al formulario de login con mensaje
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
    
    private String determinarDestinoSegunRol(String rol) {
        // Personaliza según los roles de tu sistema
        switch (rol.toLowerCase()) {
            case "admin":
                return "/admin/dashboard.jsp";
            case "bibliotecario":
                return "/bibliotecario/inicio.jsp";
            default:
                return "/index.jsp"; // Usuario regular
        }
    }
}