package biblioteca.udb.vistas.AudioVideoVista;

import biblioteca.udb.ejemplares.audio_video.CD;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtArtista, txtAño, txtUbicacion;
    private JTextField txtDiscografia, txtDuracion, txtNumeroPistas;
    private JComboBox<String> cmbGeneroMusical;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public CDVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar CD Musical");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR CD MUSICAL");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título del Álbum:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Artista/Grupo:", txtArtista = new JTextField());
        addLabelAndField(formPanel, "Año de Lanzamiento:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de CD
        addLabelAndField(formPanel, "Discográfica:", txtDiscografia = new JTextField());
        addLabelAndField(formPanel, "Duración (minutos):", txtDuracion = new JTextField());
        addLabelAndField(formPanel, "Número de Pistas:", txtNumeroPistas = new JTextField());

        formPanel.add(new JLabel("Género Musical:"));
        String[] generos = {
                "SALSA", "MERENGUE", "ROCK", "POP", "BALADA", "ROMANTICA",
                "FOLKLORE", "CUMBIA", "REGGAETON", "HIP HOP", "JAZZ", "CLASICA",
                "ELECTRONICA", "REGGAE", "RANCHERA", "VALLENATO", "BACHATA", "OTRO"
        };
        cmbGeneroMusical = new JComboBox<>(generos);
        cmbGeneroMusical.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbGeneroMusical);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(153, 153, 153));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setPreferredSize(new Dimension(120, 40));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(this);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(51, 102, 153));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setPreferredSize(new Dimension(120, 40));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this);

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(label);

        textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(textField);
    }

    private void configurarVentana() {
        setSize(500, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarCD();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarCD() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String artista = txtArtista.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String discografia = txtDiscografia.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            int numeroPistas = Integer.parseInt(txtNumeroPistas.getText());
            String generoMusical = (String) cmbGeneroMusical.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty() || artista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Artista son obligatorios");
                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0 minutos");
                return;
            }

            if (numeroPistas <= 0) {
                JOptionPane.showMessageDialog(this, "El número de pistas debe ser mayor a 0");
                return;
            }

            CD nuevoCD = new CD(codigo, titulo, artista, año, ubicacion,
                    discografia, duracion, generoMusical, numeroPistas);

            boolean exito = catalogo.agregarEjemplar(nuevoCD);

            if (exito) {
                JOptionPane.showMessageDialog(this, "CD guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el CD");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año, Duración y Número de Pistas deben ser números válidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtArtista.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtDiscografia.setText("");
        txtDuracion.setText("");
        txtNumeroPistas.setText("");
        cmbGeneroMusical.setSelectedIndex(0);
    }
}