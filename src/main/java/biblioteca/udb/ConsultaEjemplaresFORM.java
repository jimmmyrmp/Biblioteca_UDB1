/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package biblioteca.udb;

import biblioteca.udb.ejemplares.ejemplares;
import java.sql.Connection;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ConsultaEjemplaresFORM extends javax.swing.JFrame {
    
    // Variables para Persona 4
    private Ejemplar1 ejemplarDAO;
    private DefaultTableModel modeloTabla;

    /**
     * Creates new form ConsultaEjemplares
     */
    public ConsultaEjemplaresFORM() {
        initComponents();
        inicializar();
    }

    private void inicializar() {
        try {
            // Conectar a la base de datos
        Connection conn = ConexionBD.conectar();
            ejemplarDAO = new Ejemplar1(conn);
            
            // Configurar la tabla
            configurarTabla();
            
            // Cargar todos los ejemplares
            cargarTodosLosEjemplares();
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al inicializar: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

 

   
    //  columnas de la tabla
    private void configurarTabla() {
        String[] columnas = {"Código", "Título", "Autor", "Año", "Ubicación", "Disponible", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        jTable2.setModel(modeloTabla);
    }
    
    // donde cargan todos los ejemplares
    private void cargarTodosLosEjemplares() {
        try {
            List<ejemplares> lista = ejemplarDAO.obtenerTodos();
            cargarEjemplaresEnTabla(lista);
            actualizarEstadisticas();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al cargar ejemplares: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // donde cargan los ejemplares en la tabla
    private void cargarEjemplaresEnTabla(List<ejemplares> lista) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        for (ejemplares ej : lista) {
            Object[] fila = {
                ej.getCodigo(),
                ej.getTitulo(),
                ej.getAutor() != null ? ej.getAutor() : "N/A",
                ej.getAño(),
                ej.getUbicacion(),
                ej.isDisponible() ? "Sí" : "No",
                ej.getTipo()
            };
            modeloTabla.addRow(fila);
        }
    }
    
    // para actualizar las estadísticas
    private void actualizarEstadisticas() {
        try {
            int[] stats = ejemplarDAO.obtenerEstadisticas();
            lblTotal.setText("Total: " + stats[0]);
            lblDisponibles.setText("Disponibles: " + stats[1]);
            lblPrestados.setText("Prestados: " + stats[2]);
        } catch (Exception e) {
            System.err.println("Error en estadísticas: " + e.getMessage());
        }
    }
    
    // para buscar los ejemplares por criterio
    private void buscarEjemplares() {
        String criterio = txtBuscar.getText().trim();
        
        if (criterio.isEmpty()) {
            cargarTodosLosEjemplares();
            return;
        }
        
        try {
            List<ejemplares> lista = ejemplarDAO.buscarEjemplares(criterio);
            cargarEjemplaresEnTabla(lista);
            
            if (lista.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No se encontraron ejemplares con: " + criterio,
                    "Sin resultados",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al buscar: " + e.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

   
    public static void main(String args[]) {
     
        /*Crear y mostrar el form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaEjemplaresFORM().setVisible(true);
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnMostrarTodos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblDisponibles = new javax.swing.JLabel();
        lblPrestados = new javax.swing.JLabel();
        volverMenu = new javax.swing.JButton();
        tblEjemplares = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Buscar:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnMostrarTodos.setText("Mostrar Todos");
        btnMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lblTotal.setText("Total: 0");

        lblDisponibles.setText("Disponibles: 0");

        lblPrestados.setText("Prestados: 0");

        volverMenu.setText("Volver al menu");
        volverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(volverMenu)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(lblPrestados, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisponibles)
                    .addComponent(lblTotal)
                    .addComponent(lblPrestados))
                .addGap(35, 35, 35)
                .addComponent(volverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEjemplares.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tblEjemplares))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tblEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         buscarEjemplares();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodosActionPerformed
        cargarTodosLosEjemplares();
    }//GEN-LAST:event_btnMostrarTodosActionPerformed

    private void volverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverMenuActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_volverMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblPrestados;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JScrollPane tblEjemplares;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JButton volverMenu;
    // End of variables declaration//GEN-END:variables
}
