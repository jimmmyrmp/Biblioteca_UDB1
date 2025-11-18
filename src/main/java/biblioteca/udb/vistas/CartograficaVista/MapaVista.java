package biblioteca.udb.vistas.CartograficaVista;

import biblioteca.udb.ejemplares.Cartografia.Mapa;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapaVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtRegion, txtAño, txtUbicacion;
    private JTextField txtEscala, txtEditorial, txtAñoEdicion, txtDimensiones;
    private JComboBox<String> cmbTipoMapa;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public MapaVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Mapa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR MAPA");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(11, 2, 10, 10));

        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Región:", txtRegion = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());
        addLabelAndField(formPanel, "Escala:", txtEscala = new JTextField());
        addLabelAndField(formPanel, "Editorial:", txtEditorial = new JTextField());
        addLabelAndField(formPanel, "Año de Edición:", txtAñoEdicion = new JTextField());
        addLabelAndField(formPanel, "Dimensiones:", txtDimensiones = new JTextField());

        formPanel.add(new JLabel("Tipo de Mapa:"));
        String[] tiposMapa = {
                "FISICO", "POLITICO", "TOPOGRAFICO", "TURISTICO", "CLIMATICO",
                "GEOLOGICO", "HIDROGRAFICO", "URBANO", "RUTAS", "HISTORICO", "OTRO"
        };
        cmbTipoMapa = new JComboBox<>(tiposMapa);
        cmbTipoMapa.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbTipoMapa);

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
            guardarMapa();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarMapa() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String region = txtRegion.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String escala = txtEscala.getText();
            String tipoMapa = (String) cmbTipoMapa.getSelectedItem();
            String editorial = txtEditorial.getText();
            int añoEdicion = Integer.parseInt(txtAñoEdicion.getText());
            String dimensiones = txtDimensiones.getText();

            if (codigo.isEmpty() || titulo.isEmpty() || region.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Región son obligatorios");
                return;
            }

            if (añoEdicion <= 0) {
                JOptionPane.showMessageDialog(this, "El año de edición debe ser válido");
                return;
            }

            Mapa nuevoMapa = new Mapa(codigo, titulo, region, año, ubicacion,
                    escala, tipoMapa, editorial, añoEdicion, dimensiones);

            boolean exito = catalogo.agregarEjemplar(nuevoMapa);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Mapa guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el Mapa");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año y Año de Edición deben ser números válidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtRegion.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtEscala.setText("");
        txtEditorial.setText("");
        txtAñoEdicion.setText("");
        txtDimensiones.setText("");
        cmbTipoMapa.setSelectedIndex(0);
    }
}