package biblioteca.udb.vistas.textoVista;

import biblioteca.udb.ejemplares.textos.periodicos;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PeriodicosVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtAño, txtUbicacion;
    private JTextField txtSeccion, txtNumeroPaginas;
    private JComboBox<String> cmbFrecuencia;
    private JButton btnGuardar;
    private JFormattedTextField txtFechaPublicacion;

    public PeriodicosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Periódico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor/Editor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de Periódico
        add(new JLabel("Fecha Publicación:"));
        txtFechaPublicacion = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        txtFechaPublicacion.setValue(new Date());
        add(txtFechaPublicacion);

        add(new JLabel("Sección:"));
        txtSeccion = new JTextField();
        add(txtSeccion);

        add(new JLabel("Número de Páginas:"));
        txtNumeroPaginas = new JTextField();
        add(txtNumeroPaginas);

        add(new JLabel("Frecuencia:"));
        cmbFrecuencia = new JComboBox<>(new String[]{"DIARIO", "SEMANAL", "MENSUAL", "QUINCENAL"});
        add(cmbFrecuencia);

        // Botón guardar
        btnGuardar = new JButton("Guardar Periódico");
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(this);
    }

    private void configurarVentana() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarPeriodico();
        }
    }

    private void guardarPeriodico() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            Date fechaPublicacion = (Date) txtFechaPublicacion.getValue();
            String seccion = txtSeccion.getText();
            int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
            String frecuencia = (String) cmbFrecuencia.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código y Título son obligatorios");
                return;
            }

            periodicos nuevoPeriodico = new periodicos(codigo, titulo, autor, año, ubicacion,
                    fechaPublicacion, seccion, numeroPaginas, frecuencia);

            // Guardar en base de datos
            boolean exito = catalogo.agregarEjemplar(nuevoPeriodico);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Periódico guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el periódico");
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
        txtAutor.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtFechaPublicacion.setValue(new Date());
        txtSeccion.setText("");
        txtNumeroPaginas.setText("");
        cmbFrecuencia.setSelectedIndex(0);
    }
}