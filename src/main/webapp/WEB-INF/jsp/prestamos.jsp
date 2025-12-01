<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Préstamo de ejemplares - Biblioteca UDB</title>
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 900px;
            margin: 40px auto;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            padding: 30px;
        }
        header {
            border-bottom: 1px solid #e0e0e0;
            margin-bottom: 20px;
            padding-bottom: 10px;
        }
        header h1 {
            margin: 0;
            color: #336699;
        }
        header p {
            margin-top: 5px;
            color: #666666;
        }
        .mensaje {
            padding: 12px 16px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        .mensaje-ok {
            background-color: #e8f5e9;
            border: 1px solid #388e3c;
            color: #1b5e20;
        }
        .mensaje-error {
            background-color: #ffebee;
            border: 1px solid #c62828;
            color: #b71c1c;
        }
        form {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 20px;
        }
        .campo {
            display: flex;
            flex-direction: column;
        }
        .campo label {
            font-weight: 600;
            margin-bottom: 6px;
            color: #333333;
        }
        .campo input,
        .campo select {
            padding: 10px 12px;
            border-radius: 4px;
            border: 1px solid #cccccc;
            font-size: 0.95rem;
        }
        .acciones {
            grid-column: 1 / -1;
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }
        .boton {
            padding: 10px 18px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.9rem;
            letter-spacing: 0.5px;
        }
        .boton-primario {
            background-color: #336699;
            color: #ffffff;
        }
        .boton-primario:hover {
            background-color: #24486e;
        }
        .boton-secundario {
            background-color: #e0e0e0;
            color: #333333;
        }
        .boton-secundario:hover {
            background-color: #d5d5d5;
        }
        .nota {
            margin-top: 25px;
            font-size: 0.9rem;
            color: #666666;
            background-color: #f0f8ff;
            padding: 10px 12px;
            border-left: 3px solid #336699;
        }
        a.volver {
            display: inline-block;
            margin-top: 15px;
            font-size: 0.9rem;
            color: #336699;
            text-decoration: none;
        }
        a.volver:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Módulo de Préstamos</h1>
            <p>Registrar préstamos de ejemplares para usuarios registrados.</p>
        </header>

        <%
            String mensaje = (String) request.getAttribute("mensaje");
            String tipoMensaje = (String) request.getAttribute("tipoMensaje");

            if (mensaje != null && !mensaje.isEmpty()) {
                String clase = "mensaje ";
                if ("ok".equals(tipoMensaje)) {
                    clase += "mensaje-ok";
                } else {
                    clase += "mensaje-error";
                }
        %>
            <div class="<%= clase %>">
                <%= mensaje %>
            </div>
        <%
            }
            String idUsuarioTexto = (String) request.getAttribute("idUsuarioTexto");
            String codigoEjemplarTexto = (String) request.getAttribute("codigoEjemplarTexto");
            String diasSeleccionados = (String) request.getAttribute("diasSeleccionados");
            if (diasSeleccionados == null || diasSeleccionados.isEmpty()) {
                diasSeleccionados = "7";
            }
        %>

        <form method="post" action="prestamos" id="formularioPrestamo">
            <div class="campo">
                <label for="idUsuario">ID de usuario</label>
                <input type="number" id="idUsuario" name="idUsuario"
                       value="<%= (idUsuarioTexto != null ? idUsuarioTexto : "") %>"
                       placeholder="Ej: 1">
            </div>

            <div class="campo">
                <label for="codigoEjemplar">Código del ejemplar</label>
                <input type="text" id="codigoEjemplar" name="codigoEjemplar"
                       value="<%= (codigoEjemplarTexto != null ? codigoEjemplarTexto : "") %>"
                       placeholder="Ej: LIB-001">
            </div>

            <div class="campo">
                <label for="diasPrestamo">Días de préstamo</label>
                <select id="diasPrestamo" name="diasPrestamo">
                    <option value="3"  <%= "3".equals(diasSeleccionados) ? "selected" : "" %>>3 días</option>
                    <option value="7"  <%= "7".equals(diasSeleccionados) ? "selected" : "" %>>7 días</option>
                    <option value="14" <%= "14".equals(diasSeleccionados) ? "selected" : "" %>>14 días</option>
                </select>
            </div>

            <div class="acciones">
                <button type="submit" class="boton boton-primario">Registrar préstamo</button>
                <button type="button" class="boton boton-secundario" onclick="limpiarFormulario()">Limpiar</button>
            </div>
        </form>

        <div class="nota">
            <strong>Notas del sistema:</strong>
            <ul>
                <li>Solo se permiten préstamos a usuarios sin mora.</li>
                <li>El ejemplar debe estar disponible para poder prestarlo.</li>
                <li>La fecha de devolución se calcula automáticamente según los días seleccionados.</li>
            </ul>
        </div>

        <a href="<%= request.getContextPath() %>/" class="volver">← Volver al inicio</a>
    </div>
    
    <script>
        function limpiarFormulario() {
            document.getElementById("idUsuario").value = "";
            document.getElementById("codigoEjemplar").value = "";
            document.getElementById("diasPrestamo").value = "7";
        }
    </script>
</body>
</html>