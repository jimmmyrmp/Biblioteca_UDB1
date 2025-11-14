package biblioteca.udb.vistas;

import biblioteca.udb.vistas.AudioVideoVista.*;
import biblioteca.udb.vistas.textoVista.*;
import biblioteca.udb.vistas.CartograficaVista.*;
import biblioteca.udb.vistas.DigitalVista.*;
import biblioteca.udb.vistas.AcademicoVista.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bibliotecaVista extends JFrame implements ActionListener {
    private JButton btnLibro, btnRevista, btnTesis, btnPeriodico, btnFolleto, btnCD, btnDVD, btnAudioLibros, btnMapas, btnAtlas, btnEbooks, btnInformesTecnicos;

    public bibliotecaVista() {
        setTitle("Sistema Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        setSize(1000, 1000);

        add(new JLabel("Seleccione tipo de ejemplar:"));

        btnLibro = new JButton("Libro");
        btnRevista = new JButton("Revista");
        btnTesis = new JButton("Tesis");
        btnPeriodico = new JButton("Periodico");
        btnFolleto = new JButton("Folleto");
        btnCD = new JButton("CD");
        btnDVD = new JButton("DVD");
        btnAudioLibros = new JButton("AudioLibros");
        btnMapas = new JButton("Mapas");
        btnAtlas = new JButton("Atlas");
        btnEbooks = new JButton("Ebooks");
        btnInformesTecnicos = new JButton("Informes Tecnicos");

        btnLibro.addActionListener(this);
        btnRevista.addActionListener(this);
        btnTesis.addActionListener(this);
        btnPeriodico.addActionListener(this);
        btnFolleto.addActionListener(this);
        btnCD.addActionListener(this);
        btnDVD.addActionListener(this);
        btnAudioLibros.addActionListener(this);
        btnMapas.addActionListener(this);
        btnAtlas.addActionListener(this);
        btnEbooks.addActionListener(this);
        btnInformesTecnicos.addActionListener(this);

        add(btnLibro);
        add(btnRevista);
        add(btnTesis);
        add(btnPeriodico);
        add(btnFolleto);
        add(btnCD);
        add(btnDVD);
        add(btnAudioLibros);
        add(btnMapas);
        add(btnAtlas);
        add(btnEbooks);
        add(btnInformesTecnicos);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLibro) {
            new LibroVista();
        } else if (e.getSource() == btnRevista) {
            new RevistasVista();
        } else if (e.getSource() == btnTesis) {
            new TesisVista();
        } else if (e.getSource() == btnPeriodico) {
            new PeriodicosVista();
        } else if (e.getSource() == btnFolleto) {
            new FolletosVista();
        } else if (e.getSource() == btnCD) {
            new CDVista();
        } else if (e.getSource() == btnDVD) {
            new DVDVista();
        } else if (e.getSource() == btnAudioLibros) {
            new AudioLibrosVista();
        } else if (e.getSource() == btnMapas) {
            new MapaVista();
        } else if (e.getSource() == btnAtlas) {
            new AtlasVista();
        } else if (e.getSource() == btnEbooks) {
            new EBookVista();
        } else if (e.getSource() == btnInformesTecnicos) {
            new InformesTecnicosVista();
        }
    }

    public static void main(String[] args) {
        new bibliotecaVista();
    }
}