package biblioteca.udb.vistas.AudioVideoVista;

import biblioteca.udb.ejemplares.audio_video.AudioLibro;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioLibrosVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtAño, txtUbicacion;
    private JTextField txtNarrador, txtEditorial, txtDuracion;
    private JComboBox<String> cmbFormatoAudio, cmbIdioma;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public AudioLibrosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Audio Libro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR AUDIO LIBRO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(11, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Autor:", txtAutor = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de Audio Libro
        addLabelAndField(formPanel, "Narrador:", txtNarrador = new JTextField());
        addLabelAndField(formPanel, "Editorial:", txtEditorial = new JTextField());
        addLabelAndField(formPanel, "Duración (minutos):", txtDuracion = new JTextField());

        formPanel.add(new JLabel("Formato de Audio:"));
        String[] formatos = {"MP3", "WAV", "AAC", "FLAC", "M4A", "OTRO"};
        cmbFormatoAudio = new JComboBox<>(formatos);
        cmbFormatoAudio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbFormatoAudio);

        formPanel.add(new JLabel("Idioma:"));
        String[] idiomas = {"ESPAÑOL", "INGLÉS", "FRANCÉS", "ALEMÁN", "ITALIANO", "PORTUGUÉS", "OTRO"};
        cmbIdioma = new JComboBox<>(idiomas);
        cmbIdioma.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbIdioma);

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
            guardarAudioLibro();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarAudioLibro() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String narrador = txtNarrador.getText();
            String editorial = txtEditorial.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String formatoAudio = (String) cmbFormatoAudio.getSelectedItem();
            String idioma = (String) cmbIdioma.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Autor son obligatorios");
                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0 minutos");
                return;
            }

            AudioLibro nuevoAudioLibro = new AudioLibro(codigo, titulo, autor, año, ubicacion,
                    narrador, editorial, duracion, formatoAudio, idioma);

            boolean exito = catalogo.agregarEjemplar(nuevoAudioLibro);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Audio Libro guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el Audio Libro");
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
        txtAutor.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtNarrador.setText("");
        txtEditorial.setText("");
        txtDuracion.setText("");
        cmbFormatoAudio.setSelectedIndex(0);
        cmbIdioma.setSelectedIndex(0);
    }
}