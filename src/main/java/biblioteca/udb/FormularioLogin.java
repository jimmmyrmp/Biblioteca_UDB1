package biblioteca.udb;

import biblioteca.udb.menu.menuVista;
import biblioteca.udb.menu.menuAdmin;
import java.awt.Color;
import java.awt.Font;

public class FormularioLogin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioLogin.class.getName());

    public FormularioLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuarioLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContraseñaLogin = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCambiarContraseña = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaResultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Biblioteca UDB");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        jLabel3.setForeground(new java.awt.Color(51, 102, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BIBLIOTECA UDB");

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel1.setText("Usuario:");

        txtUsuarioLogin.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLabel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLabel2.setText("Contraseña:");

        txtContraseñaLogin.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        btnLogin.setBackground(new java.awt.Color(51, 102, 153));
        btnLogin.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("INICIAR SESIÓN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCambiarContraseña.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        btnCambiarContraseña.setForeground(new java.awt.Color(51, 102, 153));
        btnCambiarContraseña.setText("Olvidé mi contraseña");
        btnCambiarContraseña.setBorderPainted(false);
        btnCambiarContraseña.setContentAreaFilled(false);
        btnCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraseñaActionPerformed(evt);
            }
        });

        areaResultados.setEditable(false);
        areaResultados.setBackground(new java.awt.Color(242, 242, 242));
        areaResultados.setColumns(20);
        areaResultados.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        areaResultados.setRows(3);
        jScrollPane1.setViewportView(areaResultados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtContraseñaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCambiarContraseña))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContraseñaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCambiarContraseña)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
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

    private void btnCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {
        FormularioCambiarContraseña formularioCambio = new FormularioCambiarContraseña();
        formularioCambio.setVisible(true);
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreUsuario = txtUsuarioLogin.getText();
        String contraseña = new String(txtContraseñaLogin.getPassword());

        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            areaResultados.setText("Error: Ingresa usuario y contraseña");
            return;
        }

        GestionUsuarios gestion = new GestionUsuarios();
        Usuario usuario = gestion.obtenerUsuario(nombreUsuario);

        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            areaResultados.setText("Login exitoso. Bienvenido " + usuario.getNombreUsuario());

            if (usuario.getPrivilegio().equals("admin")) {
                menuAdmin ventana = new menuAdmin();
                ventana.setVisible(true);
                this.dispose();
            } else if (usuario.getPrivilegio().equals("docente") || usuario.getPrivilegio().equals("estudiante")) {
                menuVista menu = new menuVista();
                menu.setVisible(true);
                this.dispose();
            }
        } else {
            areaResultados.setText("Usuario o contraseña incorrectos");
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

        java.awt.EventQueue.invokeLater(() -> new FormularioLogin().setVisible(true));
    }

    private javax.swing.JTextArea areaResultados;
    private javax.swing.JButton btnCambiarContraseña;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField txtContraseñaLogin;
    private javax.swing.JTextField txtUsuarioLogin;
}