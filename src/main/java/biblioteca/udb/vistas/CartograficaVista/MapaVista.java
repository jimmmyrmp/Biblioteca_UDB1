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
    private JButton btnGuardar;

    public MapaVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Mapa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(11, 2, 10, 10));

        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Región:"));
        txtRegion = new JTextField();
        add(txtRegion);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de Mapa
        add(new JLabel("Escala:"));
        txtEscala = new JTextField();
        add(txtEscala);

        add(new JLabel("Tipo de Mapa:"));
        String[] tiposMapa = {
                "FISICO", "POLITICO", "TOPOGRAFICO", "TURISTICO", "CLIMATICO",
                "GEOLOGICO", "HIDROGRAFICO", "URBANO", "RUTAS", "HISTORICO", "OTRO"
        };
        cmbTipoMapa = new JComboBox<>(tiposMapa);
        add(cmbTipoMapa);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Año de Edición:"));
        txtAñoEdicion = new JTextField();
        add(txtAñoEdicion);

        add(new JLabel("Dimensiones:"));
        txtDimensiones = new JTextField();
        add(txtDimensiones);

        btnGuardar = new JButton("Guardar Mapa");
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
            guardarMapa();
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
