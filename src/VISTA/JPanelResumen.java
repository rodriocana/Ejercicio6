/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VISTA;

import CONTROLADOR.JavaConnect;
import MODELO.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Rodri
 */
public class JPanelResumen extends javax.swing.JPanel {

    ResultSet rs;
    ResultSet rsAux;
    int numfilas = 0;
    GregorianCalendar fecha;
    String rutaImagen;

    public JPanelResumen() {
        initComponents();

        DatoUsuario();

    }

    public void DatoUsuario() {

        try {

            java.sql.Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM usuario");

            if (rs.first()) //pasa a la siguiente tupla
            {
                mostrarDatos();
                btnAnterior.setEnabled(false);
                btnPrimero.setEnabled(false);
            }

            // rs.close();
            //stmt.close();
        } catch (Exception e) {
            System.out.println("ALGO HA FALLADO EN -> PanelDetalle -> datosUsuario()");
            System.out.println(e);
        }
    }

    public void mostrarDatos() //metodo para mostrar datos
    {
        try {
            lblNumActual.setText(rs.getRow() + " / " + numfilas);
            textFieldCodigo.setText(rs.getString("numero"));
            textFieldNombre.setText(rs.getString("nombre"));
            textFieldSueldo.setText(rs.getString("sueldo"));

            /*rutaImagen = rs.getString("IMAGEN");
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                javax.swing.ImageIcon imagenIcon = new javax.swing.ImageIcon(getClass().getResource(rutaImagen));
                if (imagenIcon != null) {
                    java.awt.Image imagenEscalada = imagenIcon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                    lblFotoUsuario.setIcon(new javax.swing.ImageIcon(imagenEscalada));
                } else {
                    // Manejar el caso en que no se pueda cargar la imagen
                    System.out.println("No se pudo cargar la imagen desde: " + rutaImagen);
                }
            } else {
                lblFotoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ejercicio5-psp/default.jpg"))); // Imagen por defecto
            }*/
            
            java.sql.Timestamp timestamp = rs.getTimestamp("fechaalta");
            if (timestamp != null) {
                fecha = new GregorianCalendar();
                fecha.setTimeInMillis(timestamp.getTime());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // define el formato de la fecha
                String fechaAltaFormateada = dateFormat.format(fecha.getTime()); // formatea la fecha
                txtFecha.setText(fechaAltaFormateada); // muestro la fecha formateada en el label

            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldNombre = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        textFieldCodigo = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnPrimero = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();
        lblNumero = new javax.swing.JLabel();
        lblNumActual = new javax.swing.JLabel();
        txtNumero = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnListaCoches = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        textFieldSueldo = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JLabel();
        lblFotoUsuario = new javax.swing.JLabel();

        textFieldNombre.setText("jTextField1");

        lblUsuario.setText("Usuario");

        textFieldCodigo.setText("jTextField1");
        textFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCodigoActionPerformed(evt);
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

        lblNumActual.setText("jLabel1");

        txtNumero.setText("Codigo Usuario");

        txtNombre.setText("Nombre");

        jLabel3.setText("Usuario :");

        btnListaCoches.setText("ListaCoches");
        btnListaCoches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaCochesActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha");

        txtFecha.setText("jTextField1");

        textFieldSueldo.setText("jTextField1");
        textFieldSueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSueldoActionPerformed(evt);
            }
        });

        txtSueldo.setText("Sueldo");

        lblFotoUsuario.setText("IMAGEN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrimero)
                            .addComponent(btnAnterior)
                            .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumActual, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNumero)
                                .addComponent(txtNombre)
                                .addComponent(jLabel4)
                                .addComponent(txtSueldo)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnListaCoches)
                        .addGap(120, 120, 120))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblFotoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(80, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblUsuario))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSueldo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(lblNumActual))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrimero)
                    .addComponent(btnUltimo))
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblFotoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnListaCoches)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCodigoActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        try {
            rs.previous(); //pasa a la anterior tupla
            mostrarDatos();

            btnSiguiente.setEnabled(true);
            btnUltimo.setEnabled(true);

            if (rs.isFirst()) //si está sobre la primera tupla, anula el botón anterior
            {
                System.out.println("hola");
                btnAnterior.setEnabled(false);
                btnPrimero.setEnabled(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        try {

            rs.next(); //pasa a la siguiente tupla
            mostrarDatos();

            btnAnterior.setEnabled(true);
            btnPrimero.setEnabled(true);

            if (rs.isLast()) //si está sobre la última tupla, anula el botón siguiente
            {
                btnSiguiente.setEnabled(false);
                btnUltimo.setEnabled(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void textFieldSueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSueldoActionPerformed

    private void btnListaCochesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaCochesActionPerformed

        //Creo la nueva ventana
        JFrame VistaCoches = new JFrame();
        VistaCoches.setSize(400, 200);

        DefaultListModel<String> listaModelo = new DefaultListModel<>();
        JList<String> ListaCoches = new JList<>(listaModelo);

        VistaCoches.getContentPane().setLayout(new BorderLayout());
        VistaCoches.getContentPane().add(new JScrollPane(ListaCoches), BorderLayout.CENTER);
        VistaCoches.getContentPane().setBackground(Color.RED);

        VistaCoches.setVisible(true);

        try {

            //Creo la lista de empleados
            java.sql.Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rsAux = stmt.executeQuery("SELECT * FROM coche WHERE cod_usuario = " + rs.getInt("numero") + "");

            while (rsAux.next()) {

                listaModelo.addElement(
                        rsAux.getString("modelo") + ", "
                        + rsAux.getString("color")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelResumen.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnListaCochesActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnListaCoches;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblFotoUsuario;
    private javax.swing.JLabel lblNumActual;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField textFieldCodigo;
    private javax.swing.JTextField textFieldNombre;
    private javax.swing.JTextField textFieldSueldo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtNumero;
    private javax.swing.JLabel txtSueldo;
    // End of variables declaration//GEN-END:variables
}
