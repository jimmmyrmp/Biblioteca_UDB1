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

public class menuAdmin extends JFrame implements ActionListener {
    private JButton btnBiblioteca, btnAgregarUser, btnDevoluciones, btnControlPrestamos;

    public menuAdmin() {
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        setSize(500, 500);

        add(new JLabel("Seleccione que desea realizar:"));

        btnBiblioteca = new JButton("Agregar ejemplar");
        btnAgregarUser = new JButton("Agregar Usuario");
        btnDevoluciones = new JButton("Devoluciones");
        btnControlPrestamos = new JButton("Control Prestamos");


        btnBiblioteca.addActionListener(this);
        btnAgregarUser.addActionListener(this);
        btnDevoluciones.addActionListener(this);
        btnControlPrestamos.addActionListener(this);


        add(btnBiblioteca);
        add(btnAgregarUser);
        add(btnDevoluciones);
        add(btnControlPrestamos);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBiblioteca) {
            new bibliotecaVista().setVisible(true);
        }
        else if (e.getSource() == btnAgregarUser) {
            new FormularioUsuarios().setVisible(true);
        }
        else if (e.getSource() == btnDevoluciones) {
            new Devoluciones().setVisible(true);
        }
        else if (e.getSource() == btnControlPrestamos) {
            new FormularioConfiguracionEJEM().setVisible(true);
        }
    }
}

