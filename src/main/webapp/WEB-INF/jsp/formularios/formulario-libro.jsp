<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="biblioteca.udb.ConexionBD" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Libro - Biblioteca UDB</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #336699;
            --primary-dark: #003366;
            --secondary-color: #6c757d;
            --success-color: #28a745;
            --danger-color: #dc3545;
            --light-color: #f8f9fa;
            --dark-color: #343a40;
            --card-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            --transition: all 0.3s ease;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            color: var(--dark-color);
            line-height: 1.6;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
        }

        header {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
            color: white;
            padding: 25px;
            border-radius: 12px;
            margin-bottom: 30px;
            box-shadow: var(--card-shadow);
            text-align: center;
        }

        header h1 {
            font-size: 2.2rem;
            margin-bottom: 10px;
            font-weight: 700;
        }

        header .subtitle {
            font-size: 1.1rem;
            opacity: 0.9;
            margin-bottom: 15px;
        }

        .back-btn {
            background: rgba(255,255,255,0.2);
            color: white;
            padding: 10px 20px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            transition: var(--transition);
            display: inline-flex;
            align-items: center;
            gap: 8px;
        }

        .back-btn:hover {
            background: rgba(255,255,255,0.3);
            transform: translateY(-2px);
        }

        .form-container {
            background: white;
            border-radius: 12px;
            padding: 30px;
            box-shadow: var(--card-shadow);
            margin-bottom: 30px;
        }

        .form-section {
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #eee;
        }

        .form-section:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }

        .form-section h3 {
            color: var(--primary-color);
            margin-bottom: 20px;
            font-size: 1.4rem;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #ddd;
            border-radius: 6px;
            font-size: 1rem;
            transition: var(--transition);
            font-family: inherit;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(51, 102, 153, 0.1);
        }

        .form-group input[readonly] {
            background-color: #f8f9fa;
            cursor: not-allowed;
        }

        .btn-group {
            display: flex;
            gap: 15px;
            justify-content: center;
            margin-top: 30px;
        }

        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 6px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: var(--transition);
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            min-width: 150px;
        }

        .btn-primary {
            background: var(--primary-color);
            color: white;
        }

        .btn-primary:hover {
            background: var(--primary-dark);
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: var(--secondary-color);
            color: white;
        }

        .btn-secondary:hover {
            background: #5a6268;
            transform: translateY(-2px);
        }

        footer {
            background: var(--dark-color);
            color: white;
            text-align: center;
            padding: 20px;
            border-radius: 12px;
        }

        .mensaje {
            padding: 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: 500;
        }

        .mensaje.exito {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .mensaje.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        @media (max-width: 768px) {
            .form-grid {
                grid-template-columns: 1fr;
            }
            
            .btn-group {
                flex-direction: column;
            }
            
            .btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1><i class="fas fa-book"></i> REGISTRAR NUEVO LIBRO</h1>
            <p class="subtitle">Complete todos los campos para agregar un nuevo libro al catálogo</p>
            <a href="${pageContext.request.contextPath}/agregar-ejemplar" class="back-btn">
                <i class="fas fa-arrow-left"></i> Volver a Tipos de Ejemplar
            </a>
        </header>

        <%-- Mostrar mensajes --%>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            String tipoMensaje = (String) request.getAttribute("tipoMensaje");
            
            if (mensaje != null) {
        %>
            <div class="mensaje <%= tipoMensaje %>">
                <%= mensaje %>
            </div>
        <%
            }
        %>

        <form action="${pageContext.request.contextPath}/registrar-libro" method="POST" class="form-container">
            <!-- Sección 1: Información Básica -->
            <div class="form-section">
                <h3><i class="fas fa-info-circle"></i> Información Básica</h3>
                <div class="form-grid">
                    <div class="form-group">
                        <label for="codigo"><i class="fas fa-barcode"></i> Código *</label>
                        <input type="text" id="codigo" name="codigo" required 
                               placeholder="Ej: LIB-2024-001" maxlength="20">
                    </div>
                    
                    <div class="form-group">
                        <label for="titulo"><i class="fas fa-heading"></i> Título *</label>
                        <input type="text" id="titulo" name="titulo" required 
                               placeholder="Título completo del libro">
                    </div>
                    
                    <div class="form-group">
                        <label for="autor"><i class="fas fa-user-edit"></i> Autor *</label>
                        <input type="text" id="autor" name="autor" required 
                               placeholder="Nombre del autor">
                    </div>
                    
                    <div class="form-group">
                        <label for="año"><i class="fas fa-calendar-alt"></i> Año de Publicación *</label>
                        <input type="number" id="año" name="año" required 
                               min="1900" max="<%= java.time.Year.now().getValue() %>" 
                               value="<%= java.time.Year.now().getValue() %>">
                    </div>
                </div>
            </div>

            <!-- Sección 2: Detalles del Libro -->
            <div class="form-section">
                <h3><i class="fas fa-book-open"></i> Detalles Específicos</h3>
                <div class="form-grid">
                    <div class="form-group">
                        <label for="isbn"><i class="fas fa-hashtag"></i> ISBN</label>
                        <input type="text" id="isbn" name="isbn" 
                               placeholder="13 dígitos ISBN">
                    </div>
                    
                    <div class="form-group">
                        <label for="editorial"><i class="fas fa-building"></i> Editorial</label>
                        <input type="text" id="editorial" name="editorial" 
                               placeholder="Nombre de la editorial">
                    </div>
                    
                    <div class="form-group">
                        <label for="numPaginas"><i class="fas fa-file-alt"></i> Número de Páginas</label>
                        <input type="number" id="numPaginas" name="numPaginas" 
                               min="1" placeholder="Ej: 250">
                    </div>
                    
                    <div class="form-group">
                        <label for="genero"><i class="fas fa-tags"></i> Género *</label>
                        <select id="genero" name="genero" required>
                            <option value="">-- Seleccione un género --</option>
                            <%
                                Connection conexion = null;
                                try {
                                    conexion = ConexionBD.conectar();
                                    String sql = "SELECT DISTINCT genero FROM libros ORDER BY genero";
                                    PreparedStatement pstmt = conexion.prepareStatement(sql);
                                    ResultSet rs = pstmt.executeQuery();
                                    
                                    while (rs.next()) {
                                        String genero = rs.getString("genero");
                            %>
                                <option value="<%= genero %>"><%= genero %></option>
                            <%
                                    }
                                    rs.close();
                                    pstmt.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    if (conexion != null) {
                                        try { conexion.close(); } catch (Exception e) {}
                                    }
                                }
                            %>
                            <option value="otro">Otro (especificar)</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group" id="otroGeneroContainer" style="display: none;">
                    <label for="otroGenero"><i class="fas fa-plus-circle"></i> Especificar Género</label>
                    <input type="text" id="otroGenero" name="otroGenero" 
                           placeholder="Escriba el nuevo género">
                </div>
            </div>

            <!-- Sección 3: Información Adicional -->
            <div class="form-section">
                <h3><i class="fas fa-map-marker-alt"></i> Ubicación en Biblioteca</h3>
                <div class="form-grid">
                    <div class="form-group">
                        <label for="ubicacion"><i class="fas fa-map-pin"></i> Ubicación *</label>
                        <input type="text" id="ubicacion" name="ubicacion" required 
                               placeholder="Ej: Estante A-5, Sección Literatura">
                    </div>
                    
                    <div class="form-group">
                        <label for="tipo"><i class="fas fa-bookmark"></i> Tipo de Ejemplar</label>
                        <input type="text" id="tipo" name="tipo" value="Libro" readonly>
                    </div>
                </div>
            </div>

            <!-- Botones -->
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save"></i> GUARDAR LIBRO
                </button>
                <button type="reset" class="btn btn-secondary">
                    <i class="fas fa-eraser"></i> LIMPIAR FORMULARIO
                </button>
            </div>
        </form>

        <footer>
            <p>&copy; 2024 Biblioteca Universidad Don Bosco | Formulario de Registro de Libros</p>
        </footer>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const generoSelect = document.getElementById('genero');
            const otroGeneroContainer = document.getElementById('otroGeneroContainer');
            
            generoSelect.addEventListener('change', function() {
                if (this.value === 'otro') {
                    otroGeneroContainer.style.display = 'block';
                } else {
                    otroGeneroContainer.style.display = 'none';
                }
            });
            
            const form = document.querySelector('form');
            form.addEventListener('submit', function(e) {
                if (generoSelect.value === 'otro') {
                    const otroGenero = document.getElementById('otroGenero').value.trim();
                    if (!otroGenero) {
                        e.preventDefault();
                        alert('Por favor especifique el género');
                        return;
                    }
                }
                
                const codigo = document.getElementById('codigo').value.trim();
                const titulo = document.getElementById('titulo').value.trim();
                const autor = document.getElementById('autor').value.trim();
                const ubicacion = document.getElementById('ubicacion').value.trim();
                
                if (!codigo || !titulo || !autor || !ubicacion) {
                    e.preventDefault();
                    alert('Por favor complete todos los campos obligatorios (*)');
                }
            });
            
            const añoInput = document.getElementById('año');
            añoInput.addEventListener('input', function() {
                const currentYear = new Date().getFullYear();
                if (this.value > currentYear) {
                    this.value = currentYear;
                }
            });
        });
    </script>
</body>
</html>