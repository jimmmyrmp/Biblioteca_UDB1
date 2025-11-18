package biblioteca.udb;

import java.awt.Color;
import java.awt.Font;

public class Devoluciones extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Devoluciones.class.getName());

    public Devoluciones() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoEjemplar1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdUsuario1 = new javax.swing.JTextField();
        btnRealizarDevolucion1 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaResultados1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Devoluciones");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        jLabel1.setForeground(new java.awt.Color(51, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DEVOLUCIONES");

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setText("Código Ejemplar:");

        txtCodigoEjemplar1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLabel5.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel5.setText("ID Usuario:");

        txtIdUsuario1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        btnRealizarDevolucion1.setBackground(new java.awt.Color(51, 102, 153));
        btnRealizarDevolucion1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnRealizarDevolucion1.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarDevolucion1.setText("REALIZAR DEVOLUCIÓN");
        btnRealizarDevolucion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarDevolucion1ActionPerformed(evt);
            }
        });

        btnCancelar1.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        areaResultados1.setEditable(false);
        areaResultados1.setBackground(new java.awt.Color(242, 242, 242));
        areaResultados1.setColumns(20);
        areaResultados1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        areaResultados1.setRows(5);
        jScrollPane2.setViewportView(areaResultados1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtCodigoEjemplar1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtIdUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(btnRealizarDevolucion1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoEjemplar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRealizarDevolucion1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnRealizarDevolucion1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idUsuarioTexto = txtIdUsuario1.getText();
            String codigoEjemplar = txtCodigoEjemplar1.getText();

            if (idUsuarioTexto.isEmpty() || codigoEjemplar.isEmpty()) {
                areaResultados1.setText("Error: Todos los campos son obligatorios");
                return;
            }

            int idUsuario = Integer.parseInt(idUsuarioTexto);

            GestionDevoluciones gestionDevoluciones = new GestionDevoluciones();

            if (!gestionDevoluciones.ejemplarPrestadoAUsuario(idUsuario, codigoEjemplar)) {
                areaResultados1.setText("Error: No se encontró un préstamo activo de este ejemplar para el usuario.");
                return;
            }

            int diasMora = gestionDevoluciones.calcularDiasMora(idUsuario, codigoEjemplar);
            double multa = 0.0;
            if (diasMora > 0) {
                double tarifaMoraDiaria = gestionDevoluciones.obtenerTarifaMoraDiaria();
                multa = diasMora * tarifaMoraDiaria;
            }

            boolean resultado = gestionDevoluciones.realizarDevolucion(idUsuario, codigoEjemplar);

            if (resultado) {
                String mensaje = "Devolución realizada exitosamente.\n";
                if (multa > 0) {
                    mensaje += "Con mora de " + diasMora + " días.\n";
                    mensaje += "Multa a pagar: $" + multa + "\n";
                } else {
                    mensaje += "¡No tiene mora!";
                }
                areaResultados1.setText(mensaje);

                txtIdUsuario1.setText("");
                txtCodigoEjemplar1.setText("");
            } else {
                areaResultados1.setText("Error: No se pudo registrar la devolución. Verifique los datos.");
            }
        } catch (NumberFormatException ex) {
            areaResultados1.setText("Error: El ID de usuario debe ser un número válido");
        } catch (Exception ex) {
            areaResultados1.setText("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {
        txtIdUsuario1.setText("");
        txtCodigoEjemplar1.setText("");
        areaResultados1.setText("");
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Devoluciones().setVisible(true));
    }

    private javax.swing.JTextArea areaResultados1;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnRealizarDevolucion1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCodigoEjemplar1;
    private javax.swing.JTextField txtIdUsuario1;
}