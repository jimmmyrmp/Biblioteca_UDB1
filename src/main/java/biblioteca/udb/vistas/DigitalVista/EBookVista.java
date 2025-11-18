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
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public EBookVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Ebook");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR EBOOK");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(12, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Autor:", txtAutor = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de Ebook
        addLabelAndField(formPanel, "Editorial:", txtEditorial = new JTextField());
        addLabelAndField(formPanel, "ISBN:", txtIsbn = new JTextField());
        addLabelAndField(formPanel, "Tamaño (KB):", txtTamaño = new JTextField());
        addLabelAndField(formPanel, "URL de Acceso:", txtUrlAcceso = new JTextField());

        formPanel.add(new JLabel("Formato:"));
        String[] formatos = {"PDF", "EPUB", "MOBI", "AZW", "DOC", "DOCX", "OTRO"};
        cmbFormato = new JComboBox<>(formatos);
        cmbFormato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbFormato);

        formPanel.add(new JLabel("Plataforma:"));
        String[] plataformas = {
                "KINDLE", "GOOGLE_BOOKS", "APPLE_BOOKS", "KOBO",
                "ADOBE_DIGITAL_EDITIONS", "OTRO"
        };
        cmbPlataforma = new JComboBox<>(plataformas);
        cmbPlataforma.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbPlataforma);

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
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarEbook();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarEbook() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String editorial = txtEditorial.getText();
            String isbn = txtIsbn.getText();
            String formato = (String) cmbFormato.getSelectedItem();
            int tamaño = Integer.parseInt(txtTamaño.getText());
            String plataforma = (String) cmbPlataforma.getSelectedItem();
            String urlAcceso = txtUrlAcceso.getText();

            if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Autor son obligatorios");
                return;
            }

            if (tamaño <= 0) {
                JOptionPane.showMessageDialog(this, "El tamaño debe ser mayor a 0 KB");
                return;
            }

            Ebook nuevoEbook = new Ebook(codigo, titulo, autor, año, ubicacion,
                    editorial, isbn, formato, tamaño,
                    plataforma, urlAcceso);

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