package biblioteca.udb.vistas.textoVista;

import biblioteca.udb.ejemplares.textos.Folletos;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolletosVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtAño, txtUbicacion;
    private JTextField txtTema, txtInstitucion, txtNumeroPaginas;
    private JComboBox<String> cmbTipoFolleto;
    private JButton btnGuardar;

    public FolletosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Folleto");
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

        // Campos específicos de Folleto
        add(new JLabel("Tema Principal:"));
        txtTema = new JTextField();
        add(txtTema);

        add(new JLabel("Institución:"));
        txtInstitucion = new JTextField();
        add(txtInstitucion);

        add(new JLabel("Número de Páginas:"));
        txtNumeroPaginas = new JTextField();
        add(txtNumeroPaginas);

        add(new JLabel("Tipo de Folleto:"));
        cmbTipoFolleto = new JComboBox<>(new String[]{
                "INFORMATIVO", "TURISTICO", "EDUCATIVO", "PROMOCIONAL", "SALUD", "CULTURAL"
        });
        add(cmbTipoFolleto);

        btnGuardar = new JButton("Guardar Folleto");
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
            guardarFolleto();
        }
    }

    private void guardarFolleto() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String tema = txtTema.getText();
            String institucion = txtInstitucion.getText();
            int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
            String tipoFolleto = (String) cmbTipoFolleto.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty() || tema.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Tema son obligatorios");
                return;
            }

            Folletos nuevoFolleto = new Folletos(codigo, titulo, autor, año, ubicacion,
                    tema, institucion, numeroPaginas, tipoFolleto);

            boolean exito = catalogo.agregarEjemplar(nuevoFolleto);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Folleto guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el folleto");
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
        txtTema.setText("");
        txtInstitucion.setText("");
        txtNumeroPaginas.setText("");
        cmbTipoFolleto.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FolletosVista());
    }
}