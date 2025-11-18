package biblioteca.udb.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import biblioteca.udb.FormularioPrestamos;
import biblioteca.udb.ConsultaEjemplaresFORM;

public class menuVista extends JFrame implements ActionListener {
    private JButton btnPrestamos, btnEjemplares;
    private JPanel mainPanel;

    public menuVista() {
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("SELECCIONE QUE DESEA REALIZAR:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        btnPrestamos = new JButton("REALIZAR PRESTAMOS");
        btnPrestamos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPrestamos.setBackground(new Color(51, 102, 153));
        btnPrestamos.setForeground(Color.WHITE);
        btnPrestamos.setFocusPainted(false);
        btnPrestamos.addActionListener(this);

        btnEjemplares = new JButton("CONSULTAR EJEMPLARES");
        btnEjemplares.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjemplares.setBackground(new Color(51, 102, 153));
        btnEjemplares.setForeground(Color.WHITE);
        btnEjemplares.setFocusPainted(false);
        btnEjemplares.addActionListener(this);

        buttonPanel.add(btnPrestamos);
        buttonPanel.add(btnEjemplares);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
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