cat /mnt/user-data/outputs/consultas-con-css-inline.jsp

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="biblioteca.udb.ejemplares.ejemplares"%>
<%@ page import="java.util.List"%>
<%@ page import="biblioteca.udb.ConexionBD"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de Ejemplares - Biblioteca UDB</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html, body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        header {
            background: linear-gradient(135deg, #336699 0%, #003366 100%);
            color: white;
            padding: 40px 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        header h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
            font-weight: 700;
        }

        header p {
            font-size: 1.1rem;
            opacity: 0.9;
            font-weight: 300;
        }

        .formulario-seccion {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .formulario-busqueda {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            align-items: end;
        }

        .grupo-campo {
            display: flex;
            flex-direction: column;
        }

        .grupo-campo label {
            font-weight: 600;
            margin-bottom: 8px;
            color: #333;
            font-size: 0.95rem;
        }

        .grupo-campo input,
        .grupo-campo select {
            padding: 12px 15px;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
            background-color: white;
            font-family: inherit;
        }

        .grupo-campo input:focus,
        .grupo-campo select:focus {
            outline: none;
            border-color: #336699;
            box-shadow: 0 0 0 3px rgba(51, 102, 153, 0.1);
        }

        .grupo-campo input::placeholder {
            color: #999;
        }

        .grupo-botones {
            display: flex;
            gap: 10px;
            grid-column: 1 / -1;
            justify-content: center;
            margin-top: 10px;
        }

        .boton {
            padding: 12px 30px;
            border: none;
            border-radius: 6px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .boton-buscar {
            background: linear-gradient(135deg, #336699 0%, #003366 100%);
            color: white;
            flex: 1;
            max-width: 200px;
        }

        .boton-buscar:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(51, 102, 153, 0.3);
        }

        .boton-buscar:active {
            transform: translateY(0);
        }

        .boton-limpiar {
            background-color: #e0e0e0;
            color: #333;
            flex: 1;
            max-width: 200px;
        }

        .boton-limpiar:hover {
            background-color: #d0d0d0;
            transform: translateY(-2px);
        }

        .resultados-seccion {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .estadisticas {
            background-color: #f0f8ff;
            padding: 15px 20px;
            border-left: 4px solid #336699;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .estadisticas p {
            margin: 0;
            color: #336699;
            font-weight: 500;
        }

        .mensaje-info {
            background-color: #fff3cd;
            color: #856404;
            padding: 20px;
            border-radius: 6px;
            border: 1px solid #ffeeba;
            text-align: center;
            font-weight: 500;
        }

        .tabla-resultados {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 0.95rem;
        }

        .tabla-resultados thead {
            background: linear-gradient(135deg, #336699 0%, #003366 100%);
            color: white;
        }

        .tabla-resultados th {
            padding: 15px;
            text-align: left;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.85rem;
            letter-spacing: 0.5px;
        }

        .tabla-resultados td {
            padding: 15px;
            border-bottom: 1px solid #eee;
        }

        .tabla-resultados tbody tr {
            transition: background-color 0.2s;
        }

        .tabla-resultados tbody tr:hover {
            background-color: #f9f9f9;
        }

        .tabla-resultados tbody tr:nth-child(odd) {
            background-color: #fafafa;
        }

        .tabla-resultados tbody tr:nth-child(odd):hover {
            background-color: #f0f0f0;
        }

        .estado {
            padding: 8px 12px;
            border-radius: 4px;
            font-weight: 600;
            text-align: center;
            font-size: 0.9rem;
        }

        .estado.disponible {
            background-color: #c8e6c9;
            color: #2e7d32;
        }

        .estado.prestado {
            background-color: #ffcccc;
            color: #c62828;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 40px;
            border-radius: 8px;
            font-size: 0.9rem;
        }

        @media (max-width: 768px) {
            header h1 {
                font-size: 1.8rem;
            }

            header p {
                font-size: 1rem;
            }

            .formulario-busqueda {
                grid-template-columns: 1fr;
            }

            .grupo-botones {
                grid-column: 1;
                flex-direction: column;
            }

            .boton {
                max-width: none;
                width: 100%;
            }

            .tabla-resultados {
                font-size: 0.85rem;
            }

            .tabla-resultados th,
            .tabla-resultados td {
                padding: 10px;
            }

            .container {
                padding: 10px;
            }

            .formulario-seccion,
            .resultados-seccion {
                padding: 15px;
            }
        }

        @media (max-width: 480px) {
            header {
                padding: 20px 10px;
            }

            header h1 {
                font-size: 1.5rem;
            }

            header p {
                font-size: 0.9rem;
            }

            .tabla-resultados {
                font-size: 0.75rem;
            }

            .tabla-resultados th,
            .tabla-resultados td {
                padding: 8px 5px;
            }

            .estado {
                padding: 6px 8px;
                font-size: 0.8rem;
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .formulario-seccion,
        .resultados-seccion {
            animation: fadeIn 0.3s ease-in-out;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>📚 CONSULTA DE EJEMPLARES</h1>
            <p>Busca los materiales disponibles en la Biblioteca UDB</p>
        </header>

        <section class="formulario-seccion">
            <form action="${pageContext.request.contextPath}/consultas" method="GET" class="formulario-busqueda">
                <input type="hidden" name="accion" value="buscar">
                
                <div class="grupo-campo">
                    <label for="titulo">Título:</label>
                    <input type="text" id="titulo" name="titulo" placeholder="Ingrese título del libro..." 
                           value="${param.titulo}">
                </div>

                <div class="grupo-campo">
                    <label for="autor">Autor:</label>
                    <input type="text" id="autor" name="autor" placeholder="Ingrese nombre del autor..." 
                           value="${param.autor}">
                </div>

                <div class="grupo-campo">
                    <label for="tipo">Tipo de Material:</label>
                    <select id="tipo" name="tipo">
                        <option value="">-- Seleccione tipo --</option>
                        <%
                            // Cargar tipos de ejemplares dinámicamente desde BD
                            Connection conexion = ConexionBD.conectar();
                            if (conexion != null) {
                                try {
                                    Statement stmt = conexion.createStatement();
                                    ResultSet rs = stmt.executeQuery(
                                        "SELECT DISTINCT tipo FROM ejemplares ORDER BY tipo"
                                    );
                                    
                                    String tipoSeleccionado = request.getParameter("tipo");
                                    
                                    while (rs.next()) {
                                        String tipo = rs.getString("tipo");
                                        String selected = (tipo != null && tipo.equals(tipoSeleccionado)) ? "selected" : "";
                                        out.println("<option value=\"" + tipo + "\" " + selected + ">" + tipo + "</option>");
                                    }
                                    
                                    rs.close();
                                    stmt.close();
                                } catch (SQLException e) {
                                    out.println("<option>Error al cargar tipos</option>");
                                } finally {
                                    ConexionBD.cerrarConexion(conexion);
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="grupo-botones">
                    <button type="submit" class="boton boton-buscar">🔍 BUSCAR</button>
                    <button type="reset" class="boton boton-limpiar">🔄 LIMPIAR</button>
                </div>
            </form>
        </section>

        <!-- Resultados de la búsqueda -->
        <section class="resultados-seccion">
            <%
                List<ejemplares> ejemplaresList = (List<ejemplares>) request.getAttribute("ejemplares");
                
                if (ejemplaresList != null) {
                    if (ejemplaresList.isEmpty()) {
                        out.println("<div class='mensaje-info'>");
                        out.println("⚠️ No se encontraron resultados para su búsqueda");
                        out.println("</div>");
                    } else {
                        out.println("<div class='estadisticas'>");
                        out.println("<p>Se encontraron <strong>" + ejemplaresList.size() + "</strong> resultado(s)</p>");
                        out.println("</div>");
                        
                        out.println("<table class='tabla-resultados'>");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th>Código</th>");
                        out.println("<th>Título</th>");
                        out.println("<th>Autor</th>");
                        out.println("<th>Año</th>");
                        out.println("<th>Ubicación</th>");
                        out.println("<th>Tipo</th>");
                        out.println("<th>Estado</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody>");
                        
                        for (ejemplares ej : ejemplaresList) {
                            String estado = ej.isDisponible() ? "✓ Disponible" : "✗ Prestado";
                            String claseEstado = ej.isDisponible() ? "disponible" : "prestado";
                            
                            out.println("<tr>");
                            out.println("<td>" + ej.getCodigo() + "</td>");
                            out.println("<td>" + ej.getTitulo() + "</td>");
                            out.println("<td>" + (ej.getAutor() != null ? ej.getAutor() : "N/A") + "</td>");
                            out.println("<td>" + ej.getAño() + "</td>");
                            out.println("<td>" + ej.getUbicacion() + "</td>");
                            out.println("<td>" + ej.getTipo() + "</td>");
                            out.println("<td class='estado " + claseEstado + "'>" + estado + "</td>");
                            out.println("</tr>");
                        }
                        
                        out.println("</tbody>");
                        out.println("</table>");
                    }
                }
            %>
        </section>
    </div>

    <footer>
        <p>&copy;Biblioteca Universidad Don Bosco. </p>
    </footer>
</body>
</html>