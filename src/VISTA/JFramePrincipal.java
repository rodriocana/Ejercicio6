/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

//import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.JavaConnect;
import MODELO.Usuario;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author bosque
 */
public class JFramePrincipal extends javax.swing.JFrame {

    private JPanelEntrar jpanel;

    public JFramePrincipal() {
        initComponents();
        // Tomas: Centrar JFrame en la pantalla
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Tomas: creo aquí los paneles, así cada uno siempre será el mismo.
        jPanelInicio = new JPanelInicio();

        //para INICIAR LA BASE DE DATOS;
        
        
        JavaConnect.connectdb();

        DetalleItem.setVisible(false);
        ResumenItem.setVisible(false);
        VisualizarMenu.setVisible(false);

        jPanelEntrar = new JPanelEntrar(this);
        this.cambiarPanel(jPanelInicio);
        
        
        
        

    }

    public void VisibilizarMenu() {

        DetalleItem.setVisible(true);
        ResumenItem.setVisible(true);
        VisualizarMenu.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuValidar = new javax.swing.JMenu();
        jMenuItemEntrar = new javax.swing.JMenuItem();
        jMenuItemSalir = new javax.swing.JMenuItem();
        VisualizarMenu = new javax.swing.JMenu();
        DetalleItem = new javax.swing.JMenuItem();
        ResumenItem = new javax.swing.JMenuItem();
        jMenuAcercaDe = new javax.swing.JMenu();
        jMenuItemAutor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica Java 5 BDs - Derby");
        setAlwaysOnTop(true);

        jMenuValidar.setText("Validar");

        jMenuItemEntrar.setText("Entrar");
        jMenuItemEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEntrarActionPerformed(evt);
            }
        });
        jMenuValidar.add(jMenuItemEntrar);

        jMenuItemSalir.setText("Salir");
        jMenuValidar.add(jMenuItemSalir);

        jMenuBar1.add(jMenuValidar);

        VisualizarMenu.setText("Visualizar");

        DetalleItem.setText("Detalle");
        DetalleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetalleItemActionPerformed(evt);
            }
        });
        VisualizarMenu.add(DetalleItem);

        ResumenItem.setText("Resumen");
        ResumenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResumenItemActionPerformed(evt);
            }
        });
        VisualizarMenu.add(ResumenItem);

        jMenuBar1.add(VisualizarMenu);

        jMenuAcercaDe.setText("Acerca de");

        jMenuItemAutor.setText("Autor");
        jMenuItemAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAutorActionPerformed(evt);
            }
        });
        jMenuAcercaDe.add(jMenuItemAutor);

        jMenuBar1.add(jMenuAcercaDe);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEntrarActionPerformed
        // TODO add your handling code here:
        // Tomas: cargo el panel. Se podría crear aquí, entonces siempre seria nuevo y no habría que declararlo como atributo.
        this.cambiarPanel(jPanelEntrar);


    }//GEN-LAST:event_jMenuItemEntrarActionPerformed

    private void jMenuItemAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAutorActionPerformed
        // TODO add your handling code here:

        JDialogAutor jDialogAutor = new JDialogAutor(this, true);
        // Tomas: Sin localización, se centra en la pantalla
        jDialogAutor.setResizable(false);
        jDialogAutor.setLocationRelativeTo(null);
        jDialogAutor.setVisible(true);

    }//GEN-LAST:event_jMenuItemAutorActionPerformed

    private void DetalleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleItemActionPerformed

        jPanelDetalle = new PanelDetalle(jpanel);
        this.cambiarPanel(jPanelDetalle);

    }//GEN-LAST:event_DetalleItemActionPerformed

    private void ResumenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResumenItemActionPerformed
       
        JPanelResumen JPanelResumen = new JPanelResumen();
        this.cambiarPanel(JPanelResumen);
        
    }//GEN-LAST:event_ResumenItemActionPerformed

    private void cambiarPanel(javax.swing.JPanel panel) {
        this.setContentPane(panel);
        this.pack();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePrincipal().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DetalleItem;
    private javax.swing.JMenuItem ResumenItem;
    private javax.swing.JMenu VisualizarMenu;
    private javax.swing.JMenu jMenuAcercaDe;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAutor;
    private javax.swing.JMenuItem jMenuItemEntrar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuValidar;
    // End of variables declaration//GEN-END:variables

    // Tomas: atributos a mano (paneles)
    private final JPanelInicio jPanelInicio;
    private final JPanelEntrar jPanelEntrar;
    private PanelDetalle jPanelDetalle;

}
