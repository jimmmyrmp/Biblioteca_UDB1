package biblioteca.udb;

import biblioteca.udb.ejemplares.ejemplares;
import java.sql.Connection;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

public class ConsultaEjemplaresFORM extends javax.swing.JFrame {

    private Ejemplar1 ejemplarDAO;
    private DefaultTableModel modeloTabla;

    public ConsultaEjemplaresFORM() {
        initComponents();
        setLocationRelativeTo(null);
        inicializar();
    }

    private void inicializar() {
        try {
            Connection conn = ConexionBD.conectar();
            ejemplarDAO = new Ejemplar1(conn);

            configurarTabla();
            cargarTodosLosEjemplares();

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al inicializar: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void configurarTabla() {
        String[] columnas = {"Código", "Título", "Autor", "Año", "Ubicación", "Disponible", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable2.setModel(modeloTabla);
    }

    private void cargarTodosLosEjemplares() {
        try {
            List<ejemplares> lista = ejemplarDAO.obtenerTodos();
            cargarEjemplaresEnTabla(lista);
            actualizarEstadisticas();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al cargar ejemplares: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEjemplaresEnTabla(List<ejemplares> lista) {
        modeloTabla.setRowCount(0);

        for (ejemplares ej : lista) {
            Object[] fila = {
                    ej.getCodigo(),
                    ej.getTitulo(),
                    ej.getAutor() != null ? ej.getAutor() : "N/A",
                    ej.getAño(),
                    ej.getUbicacion(),
                    ej.isDisponible() ? "Sí" : "No",
                    ej.getTipo()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void actualizarEstadisticas() {
        try {
            int[] stats = ejemplarDAO.obtenerEstadisticas();
            lblTotal.setText("Total: " + stats[0]);
            lblDisponibles.setText("Disponibles: " + stats[1]);
            lblPrestados.setText("Prestados: " + stats[2]);
        } catch (Exception e) {
            System.err.println("Error en estadísticas: " + e.getMessage());
        }
    }

    private void buscarEjemplares() {
        String criterio = txtBuscar.getText().trim();

        if (criterio.isEmpty()) {
            cargarTodosLosEjemplares();
            return;
        }

        try {
            List<ejemplares> lista = ejemplarDAO.buscarEjemplares(criterio);
            cargarEjemplaresEnTabla(lista);

            if (lista.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "No se encontraron ejemplares con: " + criterio,
                        "Sin resultados",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnMostrarTodos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblDisponibles = new javax.swing.JLabel();
        lblPrestados = new javax.swing.JLabel();
        volverMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta de Ejemplares");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        jLabel2.setForeground(new java.awt.Color(51, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONSULTA DE EJEMPLARES");

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel1.setText("Buscar:");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        btnBuscar.setBackground(new java.awt.Color(51, 102, 153));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnMostrarTodos.setBackground(new java.awt.Color(102, 102, 102));
        btnMostrarTodos.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnMostrarTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodos.setText("MOSTRAR TODOS");
        btnMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));

        lblTotal.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setForeground(new java.awt.Color(51, 102, 153));
        lblTotal.setText("Total: 0");

        lblDisponibles.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        lblDisponibles.setForeground(new java.awt.Color(0, 153, 51));
        lblDisponibles.setText("Disponibles: 0");

        lblPrestados.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        lblPrestados.setForeground(new java.awt.Color(204, 0, 0));
        lblPrestados.setText("Prestados: 0");

        volverMenu.setBackground(new java.awt.Color(153, 153, 153));
        volverMenu.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        volverMenu.setForeground(new java.awt.Color(255, 255, 255));
        volverMenu.setText("VOLVER AL MENÚ");
        volverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblTotal)
                                .addGap(50, 50, 50)
                                .addComponent(lblDisponibles)
                                .addGap(50, 50, 50)
                                .addComponent(lblPrestados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addComponent(volverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTotal)
                                        .addComponent(lblDisponibles)
                                        .addComponent(lblPrestados)
                                        .addComponent(volverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(20, 20, 20)
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        buscarEjemplares();
    }

    private void btnMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTodosLosEjemplares();
    }

    private void volverMenuActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaEjemplaresFORM().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblPrestados;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JButton volverMenu;
}