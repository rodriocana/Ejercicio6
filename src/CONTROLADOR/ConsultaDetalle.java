/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Coche;
import MODELO.Usuario;
import VISTA.JPanelEntrar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodri
 */
public class ConsultaDetalle {

    private static ResultSet rs;
    ResultSet rsAux;
    int numfilas = 0;

    /*public ConsultaDetalle() {

        getResultSet();

    }*/
    public ConsultaDetalle() {

    }

    /*public static ResultSet getResultSet() {

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM coche WHERE cod_usuario = " + Usuario.getNumero() + "");  // esta consulta nos va a proporcionar los coches del 
            // usuario en cuestion, que sabremos gracias al atributo numero.
            ResultSetMetaData rsmd = rs.getMetaData();

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;  // este return pasa a la variable rs creada en la vistaDetalle
  
    }
     */
    // ESTE CODIGO TENGO QUE MEJORARLO, SIRVE PARA RECORRER LOS COCHES CREANDOLOS COMO OBJETOS USANDO UN USUARIO EN ESPE
    public static void iniciarResultSet(Usuario user) {
        ConsultaDetalle.rs = getResultSet(user);
    }

    public static Coche inicial(Usuario usuario) throws SQLException {

        Coche coche = new Coche();

        rs.first();
        coche.setCod_coche(rs.getInt("cod_coche"));
        coche.setCodigo_usuario(rs.getInt("cod_usuario"));
        coche.setColor(rs.getString("COLOR"));
        coche.setModelo(rs.getString("MODELO"));

        return coche;
    }

    public static Coche Siguiente(Usuario usuario) throws SQLException {

        Coche coche = new Coche();

        if (rs != null && rs.next()) {
            coche.setCod_coche(rs.getInt("cod_coche"));
            coche.setCodigo_usuario(rs.getInt("cod_usuario"));
            coche.setColor(rs.getString("COLOR"));
            coche.setModelo(rs.getString("MODELO"));

        }

        return coche;

    }

    public static Coche Atras(Usuario usuario) throws SQLException {

        Coche coche = new Coche();

       /* if (!rs.isAfterLast()) {

        }*/
       
        rs.previous();
        coche.setCod_coche(rs.getInt("cod_coche"));
        coche.setCodigo_usuario(rs.getInt("cod_usuario"));
        coche.setColor(rs.getString("COLOR"));
        coche.setModelo(rs.getString("MODELO"));

        return coche;

    }

    public static ResultSet getResultSet(Usuario user) {

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM coche WHERE cod_usuario = " + Usuario.getNumero() + "");  // esta consulta nos va a proporcionar los coches del 
            // usuario en cuestion, que sabremos gracias al atributo numero.
            ResultSetMetaData rsmd = rs.getMetaData();

            return rs;

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<Coche> listaCoches(Usuario user) throws SQLException {

        ResultSet rs = getResultSet(user);  // Obtener el ResultSet desde la función anterior para saber cuantos coches en este caso tiene cada usuario para 
        // luego añadir esa cantidad al arraylist listaCoches.

        ArrayList<Coche> listaCoches = new ArrayList<>();

        if (rs != null) {
            while (rs.next()) {
                Coche coche = new Coche();
                coche.setCod_coche(rs.getInt("cod_coche"));
                coche.setCodigo_usuario(rs.getInt("cod_usuario"));
                coche.setColor(rs.getString("COLOR"));
                coche.setModelo(rs.getString("MODELO"));
                listaCoches.add(coche);
            }
        }

        return listaCoches;
    }
}
