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
        btnEjemplares = new JButton("Consultar ejemplares");


        btnPrestamos.addActionListener(this);
        btnEjemplares.addActionListener(this);

        add(btnPrestamos);
        add(btnEjemplares);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrestamos) {
            new FormularioPrestamos().setVisible(true);
        }
        else if (e.getSource() == btnEjemplares) {
            new ConsultaEjemplaresFORM().setVisible(true);
        }
    }
}
