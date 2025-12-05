package biblioteca.udb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "seleccionEjemplarServlet", urlPatterns = {"/agregar-ejemplares"})
public class seleccionEjemplarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/agregar-ejemplares.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si en algún momento quieres manejar una selección por POST
        String tipoEjemplar = request.getParameter("tipoEjemplar");
        
        switch (tipoEjemplar) {
            case "Libro":
                response.sendRedirect(request.getContextPath() + "/formulario-libro");
                break;
            case "Revista":
                response.sendRedirect(request.getContextPath() + "/formulario-revista");
                break;
            case "Tesis":
                response.sendRedirect(request.getContextPath() + "/formulario-tesis");
                break;
            case "CD":
                response.sendRedirect(request.getContextPath() + "/formulario-cd");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/seleccion-ejemplar?error=tipo_invalido");
                break;
        }
    }
}