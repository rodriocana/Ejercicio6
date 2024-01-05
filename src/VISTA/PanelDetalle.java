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

/**
 *
 * @author Rodri
 */
public class PanelDetalle extends javax.swing.JPanel {

    private final JPanelEntrar jframe;

    GregorianCalendar fecha;
    ResultSet rs;  // declarada para pasarle el return de la funcion creada en el controlador consultaDetalle.
    ResultSet rsAux;
    int numfilas = 0;

    public PanelDetalle(JPanelEntrar frame) {
        initComponents();

        this.jframe = frame;
        Usuario user = null;

        ConsultaDetalle detalle = new ConsultaDetalle(); // aqui creamos una instancia de la clase ControladorDetalle
        rs = detalle.getResultSet(user);  // aqui llamamos al statement pasandole el return de la funcion DatoUsuario a la variable rs y ya nos permite recorrer
        // las filas de la tabla del usuario introducido en el login.

        ConsultaDetalle.iniciarResultSet(user); // aqui inicio el rs del usuario que lo recojo de la clase consultaDetalle.
        
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

    public void mostrarPrimerRegistro(Usuario user) {
        
        ArrayList<Coche> cochesLista = new ArrayList<>();

        try {
            
            lblUsuario.setText(Usuario.getNombre());
//            
            ConsultaDetalle.inicial(user);  // para usar los coches como objetos.
            
            System.out.println("Modelo objeto coche " + Coche.getModelo());
            System.out.println("Color objeto coche " + Coche.getColor());
            
            cochesLista = ConsultaDetalle.listaCoches(user);  // aqui le paso el return listacoches de la clase ConsultaDetalle a la variable cochesLista
            
            System.out.println("Tamaño de la lista " + cochesLista.size());
            
           
            
            try {
                
                if (rs != null && rs.next()) {
                    /*jlabelNumeroActual.setText(rs.getRow() + " / " + numfilas);
                    textFieldModelo.setText(rs.getString("Modelo"));
                    textFieldColor.setText(rs.getString("color"));*/
                    
                    // asi seria usando un objeto de coche
                    jlabelNumeroActual.setText(rs.getRow() + " / " + numfilas);
                    textFieldModelo.setText(Coche.getModelo());
                    textFieldColor.setText(Coche.getColor());
                    
                } else {
                    JOptionPane.showMessageDialog(this, "No hay más registros.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel4 = new javax.swing.JLabel();

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

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Rodri\\Documents\\GRADO-SUPERIOR-2024\\PSP\\p06_java_final\\fotos\\1001.jpg")); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
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
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnterior)
                            .addComponent(btnSiguiente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPrimero)
                            .addComponent(btnUltimo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        Usuario user = null;
        
        try {

            rs.next(); //pasa a la siguiente tupla
            
            //ConsultaDetalle.Siguiente(user); para usar los coches como objeto
            mostrarDatos();

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        Usuario user = null;
        try {
            if (!rs.isFirst()) {

                 rs.previous();
                //ConsultaDetalle.Atras(user);  para usar los coches como objetos
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlabelNumeroActual;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField textFieldColor;
    private javax.swing.JTextField textFieldModelo;
    // End of variables declaration//GEN-END:variables
}
