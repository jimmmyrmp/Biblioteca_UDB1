package biblioteca.udb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import biblioteca.udb.ConexionBD;
import biblioteca.udb.ejemplares.textos.Libros;
import biblioteca.udb.ejemplares.catalogo;

@WebServlet(name = "libroServlet", urlPatterns = {"/formulario-libro"})
public class libroServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/formularios/formulario-libro.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje = "";
        String tipoMensaje = "";
        
        try {
            String codigo = request.getParameter("codigo");
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            int año = Integer.parseInt(request.getParameter("año"));
            String ubicacion = request.getParameter("ubicacion");
            String tipo = "Libro"; // Tipo fijo para libros
            String isbn = request.getParameter("isbn");
            String editorial = request.getParameter("editorial");
            int numPaginas = 0;
            if (request.getParameter("numPaginas") != null && !request.getParameter("numPaginas").isEmpty()) {
                numPaginas = Integer.parseInt(request.getParameter("numPaginas"));
            }
            
            String genero = request.getParameter("genero");
            if ("otro".equals(genero)) {
                genero = request.getParameter("otroGenero");
                guardarNuevoGenero(genero);
            }
            
            // Crear libro con constructor de 9 parámetros (sin el tipo "Libro" en medio)
            // Si el constructor es: (codigo, titulo, autor, año, ubicacion, isbn, editorial, numPaginas, genero)
            Libros libro = new Libros(codigo, titulo, autor, año, ubicacion, 
                                      isbn, editorial, numPaginas, genero);
            
            // El tipo "Libro" debe estar en la superclase o ser manejado de otra forma
            catalogo catalogo = new catalogo();
            boolean resultado = catalogo.agregarEjemplar(libro);
            
            if (resultado) {
                mensaje = "¡Libro registrado exitosamente! Código: " + codigo;
                tipoMensaje = "exito";
                // Limpiar formulario
                request.setAttribute("codigo", "");
                request.setAttribute("titulo", "");
                request.setAttribute("autor", "");
                request.setAttribute("editorial", "");
                request.setAttribute("ubicacion", "");
            } else {
                mensaje = "Error: No se pudo registrar el libro. Verifique los datos.";
                tipoMensaje = "error";
            }
            
        } catch (NumberFormatException e) {
            mensaje = "Error: El año y número de páginas deben ser valores numéricos válidos.";
            tipoMensaje = "error";
        } catch (Exception e) {
            mensaje = "Error inesperado: " + e.getMessage();
            tipoMensaje = "error";
            e.printStackTrace();
        }
        
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);
        request.getRequestDispatcher("/WEB-INF/jsp/formulario-libro.jsp").forward(request, response);
    }
    
    private void guardarNuevoGenero(String genero) {
        Connection conexion = null;
        try {
            conexion = ConexionBD.conectar();
            String sql = "INSERT INTO generos_libros (genero) VALUES (?) ON DUPLICATE KEY UPDATE genero = genero";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, genero);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try { conexion.close(); } catch (Exception e) {}
            }
        }
    }
}