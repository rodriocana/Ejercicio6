/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

//import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.AccionesSql;
import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.JavaConnect;
import CONTROLADOR.MiExcepcion;
import MODELO.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFramePrincipal extends javax.swing.JFrame {

    private JPanelEntrar jpanel;
    private static ResultSet rs;
    private PanelDetalle panelDetalle;

    public JFramePrincipal() {
        initComponents();
        // Centrar JFrame en la pantalla
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // creo aquí los paneles, así cada uno siempre será el mismo.
        jPanelInicio = new JPanelInicio();

        //para INICIAR LA BASE DE DATOS;
        try {
            JavaConnect.connectdb();
        } catch (MiExcepcion e) {  // aqui se maneja la excepcion MiExcepcion, y si hay un error nos saltará el mensaje especifico.
            System.out.println("Error código " + e.getCodigo() + ": " + e.getMessage());

        }

        DetalleItem.setVisible(false);
        ResumenItem.setVisible(false);
        VisualizarMenu.setVisible(false);
        jmenuAñadirEliminar.setVisible(false);

        jPanelEntrar = new JPanelEntrar(this); // aqui declaro una instancia de jPanelEntrar para poder usar sus metodos en esta vista
        this.cambiarPanel(jPanelInicio);

    }

    public void VisibilizarMenu() {

        DetalleItem.setVisible(true);
        ResumenItem.setVisible(true);
        VisualizarMenu.setVisible(true);

    }

    public void deshabilitarBotones() {

        DetalleItem.setVisible(false);
        ResumenItem.setVisible(false);
        VisualizarMenu.setVisible(false);
        jmenuAñadirEliminar.setVisible(false);

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
        jmenuAñadirEliminar = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
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
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
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

        jmenuAñadirEliminar.setText("Añadir/Modificar/Eliminar");

        jMenuItem1.setText("Nuevo coche");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmenuAñadirEliminar.add(jMenuItem1);

        jMenuItem3.setText("Modificar coche");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jmenuAñadirEliminar.add(jMenuItem3);

        jMenuItem2.setText("Eliminar coche");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jmenuAñadirEliminar.add(jMenuItem2);

        jMenuBar1.add(jmenuAñadirEliminar);

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

    // esta funcion cambia al panel entrar cuando pulsemos validar y despues entrar.
    private void jMenuItemEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEntrarActionPerformed
        // TODO add your handling code here:
        // Tomas: cargo el panel. Se podría crear aquí, entonces siempre seria nuevo y no habría que declararlo como atributo.
        this.cambiarPanel(jPanelEntrar);


    }//GEN-LAST:event_jMenuItemEntrarActionPerformed
    //esta funcion muestra el panel AUTOR
    private void jMenuItemAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAutorActionPerformed
        // TODO add your handling code here:

        JDialogAutor jDialogAutor = new JDialogAutor(this, true);
        // Tomas: Sin localización, se centra en la pantalla
        jDialogAutor.setResizable(false);
        jDialogAutor.setLocationRelativeTo(null);
        jDialogAutor.setVisible(true);

    }//GEN-LAST:event_jMenuItemAutorActionPerformed

    //esta funcion muestra el panel DETALLE
    private void DetalleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DetalleItemActionPerformed

        try {
            jPanelDetalle = new PanelDetalle(jpanel);  // aqui creo el objeto jPanelDetalle para poder entrar en sus metodos de clase, donde entro al darle a nuevo coche.
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jmenuAñadirEliminar.setVisible(true);
        this.cambiarPanel(jPanelDetalle);

    }//GEN-LAST:event_DetalleItemActionPerformed

    // esta funcion muestra el panel RESUMEN
    private void ResumenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResumenItemActionPerformed

        JPanelResumen JPanelResumen = new JPanelResumen();
        this.cambiarPanel(JPanelResumen);

    }//GEN-LAST:event_ResumenItemActionPerformed

    //ELIMINAR
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        try {
            Usuario user = null;
            //jPanelDetalle.VisibilizarBotonesNuevoCoche();
            AccionesSql.borrarCoche(user);  // esto funciona perfectamente
            JavaConnect.connectdb();
            rs = ConsultaDetalle.getResultSet(user);
            jPanelDetalle.mostrarDatos();
            try {

                jPanelDetalle.añadirTablasCoches(user);  // hace falta borrar la antigua y enseñar la 
            } catch (SQLException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MiExcepcion ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // NUEVO COCHE
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            Usuario user = null;

            AccionesSql.insertarCoche(user);  // esto funciona perfectamente
            JavaConnect.connectdb();
            rs = ConsultaDetalle.getResultSet(user);
            jPanelDetalle.mostrarDatos();
            try {
                jPanelDetalle.añadirTablasCoches(user);  // hace falta borrar la antigua y enseñar la 
            } catch (SQLException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MiExcepcion ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // BOTON SALIR
    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed

        jPanelEntrar.resetearDatos();
        deshabilitarBotones();

    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        Usuario usuario = null;

        AccionesSql.ActualizarCoche(usuario);  // esto funciona perfectamente

    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAutor;
    private javax.swing.JMenuItem jMenuItemEntrar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuValidar;
    private javax.swing.JMenu jmenuAñadirEliminar;
    // End of variables declaration//GEN-END:variables

    // Tomas: atributos a mano (paneles)
    private final JPanelInicio jPanelInicio;
    private final JPanelEntrar jPanelEntrar;
    private PanelDetalle jPanelDetalle;

}
