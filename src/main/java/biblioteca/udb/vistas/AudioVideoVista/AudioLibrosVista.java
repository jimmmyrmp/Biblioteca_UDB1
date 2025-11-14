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
    private JButton btnGuardar;

    public AudioLibrosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Audio Libro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(11, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de Audio Libro
        add(new JLabel("Narrador:"));
        txtNarrador = new JTextField();
        add(txtNarrador);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Duración (minutos):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Formato de Audio:"));
        String[] formatos = {"MP3", "WAV", "AAC", "FLAC", "M4A", "OTRO"};
        cmbFormatoAudio = new JComboBox<>(formatos);
        add(cmbFormatoAudio);

        add(new JLabel("Idioma:"));
        String[] idiomas = {"ESPAÑOL", "INGLÉS", "FRANCÉS", "ALEMÁN", "ITALIANO", "PORTUGUÉS", "OTRO"};
        cmbIdioma = new JComboBox<>(idiomas);
        add(cmbIdioma);

        // Botón guardar
        btnGuardar = new JButton("Guardar Audio Libro");
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(this);
    }

    private void configurarVentana() {
        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarAudioLibro();
        }
    }

    private void guardarAudioLibro() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            // Obtener datos específicos
            String narrador = txtNarrador.getText();
            String editorial = txtEditorial.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String formatoAudio = (String) cmbFormatoAudio.getSelectedItem();
            String idioma = (String) cmbIdioma.getSelectedItem();

            // Validaciones
            if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Autor son obligatorios");
                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0 minutos");
                return;
            }

            // Crear objeto AudioLibro
            AudioLibro nuevoAudioLibro = new AudioLibro(codigo, titulo, autor, año, ubicacion,
                    narrador, editorial, duracion, formatoAudio, idioma);

            // Guardar en base de datos
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