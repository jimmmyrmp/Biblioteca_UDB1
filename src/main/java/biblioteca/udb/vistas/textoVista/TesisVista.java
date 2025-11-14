package biblioteca.udb.vistas.textoVista;

import biblioteca.udb.ejemplares.textos.Tesis;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TesisVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtUbicacion, txtAño, txtCarrera;
    private JTextField txtFacultad, txtDirector, txtGradoAcademico;
    private JButton btnGuardar;

    public TesisVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Tesis");
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

        add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        add(txtCarrera);

        add(new JLabel("Facultad:"));
        txtFacultad = new JTextField();
        add(txtFacultad);

        add(new JLabel("Director:"));
        txtDirector = new JTextField();
        add(txtDirector);

        add(new JLabel("Grado Academico:"));
        txtGradoAcademico = new JTextField();
        add(txtGradoAcademico);

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
            guardarTesis();
        }
    }

    private void guardarTesis() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();
            String carrera = txtCarrera.getText();
            String Facultad = txtFacultad.getText();
            String Director = txtDirector.getText();
            String gradoAcademico = txtGradoAcademico.getText();

            Tesis nuevaTesis = new Tesis(codigo, titulo, autor, año, ubicacion, carrera, Facultad, Director, gradoAcademico);

            boolean exito = catalogo.agregarEjemplar(nuevaTesis);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Tesis guardada");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}