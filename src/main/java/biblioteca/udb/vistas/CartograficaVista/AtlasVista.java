package biblioteca.udb.vistas.CartograficaVista;

import biblioteca.udb.ejemplares.Cartografia.Atlas;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtlasVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtTema, txtAño, txtUbicacion;
    private JTextField txtEditorial, txtNumeroPaginas, txtDimensiones, txtIsbn;
    private JComboBox<String> cmbTipoEncuadernacion;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public AtlasVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Atlas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR ATLAS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(12, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Tema:", txtTema = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());
        addLabelAndField(formPanel, "Editorial:", txtEditorial = new JTextField());
        addLabelAndField(formPanel, "Número de Páginas:", txtNumeroPaginas = new JTextField());
        addLabelAndField(formPanel, "Dimensiones:", txtDimensiones = new JTextField());

        formPanel.add(new JLabel("Tipo de Encuadernación:"));
        String[] tiposEncuadernacion = {
                "TAPA_DURA", "TAPA_BLANDA", "ESPIRAL", "GRAPADO", "OTRO"
        };
        cmbTipoEncuadernacion = new JComboBox<>(tiposEncuadernacion);
        cmbTipoEncuadernacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbTipoEncuadernacion);

        addLabelAndField(formPanel, "ISBN:", txtIsbn = new JTextField());

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
            guardarAtlas();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarAtlas() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String tema = txtTema.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String editorial = txtEditorial.getText();
            int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
            String dimensiones = txtDimensiones.getText();
            String tipoEncuadernacion = (String) cmbTipoEncuadernacion.getSelectedItem();
            String isbn = txtIsbn.getText();

            if (codigo.isEmpty() || titulo.isEmpty() || tema.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Tema son obligatorios");
                return;
            }

            if (numeroPaginas <= 0) {
                JOptionPane.showMessageDialog(this, "El número de páginas debe ser mayor a 0");
                return;
            }

            Atlas nuevoAtlas = new Atlas(codigo, titulo, tema, año, ubicacion,
                    editorial, numeroPaginas, dimensiones,
                    tipoEncuadernacion, isbn);

            boolean exito = catalogo.agregarEjemplar(nuevoAtlas);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Atlas guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el Atlas");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año y Número de Páginas deben ser números válidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtTema.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtEditorial.setText("");
        txtNumeroPaginas.setText("");
        txtDimensiones.setText("");
        txtIsbn.setText("");
        cmbTipoEncuadernacion.setSelectedIndex(0);
    }
}