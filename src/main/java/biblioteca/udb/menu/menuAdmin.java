package biblioteca.udb.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import biblioteca.udb.vistas.bibliotecaVista;
import biblioteca.udb.FormularioUsuarios;
import biblioteca.udb.Devoluciones;
import biblioteca.udb.FormularioConfiguracionEJEM;

public class menuAdmin extends JFrame implements ActionListener {
    private JButton btnBiblioteca, btnAgregarUser, btnDevoluciones, btnControlPrestamos;
    private JPanel mainPanel;

    public menuAdmin() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BorderLayout(0, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel titleLabel = new JLabel("MENÚ PRINCIPAL");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("Seleccione lo que desea realizar:");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new GridLayout(4, 1, 15, 15));

        btnBiblioteca = createButton("AGREGAR EJEMPLAR");
        btnAgregarUser = createButton("AGREGAR USUARIO");
        btnDevoluciones = createButton("DEVOLUCIONES");
        btnControlPrestamos = createButton("CONTROL PRÉSTAMOS");

        buttonPanel.add(btnBiblioteca);
        buttonPanel.add(btnAgregarUser);
        buttonPanel.add(btnDevoluciones);
        buttonPanel.add(btnControlPrestamos);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(subtitleLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(51, 102, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(this);
        return button;
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