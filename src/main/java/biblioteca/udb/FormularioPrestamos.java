package biblioteca.udb;
import biblioteca.udb.menu.menuVista;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

public class FormularioPrestamos extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioPrestamos.class.getName());
    private JFrame menuPrincipal;

    public FormularioPrestamos() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public FormularioPrestamos(JFrame menu) {
        this.menuPrincipal = menu;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoEjemplar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboDiasPrestamo = new javax.swing.JComboBox<>();
        btnRealizarPrestamo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaResultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar Préstamos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        jLabel4.setForeground(new java.awt.Color(51, 102, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REALIZAR PRÉSTAMOS");

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel1.setText("ID Usuario:");

        txtIdUsuario.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setText("Código Ejemplar:");

        txtCodigoEjemplar.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLabel3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel3.setText("Días de Préstamo:");

        comboDiasPrestamo.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        comboDiasPrestamo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "7", "14", "21", "30" }));

        btnRealizarPrestamo.setBackground(new java.awt.Color(51, 102, 153));
        btnRealizarPrestamo.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnRealizarPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarPrestamo.setText("REALIZAR PRÉSTAMO");
        btnRealizarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPrestamoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("VOLVER AL MENÚ");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        areaResultados.setEditable(false);
        areaResultados.setBackground(new java.awt.Color(242, 242, 242));
        areaResultados.setColumns(20);
        areaResultados.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        areaResultados.setLineWrap(true);
        areaResultados.setRows(5);
        areaResultados.setWrapStyleWord(true);
        jScrollPane1.setViewportView(areaResultados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(comboDiasPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(btnRealizarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboDiasPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnRealizarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnRealizarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String idUsuarioTexto = txtIdUsuario.getText();
            String codigoEjemplar = txtCodigoEjemplar.getText();
            String diasTexto = (String) comboDiasPrestamo.getSelectedItem();

            if (idUsuarioTexto.isEmpty() || codigoEjemplar.isEmpty()) {
                areaResultados.setText("Error: Todos los campos son obligatorios");
                return;
            }

            int idUsuario = Integer.parseInt(idUsuarioTexto);
            int diasPrestamo = Integer.parseInt(diasTexto);

            GestionPrestamos gestionPrestamos = new GestionPrestamos();

            if (gestionPrestamos.usuarioTieneMora(idUsuario)) {
                areaResultados.setText("Error: El usuario tiene mora pendiente.\nNo se puede realizar el préstamo.\nPor favor, pagar la mora antes de solicitar nuevos préstamos.");
                return;
            }

            if (!gestionPrestamos.ejemplarDisponible(codigoEjemplar)) {
                areaResultados.setText("Error: El ejemplar no está disponible.\nPuede estar prestado o no existe en el sistema.");
                return;
            }

            boolean resultado = gestionPrestamos.realizarPrestamo(idUsuario, codigoEjemplar, diasPrestamo);

            if (resultado) {
                areaResultados.setText("¡Préstamo realizado exitosamente!\n\nUsuario ID: " + idUsuario + "\nEjemplar: " + codigoEjemplar + "\nDías de préstamo: " + diasPrestamo + "\n\nPor favor, devolver el ejemplar antes de la fecha límite.");
                txtIdUsuario.setText("");
                txtCodigoEjemplar.setText("");
                comboDiasPrestamo.setSelectedItem("7");
            } else {
                areaResultados.setText("Error: No se pudo realizar el préstamo.\nVerifique los datos e intente nuevamente.");
            }

        } catch (NumberFormatException ex) {
            areaResultados.setText("Error: El ID de usuario debe ser un número válido");
        } catch (Exception ex) {
            areaResultados.setText("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        if (menuPrincipal != null) {
            menuPrincipal.setVisible(true);
        }
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

        java.awt.EventQueue.invokeLater(() -> new FormularioPrestamos().setVisible(true));
    }

    private javax.swing.JTextArea areaResultados;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRealizarPrestamo;
    private javax.swing.JComboBox<String> comboDiasPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigoEjemplar;
    private javax.swing.JTextField txtIdUsuario;
}