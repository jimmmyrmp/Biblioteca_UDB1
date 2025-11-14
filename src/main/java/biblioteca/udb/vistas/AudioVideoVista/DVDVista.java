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
    private JButton btnGuardar;

    public DVDVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar DVD");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Director:"));
        txtDirector = new JTextField();
        add(txtDirector);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de DVD
        add(new JLabel("Productora:"));
        txtProductora = new JTextField();
        add(txtProductora);

        add(new JLabel("Duración (minutos):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Género:"));
        String[] generos = {
                "ACCION", "COMEDIA", "DRAMA", "TERROR", "CIENCIA_FICCION",
                "ANIMACION", "DOCUMENTAL", "ROMANCE", "SUSPENSO", "FANTASIA", "OTRO"
        };
        cmbGenero = new JComboBox<>(generos);
        add(cmbGenero);

        add(new JLabel("Formato:"));
        String[] formatos = {"DVD", "BLU_RAY", "HD_DVD"};
        cmbFormato = new JComboBox<>(formatos);
        add(cmbFormato);

        // Botón guardar
        btnGuardar = new JButton("Guardar DVD");
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
            guardarDVD();
        }
    }

    private void guardarDVD() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String director = txtDirector.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            // Obtener datos específicos
            String productora = txtProductora.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String genero = (String) cmbGenero.getSelectedItem();
            String formato = (String) cmbFormato.getSelectedItem();

            // Validaciones
            if (codigo.isEmpty() || titulo.isEmpty() || director.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Director son obligatorios");
                return;
            }

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0 minutos");
                return;
            }

            // Crear objeto DVD
            DVD nuevoDVD = new DVD(codigo, titulo, director, año, ubicacion,
                    productora, duracion, genero, formato);

            // Guardar en base de datos
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