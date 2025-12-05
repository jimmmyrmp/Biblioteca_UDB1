<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Tipo de Ejemplar - Biblioteca UDB</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #336699;
            --primary-dark: #003366;
            --secondary-color: #6c757d;
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
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        header {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
            color: white;
            padding: 30px 20px;
            border-radius: 12px;
            margin-bottom: 30px;
            box-shadow: var(--card-shadow);
            text-align: center;
            position: relative;
            overflow: hidden;
        }

        .header-content {
            position: relative;
            z-index: 1;
        }

        header h1 {
            font-size: 2.8rem;
            margin-bottom: 10px;
            font-weight: 800;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
            letter-spacing: 1px;
        }

        header .subtitle {
            font-size: 1.2rem;
            opacity: 0.9;
            font-weight: 300;
            margin-bottom: 20px;
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
            margin-top: 15px;
        }

        .back-btn:hover {
            background: rgba(255,255,255,0.3);
            transform: translateY(-2px);
        }

        .ejemplares-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }

        .ejemplar-card {
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: var(--card-shadow);
            transition: var(--transition);
            cursor: pointer;
            height: 100%;
            display: flex;
            flex-direction: column;
            border: 2px solid transparent;
        }

        .ejemplar-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.15);
            border-color: var(--primary-color);
        }

        .ejemplar-icon {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
            color: white;
            padding: 30px;
            text-align: center;
            font-size: 3rem;
        }

        .ejemplar-content {
            padding: 25px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }

        .ejemplar-title {
            font-size: 1.4rem;
            color: var(--primary-color);
            margin-bottom: 10px;
            font-weight: 600;
            text-align: center;
        }

        .ejemplar-desc {
            color: #666;
            margin-bottom: 20px;
            text-align: center;
            flex-grow: 1;
        }

        .ejemplar-btn {
            background: var(--primary-color);
            color: white;
            padding: 12px 25px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            text-align: center;
            transition: var(--transition);
            border: none;
            cursor: pointer;
            width: 100%;
            font-size: 1rem;
        }

        .ejemplar-btn:hover {
            background: var(--primary-dark);
            transform: translateY(-2px);
        }

        footer {
            background: var(--dark-color);
            color: white;
            text-align: center;
            padding: 25px;
            margin-top: 50px;
            border-radius: 12px;
        }

        footer p {
            margin: 5px 0;
            opacity: 0.9;
        }

        @media (max-width: 768px) {
            header h1 {
                font-size: 2rem;
            }
            
            .ejemplares-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 480px) {
            .ejemplares-grid {
                grid-template-columns: 1fr;
            }
            
            .container {
                padding: 10px;
            }
            
            header, .ejemplar-card {
                padding: 15px;
            }
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .ejemplar-card {
            animation: fadeIn 0.5s ease-out forwards;
        }

        .ejemplar-card:nth-child(1) { animation-delay: 0.1s; }
        .ejemplar-card:nth-child(2) { animation-delay: 0.2s; }
        .ejemplar-card:nth-child(3) { animation-delay: 0.3s; }
        .ejemplar-card:nth-child(4) { animation-delay: 0.4s; }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <div class="header-content">
                <h1><i class="fas fa-book-medical"></i> AGREGAR EJEMPLAR</h1>
                <p class="subtitle">Seleccione el tipo de material bibliográfico que desea registrar</p>
                <a href="index.html" class="back-btn">
                    <i class="fas fa-arrow-left"></i> Volver al Menú Principal
                </a>
            </div>
        </header>

        <div class="ejemplares-grid">
            <!-- Libro -->
            <div class="ejemplar-card" onclick="seleccionarTipo('Libro')">
                <div class="ejemplar-icon">
                    <i class="fas fa-book"></i>
                </div>
                <div class="ejemplar-content">
                    <h3 class="ejemplar-title">LIBRO</h3>
                    <p class="ejemplar-desc">Obras impresas, novelas, textos académicos, enciclopedias y manuales.</p>
                    <a href="${pageContext.request.contextPath}/formulario-libro">
                        <button class="ejemplar-btn" onclick="event.stopPropagation(); seleccionarTipo('Libro')">
                        <i class="fas fa-plus"></i> Seleccionar
                    </button>
                    </a>
                </div>
            </div>

            <!-- Revista -->
            <div class="ejemplar-card" onclick="seleccionarTipo('Revista')">
                <div class="ejemplar-icon">
                    <i class="fas fa-newspaper"></i>
                </div>
                <div class="ejemplar-content">
                    <h3 class="ejemplar-title">REVISTA</h3>
                    <p class="ejemplar-desc">Publicaciones periódicas especializadas, magazines y revistas académicas.</p>
                    <button class="ejemplar-btn" onclick="event.stopPropagation(); seleccionarTipo('Revista')">
                        <i class="fas fa-plus"></i> Seleccionar
                    </button>
                </div>
            </div>

            <!-- Tesis -->
            <div class="ejemplar-card" onclick="seleccionarTipo('Tesis')">
                <div class="ejemplar-icon">
                    <i class="fas fa-graduation-cap"></i>
                </div>
                <div class="ejemplar-content">
                    <h3 class="ejemplar-title">TESIS</h3>
                    <p class="ejemplar-desc">Trabajos de investigación para grados académicos, maestrías y doctorados.</p>
                    <button class="ejemplar-btn" onclick="event.stopPropagation(); seleccionarTipo('Tesis')">
                        <i class="fas fa-plus"></i> Seleccionar
                    </button>
                </div>
            </div>

            <!-- CD -->
            <div class="ejemplar-card" onclick="seleccionarTipo('CD')">
                <div class="ejemplar-icon">
                    <i class="fas fa-compact-disc"></i>
                </div>
                <div class="ejemplar-content">
                    <h3 class="ejemplar-title">CD</h3>
                    <p class="ejemplar-desc">Discos compactos con contenido musical, educativo o de software.</p>
                    <button class="ejemplar-btn" onclick="event.stopPropagation(); seleccionarTipo('CD')">
                        <i class="fas fa-plus"></i> Seleccionar
                    </button>
                </div>
            </div>
        </div>

        <footer>
            <p>&copy; 2024 Biblioteca Universidad Don Bosco. Sistema de Gestión Bibliotecaria</p>
            <p>Seleccione un tipo de ejemplar para continuar con el registro</p>
        </footer>
    </div>

    <script>
        function seleccionarTipo(tipo) {
            switch(tipo) {
                case 'Libro':
                    window.location.href = '${pageContext.request.contextPath}/formulario-libro';
                    break;
                case 'Revista':
                    window.location.href = '${pageContext.request.contextPath}/formulario-revista';
                    break;
                case 'Tesis':
                    window.location.href = '${pageContext.request.contextPath}/formulario-tesis';
                    break;
                case 'CD':
                    window.location.href = '${pageContext.request.contextPath}/formulario-cd';
                    break;
                default:
                    alert('Tipo de ejemplar no implementado aún');
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            const cards = document.querySelectorAll('.ejemplar-card');
            cards.forEach(card => {
                card.addEventListener('click', function(e) {
                    if (!e.target.closest('.ejemplar-btn')) {
                        const tipo = this.querySelector('.ejemplar-title').textContent.trim();
                        seleccionarTipo(tipo);
                    }
                });
            });
        });
    </script>
</body>
</html>