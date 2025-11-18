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
    private JButton btnGuardar;

    public InformesTecnicosVista() {
        this.catalogo = new catalogo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        setTitle("Ingresar Informe Técnico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 10, 10));

        // Campos básicos
        add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Institución:"));
        txtInstitucion = new JTextField();
        add(txtInstitucion);

        add(new JLabel("Año:"));
        txtAño = new JTextField();
        add(txtAño);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // Campos específicos de Informe Técnico
        add(new JLabel("Área Técnica:"));
        txtAreaTecnica = new JTextField();
        add(txtAreaTecnica);

        add(new JLabel("Número de Informe:"));
        txtNumeroInforme = new JTextField();
        add(txtNumeroInforme);

        add(new JLabel("Autores:"));
        txtAutores = new JTextField();
        add(txtAutores);

        add(new JLabel("Departamento:"));
        txtDepartamento = new JTextField();
        add(txtDepartamento);

        add(new JLabel("Número de Páginas:"));
        txtNumeroPaginas = new JTextField();
        add(txtNumeroPaginas);

        add(new JLabel("Tipo de Informe:"));
        String[] tiposInforme = {
                "FINAL", "PRELIMINAR", "AVANCE", "INTERMEDIO",
                "EVALUACION", "AUDITORIA", "INVESTIGACION", "OTRO"
        };
        cmbTipoInforme = new JComboBox<>(tiposInforme);
        add(cmbTipoInforme);

        // Botón guardar
        btnGuardar = new JButton("Guardar Informe Técnico");
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
            guardarInformeTecnico();
        }
    }

    private void guardarInformeTecnico() {
        try {
            // Obtener datos básicos
            String codigo = txtCodigo.getText();
            String titulo = txtTitulo.getText();
            String institucion = txtInstitucion.getText();
            int año = Integer.parseInt(txtAño.getText());
            String ubicacion = txtUbicacion.getText();

            // Obtener datos específicos
            String areaTecnica = txtAreaTecnica.getText();
            String numeroInforme = txtNumeroInforme.getText();
            String autores = txtAutores.getText();
            String departamento = txtDepartamento.getText();
            int numeroPaginas = Integer.parseInt(txtNumeroPaginas.getText());
            String tipoInforme = (String) cmbTipoInforme.getSelectedItem();

            // Validaciones
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