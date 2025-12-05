<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="biblioteca.udb.ConexionBD" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú Administrador - Biblioteca UDB</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #336699;
            --primary-dark: #003366;
            --secondary-color: #6c757d;
            --success-color: #28a745;
            --danger-color: #dc3545;
            --warning-color: #ffc107;
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

        /* Header */
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

        header::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: radial-gradient(circle, rgba(255,255,255,0.1) 1%, transparent 1%);
            background-size: 30px 30px;
            animation: float 20s linear infinite;
        }

        @keyframes float {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
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

        .user-info {
            background: rgba(255,255,255,0.1);
            padding: 15px;
            border-radius: 8px;
            display: inline-block;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255,255,255,0.2);
        }

        /* Dashboard Grid */
        .dashboard {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }

        /* Cards */
        .card {
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: var(--card-shadow);
            transition: var(--transition);
            position: relative;
            cursor: pointer;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.15);
        }

        .card-icon {
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
            color: white;
            padding: 25px;
            text-align: center;
            font-size: 2.5rem;
        }

        .card-content {
            padding: 25px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }

        .card h3 {
            font-size: 1.4rem;
            color: var(--primary-color);
            margin-bottom: 15px;
            font-weight: 600;
        }

        .card p {
            color: #666;
            margin-bottom: 20px;
            flex-grow: 1;
        }

        .card-btn {
            display: inline-block;
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

        .card-btn:hover {
            background: var(--primary-dark);
            transform: translateY(-2px);
        }

        /* Quick Stats */
        .quick-stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }

        .stat-card {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: var(--card-shadow);
            display: flex;
            align-items: center;
            gap: 20px;
            transition: var(--transition);
        }

        .stat-card:hover {
            transform: translateY(-5px);
        }

        .stat-icon {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.8rem;
            color: white;
        }

        .stat-icon.books { background: linear-gradient(135deg, #4CAF50 0%, #2E7D32 100%); }
        .stat-icon.users { background: linear-gradient(135deg, #2196F3 0%, #0D47A1 100%); }
        .stat-icon.loans { background: linear-gradient(135deg, #FF9800 0%, #EF6C00 100%); }
        .stat-icon.overdue { background: linear-gradient(135deg, #F44336 0%, #C62828 100%); }

        .stat-info h4 {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 5px;
            color: var(--dark-color);
        }

        .stat-info p {
            color: #666;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        /* System Info */
        .system-info {
            background: white;
            border-radius: 12px;
            padding: 30px;
            box-shadow: var(--card-shadow);
            margin-top: 30px;
        }

        .system-info h3 {
            color: var(--primary-color);
            margin-bottom: 20px;
            font-size: 1.5rem;
            border-bottom: 2px solid var(--primary-color);
            padding-bottom: 10px;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }

        .info-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .info-item:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 600;
            color: #666;
        }

        .info-value {
            color: var(--dark-color);
            font-weight: 500;
        }

        /* Footer */
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

        .footer-links {
            margin-top: 15px;
        }

        .footer-links a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
            opacity: 0.8;
            transition: var(--transition);
        }

        .footer-links a:hover {
            opacity: 1;
            text-decoration: underline;
        }

        /* Responsive */
        @media (max-width: 768px) {
            header h1 {
                font-size: 2rem;
            }
            
            .dashboard {
                grid-template-columns: 1fr;
            }
            
            .quick-stats {
                grid-template-columns: repeat(2, 1fr);
            }
            
            .stat-card {
                flex-direction: column;
                text-align: center;
            }
        }

        @media (max-width: 480px) {
            .quick-stats {
                grid-template-columns: 1fr;
            }
            
            .container {
                padding: 10px;
            }
            
            header, .card, .stat-card, .system-info {
                padding: 20px;
            }
        }

        /* Animations */
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

        .card, .stat-card, .system-info {
            animation: fadeIn 0.5s ease-out forwards;
        }

        .card:nth-child(1) { animation-delay: 0.1s; }
        .card:nth-child(2) { animation-delay: 0.2s; }
        .card:nth-child(3) { animation-delay: 0.3s; }
        .card:nth-child(4) { animation-delay: 0.4s; }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <div class="header-content">
                <h1><i class="fas fa-book-reader"></i> MENÚ PRINCIPAL</h1>
                <p class="subtitle">Sistema de Gestión de Biblioteca - Panel Administrativo</p>
                <div class="user-info">
                    <i class="fas fa-user-shield"></i> Sesión: Administrador | 
                    <i class="fas fa-calendar-alt"></i> <%= new java.util.Date().toLocaleString() %>
                </div>
            </div>
        </header>

        <!-- Main Dashboard -->
        <div class="dashboard">
            <!-- Agregar Ejemplar -->
            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-book-medical"></i>
                </div>
                <div class="card-content">
                    <h3>AGREGAR EJEMPLAR</h3>
                    <p>Registre nuevos materiales bibliográficos en el sistema. Libros, revistas, tesis, multimedia y más.</p>
                    <a href="agregar-ejemplares" class="card-btn">
                        <i class="fas fa-arrow-right"></i> Acceder
                    </a>
                </div>
            </div>

            <!-- Agregar Usuario -->
            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-user-plus"></i>
                </div>
                <div class="card-content">
                    <h3>AGREGAR USUARIO</h3>
                    <p>Registre nuevos usuarios en el sistema: estudiantes, profesores y personal administrativo.</p>
                    <a href="${pageContext.request.contextPath}/usuarios" class="card-btn">
                        <i class="fas fa-arrow-right"></i> Acceder
                    </a>
                </div>
            </div>

            <!-- Devoluciones -->
            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-book-return"></i>
                </div>
                <div class="card-content">
                    <h3>DEVOLUCIONES</h3>
                    <p>Gestione las devoluciones de ejemplares prestados. Registre multas por mora si aplica.</p>
                    <a href="${pageContext.request.contextPath}/devoluciones" class="card-btn">
                        <i class="fas fa-arrow-right"></i> Acceder
                    </a>
                </div>
            </div>

            <!-- Control Préstamos -->
            <div class="card">
                <div class="card-icon">
                    <i class="fas fa-cogs"></i>
                </div>
                <div class="card-content">
                    <h3>CONTROL PRÉSTAMOS</h3>
                    <p>Configure parámetros del sistema: máximo de ejemplares por préstamo, días de préstamo, etc.</p>
                    <a href="${pageContext.request.contextPath}/configuracion" class="card-btn">
                        <i class="fas fa-arrow-right"></i> Acceder
                    </a>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Biblioteca Universidad Don Bosco. Todos los derechos reservados.</p>
        <p>Sistema de Gestión Bibliotecaria - Versión Web</p>
        <div class="footer-links">
            <a href="#"><i class="fas fa-question-circle"></i> Ayuda</a>
            <a href="#"><i class="fas fa-cog"></i> Configuración</a>
            <a href="#"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
        </div>
    </footer>

    <script>
        // Cargar estadísticas desde el servidor
        document.addEventListener('DOMContentLoaded', function() {
            // Simulación de datos (en producción, hacer petición AJAX)
            setTimeout(() => {
                document.getElementById('totalBooks').textContent = '1,245';
                document.getElementById('totalUsers').textContent = '893';
                document.getElementById('activeLoans').textContent = '127';
                document.getElementById('overdueLoans').textContent = '18';
            }, 500);
            
            // Navegación
            const cards = document.querySelectorAll('.card');
            cards.forEach(card => {
                card.addEventListener('click', function(e) {
                    if (!e.target.closest('.card-btn')) {
                        const link = this.querySelector('.card-btn');
                        if (link) window.location.href = link.href;
                    }
                });
            });
        });
    </script>
</body>
</html>