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
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public FolletosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Folleto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR FOLLETO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(9, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Autor/Editor:", txtAutor = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de Folleto
        addLabelAndField(formPanel, "Tema Principal:", txtTema = new JTextField());
        addLabelAndField(formPanel, "Institución:", txtInstitucion = new JTextField());
        addLabelAndField(formPanel, "Número de Páginas:", txtNumeroPaginas = new JTextField());

        formPanel.add(new JLabel("Tipo de Folleto:"));
        cmbTipoFolleto = new JComboBox<>(new String[]{
                "INFORMATIVO", "TURISTICO", "EDUCATIVO", "PROMOCIONAL", "SALUD", "CULTURAL"
        });
        cmbTipoFolleto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbTipoFolleto);

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
            guardarFolleto();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
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