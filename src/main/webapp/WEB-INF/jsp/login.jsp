<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="biblioteca.udb.ejemplares.ejemplares"%>
<%@ page import="java.util.List"%>
<%@ page import="biblioteca.udb.ConexionBD"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Biblioteca UDB</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <script src="${pageContext.request.contextPath}/js/funciones.js" defer></script>
</head>
<body>
    <div class="container">
        <div class="login-container">
            <h1 class="login-title">📚 Biblioteca UDB</h1>
            
            <form action="login" method="post" class="formulario-seccion" onsubmit="return validarFormularioLogin()">
                <div class="grupo-campo">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" 
                           value="${param.usuario}" 
                           placeholder="Ingrese su usuario"
                           required>
                </div>
                
                <div class="grupo-campo">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" 
                           placeholder="Ingrese su contraseña"
                           required>
                </div>
                
                <div class="grupo-botones">
                    <button type="submit" class="boton boton-primary">
                        INICIAR SESIÓN
                    </button>
                </div>
            </form>
            
            <div class="login-footer">
                <a href="cambiar-contrasena.jsp" class="enlace enlace-secundario">
                    ¿Olvidó su contraseña?
                </a>
            </div>
            
            <%-- Mostrar mensajes de error/éxito --%>
            <c:if test="${not empty mensaje}">
                <div class="alert ${tipoMensaje == 'error' ? 'alert-error' : 'alert-success'}">
                    ${mensaje}
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>