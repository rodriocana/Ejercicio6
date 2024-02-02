/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VISTA;

//import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.ConsultaDetalle;
import CONTROLADOR.JavaConnect;
import MODELO.Coche;
import MODELO.Ruta;
import MODELO.Usuario;
import java.awt.BorderLayout;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
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

    static int contador = 1;
    Usuario user = null;
    GregorianCalendar fecha;
    DefaultTableModel modelo;  // el JPANEL
    ResultSet rs;  // declarada para pasarle el return de la funcion creada en el controlador consultaDetalle.
    ArrayList<Coche> listaCoche = ConsultaDetalle.listaCoches(user);
    ArrayList<Ruta> listaRutas = ConsultaDetalle.listaRuta(user);

    ConsultaDetalle detalle = new ConsultaDetalle(); // aqui creamos una instancia de la clase ControladorDetalle

    int numfilas = 0;

    public PanelDetalle(JPanelEntrar frame) throws SQLException {
        initComponents();

        this.jframe = frame;

        rs = detalle.getResultSet(user);  // aqui llamamos al statement pasandole el return de la funcion DatoUsuario a la variable rs y ya nos permite recorrer
        // las filas de la tabla del usuario introducido en el login.

        mostrarPrimerRegistro(user);
        añadirTablasCoches(user);

        //AñadirRutas(user);  // arreglar esta funcion.
        ocultarBotonesNuevoCoche();

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

    public int recogerCodCoche() {

        int cod_coche = Integer.parseInt(TextFieldCodCoche.getText());

        return cod_coche;
    }

    public void mostrarDatos() //metodo para mostrar datos
    {

        jlabelNumeroActual.setText(contador + " / " + numfilas);
        TextFieldCodCoche.setText(String.valueOf(Coche.getCod_coche()));
        textFieldModelo.setText(Coche.getModelo());
        textFieldColor.setText(Coche.getColor());

    }

    public String recogerModeloCoche() {

        String modelo = textFieldModelo.getText();

        return modelo;
    }

    public String recogerColorCoche() {

        String color = textFieldColor.getText();

        return color;
    }

//    
    public void mostrarPrimerRegistro(Usuario user) {

        Coche coche;

        try {

            ConsultaDetalle.inicial(user);  // para usar los coches como objetos.

            mostrarDatos();
            // Se encontró al menos un resultado, procesa los datos

            btnAnterior.setEnabled(false);
            btnPrimero.setEnabled(false);// Habilita el botón "Anterior" ya que hay al menos un resultado
            lblUsuario.setText(Usuario.getNombre());

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void añadirTablasCoches(Usuario user) throws SQLException {

        modelo = (DefaultTableModel) Tabla.getModel();
        ArrayList<Coche> cochesLista = ConsultaDetalle.listaCoches(user);

        borrarTodosElementosTablaCoches();

        // Utiliza un iterador para recorrer la lista de coches
        Iterator<Coche> iterator = cochesLista.iterator();

        System.out.println("Tamaño lista " + cochesLista.size());

        // Mientras haya coches en la lista, agrega filas a la tabla
        while (iterator.hasNext()) {

            ConsultaDetalle.Atras(user);  //  uso atras para que muestre del ultimo al primero.
            Coche coche = iterator.next();
            modelo.addRow(new String[]{
                String.valueOf(coche.getCod_coche()),
                coche.getModelo(),
                coche.getColor()
            });
        }
    }

    public void borrarTodosElementosTablaCoches() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        modelo.setRowCount(0);
    }

    /*public void añadirTablasRutas(Usuario user) throws SQLException {
        
        modelo = (DefaultTableModel) Tabla1.getModel();
        ArrayList<Ruta> rutasLista = ConsultaDetalle.listaRuta(user);

        try {
            
            rs = ConsultaDetalle.getResultSetRutas(user);
            ConsultaDetalle.Atras();  // Asegúrate de que esta llamada no afecte la posición del cursor

            System.out.println("Tamaño lista " + listaRutas.size());

            // Mientras haya rutas en el ResultSet, agrega filas a la tabla
            while (rs.next()) {
                Ruta ruta = new Ruta(); // Crea un objeto Ruta y configúralo con los datos del ResultSet
                ruta.setCod_ruta(rs.getInt("cod_ruta"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDistancia_km(rs.getInt("distancia_km"));

                modelo.addRow(new String[]{
                    String.valueOf(ruta.getCod_ruta()),
                    ruta.getDestino(),
                    ruta.getOrigen(),
                    String.valueOf(ruta.getDistancia_km())
                });
            }
        } catch (SQLException e) {
            // Manejar la excepción (imprimir el rastreo de la pila o registrarla)
            e.printStackTrace();
        }
    }*/
    public void AñadirRutas(Usuario user) throws SQLException {

        rs = ConsultaDetalle.getResultSetRutas(user);

        modelo = (DefaultTableModel) Tabla1.getModel();
        ArrayList<Ruta> listaRuta = ConsultaDetalle.listaRuta(user);

        // Utiliza un iterador para recorrer la lista de coches
        Iterator<Ruta> iterator = listaRuta.iterator();

        System.out.println("Tamaño lista " + listaRuta.size());

        // Mientras haya coches en la lista, agrega filas a la tabla
        while (iterator.hasNext()) {

            ConsultaDetalle.RutaAtras();  //  uso atras para que muestre del ultimo al primero.
            Ruta ruta = iterator.next();
            modelo.addRow(new String[]{
                String.valueOf(ruta.getCod_ruta()),
                ruta.getDestino(),
                ruta.getOrigen(),
                String.valueOf(ruta.getDistancia_km())
            });
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
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JLabelCodCoche = new javax.swing.JLabel();
        TextFieldCodCoche = new javax.swing.JTextField();

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
                "Cod_Coche", "Modelo", "Color"
            }
        ));
        jScrollPane1.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(0).setHeaderValue("Cod_Coche");
            Tabla.getColumnModel().getColumn(1).setHeaderValue("Modelo");
            Tabla.getColumnModel().getColumn(2).setHeaderValue("Color");
        }

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

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod_ruta", "Origen", "Destino", "Distancia_km"
            }
        ));
        jScrollPane2.setViewportView(Tabla1);

        jLabel4.setText("RUTAS");

        jLabel5.setText("COCHES");

        JLabelCodCoche.setText("Cod_coche");

        TextFieldCodCoche.setText("jTextField1");
        TextFieldCodCoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldCodCocheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(JLabelCodCoche))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel1))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(71, 71, 71)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(TextFieldCodCoche, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                            .addComponent(textFieldModelo)
                                                            .addComponent(textFieldColor)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel2)))))
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
                                    .addComponent(textFieldRutaCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPrimero)
                                    .addComponent(btnAnterior)
                                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlabelNumeroActual, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(btnAñadir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(125, 125, 125)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel4)
                                .addGap(0, 107, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
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
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLabelCodCoche)
                            .addComponent(TextFieldCodCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(60, 60, 60)
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAñadir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed

        contador++;

        Coche coche = null;

        try {
            coche = ConsultaDetalle.Siguiente(user);
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            if (ConsultaDetalle.ultimoCoche()) {

                //añadirTablasRutas(user);
                // Hay un coche siguiente, procesa los datos y habilita los botones correspondientes
                btnSiguiente.setEnabled(false);
                btnAnterior.setEnabled(true);
                btnPrimero.setEnabled(true);
                btnUltimo.setEnabled(false);
            } else {
                // No hay más coches siguientes, desactiva el botón "Siguiente" y "Último"

                btnUltimo.setEnabled(true);
                btnAnterior.setEnabled(true);
                btnPrimero.setEnabled(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        mostrarDatos();

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        btnSiguiente.setEnabled(true);
        btnUltimo.setEnabled(true);

        contador--;

        Coche coche;
        try {

            coche = ConsultaDetalle.Atras(user);

            if (ConsultaDetalle.primerCoche()) {

                btnPrimero.setEnabled(false);
                btnAnterior.setEnabled(false); // Habilita el botón "Anterior" ya que hay al menos un resultado
            } else {
                // No hay resultados, desactiva el botón "Anterior"
                btnPrimero.setEnabled(true);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        mostrarDatos();


    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void textFieldModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldModeloActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed

        Coche coche;
        try {

            coche = ConsultaDetalle.Ultimo(user);
            mostrarDatos();

            btnPrimero.setVisible(true);
            btnAnterior.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(PanelDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void btnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeroActionPerformed

        Coche coche;
        try {

            coche = ConsultaDetalle.inicial(user);
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

    private void TextFieldCodCocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldCodCocheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldCodCocheActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCodCoche;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JTextField TextFieldCodCoche;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnPrimero;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
