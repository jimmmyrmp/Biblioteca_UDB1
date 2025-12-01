package biblioteca.udb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet encargado de gestionar los préstamos en la versión web.
 * Utiliza la lógica de la clase GestionPrestamos (Fase de escritorio).
 */
@WebServlet(name = "PrestamosServlet", urlPatterns = {"/prestamos"})
public class PrestamosServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Solo muestra el formulario
        request.getRequestDispatcher("/WEB-INF/jsp/prestamos.jsp").forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idUsuarioTexto = request.getParameter("idUsuario");
        String codigoEjemplar = request.getParameter("codigoEjemplar");
        String diasTexto = request.getParameter("diasPrestamo");

        String mensaje = "";
        String tipoMensaje = ""; // "ok" | "error"

        // Para que el formulario se rellene de nuevo con lo que escribió el usuario
        request.setAttribute("idUsuarioTexto", idUsuarioTexto);
        request.setAttribute("codigoEjemplarTexto", codigoEjemplar);
        request.setAttribute("diasSeleccionados", diasTexto);

        if (idUsuarioTexto == null || idUsuarioTexto.isBlank()
                || codigoEjemplar == null || codigoEjemplar.isBlank()
                || diasTexto == null || diasTexto.isBlank()) {

            mensaje = "Todos los campos son obligatorios.";
            tipoMensaje = "error";

        } else {
            try {
                int idUsuario = Integer.parseInt(idUsuarioTexto.trim());
                int diasPrestamo = Integer.parseInt(diasTexto.trim());

                GestionPrestamos gestion = new GestionPrestamos();

                // 1. Verificar mora
                if (gestion.usuarioTieneMora(idUsuario)) {
                    mensaje = "El usuario tiene mora registrada. No se puede realizar el préstamo.";
                    tipoMensaje = "error";

                // 2. Verificar disponibilidad
                } else if (!gestion.ejemplarDisponible(codigoEjemplar)) {
                    mensaje = "El ejemplar no está disponible. Puede estar prestado o no existir.";
                    tipoMensaje = "error";

                // 3. Intentar realizar el préstamo
                } else {
                    boolean resultado = gestion.realizarPrestamo(idUsuario, codigoEjemplar, diasPrestamo);

                    if (resultado) {
                        mensaje = "Préstamo realizado correctamente para el usuario "
                                + idUsuario + ". La fecha límite de devolución se calculó automáticamente.";
                        tipoMensaje = "ok";

                        // Limpiar campos si todo salió bien
                        request.setAttribute("idUsuarioTexto", "");
                        request.setAttribute("codigoEjemplarTexto", "");
                        request.setAttribute("diasSeleccionados", "7"); // valor por defecto
                    } else {
                        mensaje = "No se pudo registrar el préstamo. Verifique los datos e intente nuevamente.";
                        tipoMensaje = "error";
                    }
                }

            } catch (NumberFormatException ex) {
                mensaje = "El ID de usuario y los días de préstamo deben ser números válidos.";
                tipoMensaje = "error";
            } catch (Exception ex) {
                mensaje = "Ocurrió un error inesperado: " + ex.getMessage();
                tipoMensaje = "error";
                ex.printStackTrace();
            }
        }

        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);

        // Siempre regresamos al mismo JSP
        request.getRequestDispatcher("/WEB-INF/jsp/prestamos.jsp").forward(request, response);
    }
}
