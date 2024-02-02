/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Coche;
import MODELO.Ruta;
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

    int numfilas = 0;

    /*public ConsultaDetalle() {

        getResultSet();

    }*/
    public ConsultaDetalle() {

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

        if (rs.next()) {
            coche.setCod_coche(rs.getInt("cod_coche"));
            coche.setCodigo_usuario(rs.getInt("cod_usuario"));
            coche.setColor(rs.getString("COLOR"));
            coche.setModelo(rs.getString("MODELO"));
        } else {
            // No hay más coches siguientes
            coche = null;
        }

        return coche;
    }

    public static Coche Ultimo(Usuario usuario) throws SQLException {

        Coche coche = new Coche();

        rs.last();
        coche.setCod_coche(rs.getInt("cod_coche"));
        coche.setCodigo_usuario(rs.getInt("cod_usuario"));
        coche.setColor(rs.getString("COLOR"));
        coche.setModelo(rs.getString("MODELO"));

        return coche;
    }

    public static Coche Atras(Usuario usuario) throws SQLException {

        Coche coche = new Coche();

        if (!rs.previous()) {
            // Estamos antes del primer resultado, no hay datos para cargar
            coche = null;
        } else {
            coche.setCod_coche(rs.getInt("cod_coche"));
            coche.setCodigo_usuario(rs.getInt("cod_usuario"));
            coche.setColor(rs.getString("COLOR"));
            coche.setModelo(rs.getString("MODELO"));
        }

        return coche;

    }

    public static boolean ultimoCoche() throws SQLException {

        if (rs.isLast()) {

            return true;

        } else {

            return false;
        }

    }

    public static boolean primerCoche() throws SQLException {

        if (rs.isFirst()) {

            return true;

        } else {

            return false;
        }

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

    public static ResultSet getResultSetRutas(Usuario user) {
        try {

            int codCoche = obtenerCodCocheUsuario(user);

            if (codCoche != -1) { // Verifica que se haya obtenido un código de coche válido
                java.sql.Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery("SELECT * FROM ruta WHERE cod_coche = " + codCoche);

                return rs;
            } else {
                System.out.println("No se pudo obtener el código del coche para el usuario " + user.getNumero());
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Ruta inicial() throws SQLException {

        Ruta ruta = new Ruta();

        rs.first();
        Ruta.setCod_ruta(rs.getInt("cod_ruta"));
        Ruta.setDestino(rs.getString("destino"));
        Ruta.setOrigen(rs.getString("origen"));
        Ruta.setDistancia_km(rs.getInt("distancia_km"));

        return ruta;
    }

    public static Ruta Siguiente() throws SQLException {

        Ruta ruta = new Ruta();

        if (rs != null && rs.next()) {
            Ruta.setCod_ruta(rs.getInt("cod_ruta"));
            Ruta.setDestino(rs.getString("destino"));
            Ruta.setOrigen(rs.getString("origen"));
            Ruta.setDistancia_km(rs.getInt("distancia_km"));

        }

        return ruta;

    }

    public static Ruta RutaAtras() throws SQLException {

        Ruta ruta = new Ruta();

        rs.previous();
        Ruta.setCod_ruta(rs.getInt("cod_ruta"));
        Ruta.setDestino(rs.getString("destino"));
        Ruta.setOrigen(rs.getString("origen"));
        Ruta.setDistancia_km(rs.getInt("distancia_km"));

        return ruta;

    }

    public static ArrayList<Ruta> listaRuta(Usuario user) throws SQLException {

        ResultSet rs = getResultSetRutas(user);  // Obtener el ResultSet desde la función anterior para saber cuantos coches en este caso tiene cada usuario para 
        // luego añadir esa cantidad al arraylist listaCoches.

        ArrayList<Ruta> listaRutas = new ArrayList<>();

        if (rs != null) {
            while (rs.next()) {
                Ruta ruta = new Ruta();
                Ruta.setDestino(rs.getString("destino"));
                Ruta.setCod_ruta(rs.getInt("cod_ruta"));
                Ruta.setDistancia_km(rs.getInt("distancia_km"));
                Ruta.setOrigen(rs.getString("origen"));
                listaRutas.add(ruta);
            }
        }

        return listaRutas;
    }

    private static int obtenerCodCocheUsuario(Usuario user) {
        try {
            java.sql.Statement stmt = JavaConnect.getConnection().createStatement();
            ResultSet result = stmt.executeQuery("SELECT cod_coche FROM coche WHERE cod_usuario = " + user.getNumero());

            if (result.next()) {
                return result.getInt("cod_coche");
            } else {
                System.out.println("El usuario " + user.getNumero() + " no tiene un coche asociado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1; // Valor indicativo de que no se pudo obtener el código del coche
    }
}
