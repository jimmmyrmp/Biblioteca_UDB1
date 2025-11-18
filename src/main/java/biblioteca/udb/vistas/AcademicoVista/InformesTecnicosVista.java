package biblioteca.udb.vistas.AcademicoVista;

import biblioteca.udb.ejemplares.academico.InformeTecnico;
import biblioteca.udb.ejemplares.catalogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformesTecnicosVista extends JFrame implements ActionListener {
    private catalogo catalogo;
    private JTextField txtCodigo, txtTitulo, txtInstitucion, txtAño, txtUbicacion;
    private JTextField txtAreaTecnica, txtNumeroInforme, txtAutores, txtDepartamento, txtNumeroPaginas;
    private JComboBox<String> cmbTipoInforme;
    private JButton btnGuardar, btnCancelar;
    private JPanel mainPanel;

    public InformesTecnicosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Informe Técnico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("INGRESAR INFORME TÉCNICO");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setLayout(new GridLayout(12, 2, 10, 10));

        // Campos básicos
        addLabelAndField(formPanel, "Código:", txtCodigo = new JTextField());
        addLabelAndField(formPanel, "Título:", txtTitulo = new JTextField());
        addLabelAndField(formPanel, "Institución:", txtInstitucion = new JTextField());
        addLabelAndField(formPanel, "Año:", txtAño = new JTextField());
        addLabelAndField(formPanel, "Ubicación:", txtUbicacion = new JTextField());

        // Campos específicos de Informe Técnico
        addLabelAndField(formPanel, "Área Técnica:", txtAreaTecnica = new JTextField());
        addLabelAndField(formPanel, "Número de Informe:", txtNumeroInforme = new JTextField());
        addLabelAndField(formPanel, "Autores:", txtAutores = new JTextField());
        addLabelAndField(formPanel, "Departamento:", txtDepartamento = new JTextField());
        addLabelAndField(formPanel, "Número de Páginas:", txtNumeroPaginas = new JTextField());

        formPanel.add(new JLabel("Tipo de Informe:"));
        String[] tiposInforme = {
                "FINAL", "PRELIMINAR", "AVANCE", "INTERMEDIO",
                "EVALUACION", "AUDITORIA", "INVESTIGACION", "OTRO"
        };
        cmbTipoInforme = new JComboBox<>(tiposInforme);
        cmbTipoInforme.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(cmbTipoInforme);

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
            guardarInformeTecnico();
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
        }
    }

    private void guardarInformeTecnico() {
        try {
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String institucion = txtInstitucion.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            String areaTecnica = txtAreaTecnica.getText();
            String numeroInforme = txtNumeroInforme.getText();
            String autores = txtAutores.getText();
            String departamento = txtDepartamento.getText();
            int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
            String tipoInforme = (String) cmbTipoInforme.getSelectedItem();

            if (codigo.isEmpty() || titulo.isEmpty() || institucion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Código, Título e Institución son obligatorios");
                return;
            }

            if (numeroPaginas <= 0) {
                JOptionPane.showMessageDialog(this, "El número de páginas debe ser mayor a 0");
                return;
            }

            InformeTecnico nuevoInforme = new InformeTecnico(codigo, titulo, institucion, año, ubicacion,
                    areaTecnica, numeroInforme, autores, departamento,
                    numeroPaginas, tipoInforme);

            boolean exito = catalogo.agregarEjemplar(nuevoInforme);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Informe Técnico guardado exitosamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el Informe Técnico");
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
        txtInstitucion.setText("");
        txtAño.setText("");
        txtUbicacion.setText("");
        txtAreaTecnica.setText("");
        txtNumeroInforme.setText("");
        txtAutores.setText("");
        txtDepartamento.setText("");
        txtNumeroPaginas.setText("");
        cmbTipoInforme.setSelectedIndex(0);
    }
}