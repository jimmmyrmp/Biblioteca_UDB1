package biblioteca.udb.vistas.textoVista;

import biblioteca.udb.ejemplares.textos.Revistas;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevistasVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtUbicacion, txtAño, txtIssn;
    private JTextField txtNumero, txtVolumen, txtPeriodicidad;
    private JButton btnGuardar;

    public RevistasVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Revista");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2, 10, 10));

        add(new JLabel("Codigo:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Titulo:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicacion:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        add(new JLabel("Issn:"));
        txtIssn = new JTextField();
        add(txtIssn);

        add(new JLabel("Numero:"));
        txtNumero = new JTextField();
        add(txtNumero);

        add(new JLabel("Volumen:"));
        txtVolumen = new JTextField();
        add(txtVolumen);

        add(new JLabel("Periodicidad:"));
        txtPeriodicidad = new JTextField();
        add(txtPeriodicidad);

        btnGuardar = new JButton("Guardar Tesis");
        add(new JLabel(""));
        add(btnGuardar);

        btnGuardar.addActionListener(this);
    }

    private void configurarVentana() {
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarRevista();
        }
    }

    private void guardarRevista() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();
            String issn = txtIssn.getText();
            int numero = Integer.parseInt(txtNumero.getText());
            int volumen = Integer.parseInt(txtVolumen.getText());
            String periodicidad = txtPeriodicidad.getText();

            Revistas nuevaRevista = new Revistas(codigo, titulo, autor, año, ubicacion, issn, numero, volumen, periodicidad);

            boolean exito = catalogo.agregarEjemplar(nuevaRevista);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Revista guardada");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}