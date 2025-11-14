package biblioteca.udb.vistas.textoVista;

import biblioteca.udb.ejemplares.textos.Libros;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibroVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtAutor, txtAño, txtUbicacion;
    private JTextField txtIsbn, txtEditorial, txtPaginas, txtGenero;
    private JButton btnGuardar;

    public LibroVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Libro");
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

        add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        add(txtIsbn);

        add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        add(txtEditorial);

        add(new JLabel("Paginas:"));
        txtPaginas = new JTextField();
        add(txtPaginas);

        add(new JLabel("Genero:"));
        txtGenero = new JTextField();
        add(txtGenero);

        btnGuardar = new JButton("Guardar Libro");
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
            guardarLibro();
        }
    }

    private void guardarLibro() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();
            String isbn = txtIsbn.getText();
            String editorial = txtEditorial.getText();
            int num_Paginas = Integer.parseInt(txtPaginas.getText());
            String genero = txtGenero.getText();

            Libros nuevoLibro = new Libros(codigo, titulo, autor, año, ubicacion, isbn, editorial, num_Paginas, genero);

            boolean exito = catalogo.agregarEjemplar(nuevoLibro);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Libro guardado");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}