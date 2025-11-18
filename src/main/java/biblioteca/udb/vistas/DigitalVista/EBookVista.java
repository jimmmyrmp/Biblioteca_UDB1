package biblioteca.udb.vistas.DigitalVista;

import biblioteca.udb.ejemplares.digitales.Ebook;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EBookVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtAño, txtUbicacion;
    private JTextField txtEditorial, txtIsbn, txtTamaño, txtUrlAcceso;
    private JComboBox<String> cmbFormato, cmbPlataforma;
    private JButton btnGuardar;

    public EBookVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Ebook");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 10, 10));

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

        // Campos específicos de Ebook
        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        add(txtIsbn);

        add(new JLabel("Formato:"));
        String[] formatos = {"PDF", "EPUB", "MOBI", "AZW", "DOC", "DOCX", "OTRO"};
        cmbFormato = new JComboBox<>(formatos);
        add(cmbFormato);

        add(new JLabel("Tamaño (KB):"));
        txtTamaño = new JTextField();
        add(txtTamaño);

        add(new JLabel("Plataforma:"));
        String[] plataformas = {
                "KINDLE", "GOOGLE_BOOKS", "APPLE_BOOKS", "KOBO",
                "ADOBE_DIGITAL_EDITIONS", "OTRO"
        };
        cmbPlataforma = new JComboBox<>(plataformas);
        add(cmbPlataforma);

        add(new JLabel("URL de Acceso:"));
        txtUrlAcceso = new JTextField();
        add(txtUrlAcceso);

        // Botón guardar
        btnGuardar = new JButton("Guardar Ebook");
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(this);
    }

    private void configurarVentana() {
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarEbook();
        }
    }

    private void guardarEbook() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            // Obtener datos específicos
            String editorial = txtEditorial.getText();
            String isbn = txtIsbn.getText();
            String formato = (String) cmbFormato.getSelectedItem();
            int tamaño = Integer.parseInt(txtTamaño.getText());
            String plataforma = (String) cmbPlataforma.getSelectedItem();
            String urlAcceso = txtUrlAcceso.getText();

            // Validaciones
            if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Autor son obligatorios");
                return;
            }

            if (tamaño <= 0) {
                JOptionPane.showMessageDialog(this, "El tamaño debe ser mayor a 0 KB");
                return;
            }

            // Crear objeto Ebook
            Ebook nuevoEbook = new Ebook(codigo, titulo, autor, año, ubicacion,
                    editorial, isbn, formato, tamaño,
                    plataforma, urlAcceso);

            // Guardar en base de datos
            boolean exito = catalogo.agregarEjemplar(nuevoEbook);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Ebook guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el Ebook");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año y Tamaño deben ser números válidos");
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
        txtEditorial.setText("");
        txtIsbn.setText("");
        txtTamaño.setText("");
        txtUrlAcceso.setText("");
        cmbFormato.setSelectedIndex(0);
        cmbPlataforma.setSelectedIndex(0);
    }
}
