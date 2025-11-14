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
    private JButton btnGuardar;

    public AtlasVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Atlas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Tema:"));
        txtTema = new JTextField();
        add(txtTema);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Número de Páginas:"));
        txtNumeroPaginas = new JTextField();
        add(txtNumeroPaginas);

        add(new JLabel("Dimensiones:"));
        txtDimensiones = new JTextField();
        add(txtDimensiones);

        add(new JLabel("Tipo de Encuadernación:"));
        String[] tiposEncuadernacion = {
                "TAPA_DURA", "TAPA_BLANDA", "ESPIRAL", "GRAPADO", "OTRO"
        };
        cmbTipoEncuadernacion = new JComboBox<>(tiposEncuadernacion);
        add(cmbTipoEncuadernacion);

        add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        add(txtIsbn);

        btnGuardar = new JButton("Guardar Atlas");
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
            guardarAtlas();
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