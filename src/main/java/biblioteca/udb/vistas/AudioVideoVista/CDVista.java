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
    private JButton btnGuardar;

    public CDVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar CD Musical");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título del Álbum:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Artista/Grupo:"));
        txtArtista = new JTextField();
        add(txtArtista);

        add(new JLabel("Año de Lanzamiento:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de CD
        add(new JLabel("Discográfica:"));
        txtDiscografia = new JTextField();
        add(txtDiscografia);

        add(new JLabel("Duración (minutos):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Número de Pistas:"));
        txtNumeroPistas = new JTextField();
        add(txtNumeroPistas);

        add(new JLabel("Género Musical:"));
        String[] generos = {
                "SALSA", "MERENGUE", "ROCK", "POP", "BALADA", "ROMANTICA",
                "FOLKLORE", "CUMBIA", "REGGAETON", "HIP HOP", "JAZZ", "CLASICA",
                "ELECTRONICA", "REGGAE", "RANCHERA", "VALLENATO", "BACHATA", "OTRO"
        };
        cmbGeneroMusical = new JComboBox<>(generos);
        add(cmbGeneroMusical);

        btnGuardar = new JButton("Guardar CD");
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(this);
    }

    private void configurarVentana() {
        setSize(450, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarCD();
        }
    }

    private void guardarCD() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String artista = txtArtista.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            // Obtener datos específicos
            String discografia = txtDiscografia.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            int numeroPistas = Integer.parseInt(txtNumeroPistas.getText());
            String generoMusical = (String) cmbGeneroMusical.getSelectedItem();

            // Validaciones
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

            // Guardar en base de datos
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