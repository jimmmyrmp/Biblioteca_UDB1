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
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public RevistasVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Revista");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR REVISTA");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));

        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Autor:", txtAutor = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());
        addLabelAndField(formPanel, "ISSN:", txtIssn = new JTextField());
        addLabelAndField(formPanel, "Número:", txtNumero = new JTextField());
        addLabelAndField(formPanel, "Volumen:", txtVolumen = new JTextField());
        addLabelAndField(formPanel, "Periodicidad:", txtPeriodicidad = new JTextField());

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
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarRevista();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
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

            if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título y Autor son obligatorios");
                return;
            }

            Revistas nuevaRevista = new Revistas(codigo, titulo, autor, año, ubicacion, issn, numero, volumen, periodicidad);

            boolean exito = catalogo.agregarEjemplar(nuevaRevista);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Revista guardada exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la revista");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Año, Número y Volumen deben ser números válidos");
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
        txtIssn.setText("");
        txtNumero.setText("");
        txtVolumen.setText("");
        txtPeriodicidad.setText("");
    }
}