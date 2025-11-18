package biblioteca.udb.vistas.AudioVideoVista;

import biblioteca.udb.ejemplares.audio_video.DVD;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DVDVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtDirector, txtAño, txtUbicacion;
    private JTextField txtProductora, txtDuracion;
    private JComboBox<String> cmbGenero, cmbFormato;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public DVDVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar DVD");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR DVD");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Director:", txtDirector = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de DVD
        addLabelAndField(formPanel, "Productora:", txtProductora = new JTextField());
        addLabelAndField(formPanel, "Duración (minutos):", txtDuracion = new JTextField());

        formPanel.add(new JLabel("Género:"));
        String[] generos = {
                "ACCION", "COMEDIA", "DRAMA", "TERROR", "CIENCIA_FICCION",
                "ANIMACION", "DOCUMENTAL", "ROMANCE", "SUSPENSO", "FANTASIA", "OTRO"
        };
        cmbGenero = new JComboBox<>(generos);
        cmbGenero.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbGenero);

        formPanel.add(new JLabel("Formato:"));
        String[] formatos = {"DVD", "BLU_RAY", "HD_DVD"};
        cmbFormato = new JComboBox<>(formatos);
        cmbFormato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbFormato);

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
            guardarDVD();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarDVD() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String director = txtDirector.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String productora = txtProductora.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String genero = (String) cmbGenero.getSelectedItem();
            String formato = (String) cmbFormato.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty() || director.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Director son obligatorios");
                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0 minutos");
                return;
            }

            DVD nuevoDVD = new DVD(codigo, titulo, director, año, ubicacion,
                    productora, duracion, genero, formato);

            boolean exito = catalogo.agregarEjemplar(nuevoDVD);

            if (exito) {
                JOptionPane.showMessageDialog(this, "DVD guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el DVD");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año y Duración deben ser números válidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtProductora.setText("");
        txtDuracion.setText("");
        cmbGenero.setSelectedIndex(0);
        cmbFormato.setSelectedIndex(0);
    }
}