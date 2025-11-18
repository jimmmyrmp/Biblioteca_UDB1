package biblioteca.udb.vistas;

import biblioteca.udb.menu.menuAdmin;
import biblioteca.udb.vistas.AcademicoVista.InformesTecnicosVista;
import biblioteca.udb.vistas.AudioVideoVista.AudioLibrosVista;
import biblioteca.udb.vistas.AudioVideoVista.CDVista;
import biblioteca.udb.vistas.AudioVideoVista.DVDVista;
import biblioteca.udb.vistas.CartograficaVista.AtlasVista;
import biblioteca.udb.vistas.CartograficaVista.MapaVista;
import biblioteca.udb.vistas.DigitalVista.EBookVista;
import biblioteca.udb.vistas.textoVista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bibliotecaVista extends JFrame implements ActionListener {
    private JButton btnLibro, btnRevista, btnTesis, btnPeriodico, btnFolleto,
            btnCD, btnDVD, btnAudioLibros, btnMapas, btnAtlas,
            btnEbooks, btnInformesTecnicos, btnRegresar;
    private JPanel mainPanel;

    public bibliotecaVista() {
        setTitle("Sistema Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("SELECCIONE TIPO DE EJEMPLAR");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new GridLayout(6, 2, 15, 15));

        btnLibro = createButton("LIBRO");
        btnRevista = createButton("REVISTA");
        btnTesis = createButton("TESIS");
        btnPeriodico = createButton("PERIÓDICO");
        btnFolleto = createButton("FOLLETO");
        btnCD = createButton("CD");
        btnDVD = createButton("DVD");
        btnAudioLibros = createButton("AUDIOLIBROS");
        btnMapas = createButton("MAPAS");
        btnAtlas = createButton("ATLAS");
        btnEbooks = createButton("E-BOOKS");
        btnInformesTecnicos = createButton("INFORMES TÉCNICOS");

        buttonPanel.add(btnLibro);
        buttonPanel.add(btnRevista);
        buttonPanel.add(btnTesis);
        buttonPanel.add(btnPeriodico);
        buttonPanel.add(btnFolleto);
        buttonPanel.add(btnCD);
        buttonPanel.add(btnDVD);
        buttonPanel.add(btnAudioLibros);
        buttonPanel.add(btnMapas);
        buttonPanel.add(btnAtlas);
        buttonPanel.add(btnEbooks);
        buttonPanel.add(btnInformesTecnicos);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setLayout(new FlowLayout());

        btnRegresar = new JButton("REGRESAR AL MENÚ");
        btnRegresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegresar.setBackground(new Color(153, 153, 153));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setPreferredSize(new Dimension(200, 40));
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(this);

        bottomPanel.add(btnRegresar);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(new Color(51, 102, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 50));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLibro) {
            new LibroVista();
            this.dispose();
        } else if (e.getSource() == btnRevista) {
            new RevistasVista();
            this.dispose();
        } else if (e.getSource() == btnTesis) {
            new TesisVista();
            this.dispose();
        } else if (e.getSource() == btnPeriodico) {
            new PeriodicosVista();
            this.dispose();
        } else if (e.getSource() == btnFolleto) {
            new FolletosVista();
            this.dispose();
        } else if (e.getSource() == btnCD) {
            new CDVista();
            this.dispose();
        } else if (e.getSource() == btnDVD) {
            new DVDVista();
            this.dispose();
        } else if (e.getSource() == btnAudioLibros) {
            new AudioLibrosVista();
            this.dispose();
        } else if (e.getSource() == btnMapas) {
            new MapaVista();
            this.dispose();
        } else if (e.getSource() == btnAtlas) {
            new AtlasVista();
            this.dispose();
        } else if (e.getSource() == btnEbooks) {
            new EBookVista();
            this.dispose();
        } else if (e.getSource() == btnInformesTecnicos) {
            new InformesTecnicosVista();
            this.dispose();
        } else if (e.getSource() == btnRegresar) {
            new menuAdmin();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new bibliotecaVista();
    }
}