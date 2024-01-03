/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VISTA;

//import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.JavaConnect;
import MODELO.Usuario;
import java.awt.BorderLayout;
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
public class PanelDetalle extends javax.swing.JPanel {

    private final JPanelEntrar jframe;

    GregorianCalendar fecha;
    ResultSet rs;
    ResultSet rsAux;
    int numfilas = 0;

    public PanelDetalle(JPanelEntrar frame) {
        initComponents();

        this.jframe = frame;

        //ConsultaDetalle detalle = new ConsultaDetalle(); // aqui creamos una instancia de la clase ControladorDetalle
        //detalle.DatoUsuario();  // aqui llamamos al statement 
        DatoUsuario();

        mostrarPrimerRegistro();

    }

    public void DatoUsuario() {

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM coche WHERE cod_usuario = " + Usuario.getNumero() + "");
            ResultSetMetaData rsmd = rs.getMetaData();

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            textFieldModelo.setText(rs.getString("modelo"));
            textFieldColor.setText(rs.getString("color"));

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

    public void mostrarPrimerRegistro() {

        lblUsuario.setText(Usuario.getNombre());

        try {

            if (rs != null && rs.next()) {
                jlabelNumeroActual.setText(rs.getRow() + " / " + numfilas);
                textFieldModelo.setText(rs.getString("Modelo"));
                textFieldColor.setText(rs.getString("color"));

            } else {
                JOptionPane.showMessageDialog(this, "No hay más registros.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jlabelNumeroActual, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(356, Short.MAX_VALUE))
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
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrimero)
                    .addComponent(btnUltimo))
                .addContainerGap(147, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        try {

            rs.next(); //pasa a la siguiente tupla
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        try {
            if (!rs.isFirst()) {

                rs.previous();
                mostrarDatos();

            }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlabelNumeroActual;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField textFieldColor;
    private javax.swing.JTextField textFieldModelo;
    // End of variables declaration//GEN-END:variables
}
