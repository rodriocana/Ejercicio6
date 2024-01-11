/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VISTA;

//import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.JavaConnect;
import MODELO.Coche;
import MODELO.Usuario;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodri
 */
public final class PanelDetalle extends javax.swing.JPanel {

    private final JPanelEntrar jframe;

    GregorianCalendar fecha;
    DefaultTableModel modelo;  // el JPANEL
    ResultSet rs;  // declarada para pasarle el return de la funcion creada en el controlador consultaDetalle.

    int numfilas = 0;

    public PanelDetalle(JPanelEntrar frame) {
        initComponents();

        this.jframe = frame;

        Usuario user = null;
        ConsultaDetalle detalle = new ConsultaDetalle(); // aqui creamos una instancia de la clase ControladorDetalle
        rs = detalle.getResultSet(user);  // aqui llamamos al statement pasandole el return de la funcion DatoUsuario a la variable rs y ya nos permite recorrer
        // las filas de la tabla del usuario introducido en el login.

        try {

            añadirTablas(user);
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        ocultarBotonesNuevoCoche();
        mostrarPrimerRegistro(user);

    }

    public void numeroFilas() {

        //
        try {
            rs.last();
            numfilas = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarDatos() //metodo para mostrar datos
    {

        try {
            jlabelNumeroActual.setText(rs.getRow() + " / " + numfilas);
            textFieldModelo.setText(Coche.getModelo());  
            textFieldColor.setText(Coche.getColor());

            if (rs.isLast()) //si está sobre la última tupla, anula el botón siguiente
            {
                btnSiguiente.setEnabled(false);
                btnUltimo.setEnabled(false);
                btnAnterior.setEnabled(true);
                btnPrimero.setEnabled(true);
            }

            if (rs.isFirst()) {

                btnAnterior.setEnabled(false);
                btnPrimero.setEnabled(false);
                btnSiguiente.setEnabled(true);
                btnUltimo.setEnabled(true);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    
    public void mostrarPrimerRegistro(Usuario user) {

        Coche coche;

        try {

            lblUsuario.setText(Usuario.getNombre());
//            
            ConsultaDetalle.inicial(user);  // para usar los coches como objetos.

            System.out.println(" 1º Modelo objeto coche inicial " + Coche.getModelo());
            System.out.println(" 1º Color objeto coche inicial " + Coche.getColor());

            jlabelNumeroActual.setText(rs.getRow() + " / " + numfilas);
            textFieldModelo.setText(Coche.getModelo());
            textFieldColor.setText(Coche.getColor());

            /*cochesLista = ConsultaDetalle.listaCoches(user);  // aqui le paso el return listacoches de la clase ConsultaDetalle a la variable cochesLista

            System.out.println("Tamaño de la lista " + cochesLista.size());*/
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void añadirTablas(Usuario user) throws SQLException {  // solo me muestra 2 coches en vez de 4

        modelo = (DefaultTableModel) Tabla.getModel();

        while (rs.next()) {

            ConsultaDetalle.Siguiente(user);  // no se por que ha funcionado esto
            modelo.addRow(new String[]{String.valueOf(Coche.getCod_coche()), Coche.getModelo(), Coche.getColor()});

        }

    }

    public void VisibilizarBotonesNuevoCoche() {

        txtCodigCoche.setVisible(true);
        txtModeloCoche.setVisible(true);
        txtColorCoche.setVisible(true);
        txtRutaCoche.setVisible(true);
        btnAñadir.setVisible(true);

        textFieldCodigoCoche.setVisible(true);
        textFieldModeloCoche.setVisible(true);
        textFieldColorCoche.setVisible(true);
        textFieldRutaCoche.setVisible(true);

    }

    public void ocultarBotonesNuevoCoche() {

        txtCodigCoche.setVisible(false);
        txtModeloCoche.setVisible(false);
        txtColorCoche.setVisible(false);
        txtRutaCoche.setVisible(false);
        btnAñadir.setVisible(false);

        textFieldCodigoCoche.setVisible(false);
        textFieldModeloCoche.setVisible(false);
        textFieldColorCoche.setVisible(false);
        textFieldRutaCoche.setVisible(false);

    }

    /*public void fechaUsuario()     {
        try
        {
            
           Statement rsAux = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rsAux = rsAux.executeQuery("SELECT fechaalta FROM usuario WHERE numero = " + Usuario.getNombre() + "");
            
            
            if (rsAux.next()) {
                java.sql.Timestamp timestamp = rsAux.getTimestamp("fechaalta");
                if (timestamp != null) 
                {
                    fecha = new GregorianCalendar();
                    fecha.setTimeInMillis(timestamp.getTime());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // define el formato de la fecha
                    String fechaAltaFormateada = dateFormat.format(fecha.getTime()); // formatea la fecha
                    lblFechaUsuario.setText("Fecha Alta: " + fechaAltaFormateada); // muestro la fecha formateada en el label
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("ALGO HA FALLADO EN -> PanelDetalle -> fechaUsuario()");
            System.out.println(e);
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldColor = new javax.swing.JTextField();
        textFieldModelo = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnPrimero = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        lblNumero = new javax.swing.JLabel();
        jlabelNumeroActual = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        txtCodigCoche = new javax.swing.JLabel();
        txtModeloCoche = new javax.swing.JLabel();
        textFieldModeloCoche = new javax.swing.JTextField();
        textFieldCodigoCoche = new javax.swing.JTextField();
        txtRutaCoche = new javax.swing.JLabel();
        textFieldRutaCoche = new javax.swing.JTextField();
        textFieldColorCoche = new javax.swing.JTextField();
        txtColorCoche = new javax.swing.JLabel();
        btnAñadir = new javax.swing.JButton();

        textFieldColor.setText("jTextField1");

        textFieldModelo.setText("jTextField1");
        textFieldModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldModeloActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPrimero.setText("Primero");
        btnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeroActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnUltimo.setText("Ultimo");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        lblNumero.setText("Num:");

        jlabelNumeroActual.setText("jLabel1");

        jLabel1.setText("Modelo");

        jLabel2.setText("Color");

        jLabel3.setText("Usuario :");

        lblUsuario.setText("Usu");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod_Coche", "Modelo", "Color", "Ruta"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        txtCodigCoche.setText("Codigo Coche");

        txtModeloCoche.setText("Modelo");

        textFieldCodigoCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCodigoCocheActionPerformed(evt);
            }
        });

        txtRutaCoche.setText("Ruta");

        textFieldColorCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldColorCocheActionPerformed(evt);
            }
        });

        txtColorCoche.setText("Color");

        btnAñadir.setText("AÑADIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrimero)
                            .addComponent(btnAnterior)
                            .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlabelNumeroActual, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigCoche)
                    .addComponent(txtModeloCoche)
                    .addComponent(txtRutaCoche)
                    .addComponent(txtColorCoche))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textFieldColorCoche, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldModeloCoche, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldCodigoCoche, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldRutaCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(btnAñadir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblUsuario))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumero)
                            .addComponent(jlabelNumeroActual))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnterior)
                            .addComponent(btnSiguiente)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldCodigoCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigCoche))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldModeloCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModeloCoche))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldColorCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColorCoche))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldRutaCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRutaCoche))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrimero)
                    .addComponent(btnUltimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadir))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        Usuario user = null;

        try {

            ConsultaDetalle.Siguiente(user); // para usar los coches objeto

            System.out.println("siguiente Modelo objeto coche " + Coche.getModelo());
            System.out.println("siguiente Color objeto coche " + Coche.getColor());
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        Usuario user = null;
        try {

            ConsultaDetalle.Atras(user);

            System.out.println("anterior Modelo objeto coche " + Coche.getModelo());
            System.out.println("anterior Color objeto coche " + Coche.getColor());
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void textFieldModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldModeloActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed

        try {
            rs.last();
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed

        try {
            rs.first();
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrimeroActionPerformed

    private void textFieldCodigoCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCodigoCocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCodigoCocheActionPerformed

    private void textFieldColorCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldColorCocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldColorCocheActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabelNumeroActual;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField textFieldCodigoCoche;
    private javax.swing.JTextField textFieldColor;
    private javax.swing.JTextField textFieldColorCoche;
    private javax.swing.JTextField textFieldModelo;
    private javax.swing.JTextField textFieldModeloCoche;
    private javax.swing.JTextField textFieldRutaCoche;
    private javax.swing.JLabel txtCodigCoche;
    private javax.swing.JLabel txtColorCoche;
    private javax.swing.JLabel txtModeloCoche;
    private javax.swing.JLabel txtRutaCoche;
    // End of variables declaration//GEN-END:variables
}
