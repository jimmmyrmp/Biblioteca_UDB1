package biblioteca.udb.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import biblioteca.udb.FormularioPrestamos;
import biblioteca.udb.vistas.bibliotecaVista;
import biblioteca.udb.FormularioUsuarios;
import biblioteca.udb.ConsultaEjemplaresFORM;
import biblioteca.udb.Devoluciones;
import biblioteca.udb.FormularioConfiguracionEJEM;

public class menuVista extends JFrame implements ActionListener {
    private JButton btnPrestamos, btnBiblioteca, btnAgregarUser, btnEjemplares, btnDevoluciones, btnControlPrestamos;

    public menuVista() {
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        setSize(500, 500);

        add(new JLabel("Seleccione que desea realizar:"));

        btnPrestamos = new JButton("Realizar Prestamos");
        btnBiblioteca = new JButton("Biblioteca");
        btnAgregarUser = new JButton("Agregar Usuario");
        btnEjemplares = new JButton("Consultar ejemplares");
        btnDevoluciones = new JButton("Devoluciones");
        btnControlPrestamos = new JButton("Control Prestamos");


        btnPrestamos.addActionListener(this);
        btnBiblioteca.addActionListener(this);
        btnAgregarUser.addActionListener(this);
        btnEjemplares.addActionListener(this);
        btnDevoluciones.addActionListener(this);
        btnControlPrestamos.addActionListener(this);

        add(btnPrestamos);
        add(btnBiblioteca);
        add(btnAgregarUser);
        add(btnEjemplares);
        add(btnDevoluciones);
        add(btnControlPrestamos);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrestamos) {
            new FormularioPrestamos().setVisible(true);
        }
        else if (e.getSource() == btnBiblioteca) {
            new bibliotecaVista().setVisible(true);
        }
        else if (e.getSource() == btnAgregarUser) {
            new FormularioUsuarios().setVisible(true);
        }
        else if (e.getSource() == btnEjemplares) {
            new ConsultaEjemplaresFORM().setVisible(true);
        }
        else if (e.getSource() == btnDevoluciones) {
            new Devoluciones().setVisible(true);
        }
        else if (e.getSource() == btnControlPrestamos) {
            new FormularioConfiguracionEJEM().setVisible(true);
        }
    }
}
