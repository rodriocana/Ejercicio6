/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Coche;
import MODELO.Usuario;
import VISTA.JPanelEntrar;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodri
 */
public class ConsultaResumen {

    private static ResultSet rs;
    ResultSet rsAux;
    int numfilas = 0;
    public ResultSet getResultSet;

    public ConsultaResumen() {
    }

    public static ResultSet getResultSet() {

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM usuario");

            return rs;

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ResultSet getResultSetCoches() {

        try {

            java.sql.Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM coche WHERE cod_usuario = " + Usuario.getNumero() + "");

            System.out.println("Numero de socio: " + Usuario.getNumero());
            System.out.println("Color del coche primero " + Coche.getColor());
            return rs;

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Usuario inicial() throws SQLException {

        Usuario usuario = new Usuario();

        rs.first();
        Usuario.setNumero(rs.getInt("numero"));
        Usuario.setNombre(rs.getString("Nombre"));
        Usuario.setSueldo(rs.getFloat("sueldo"));
        Usuario.setDNI(rs.getString("nif"));
        Usuario.setImagen(rs.getString("foto"));

        return usuario;
    }

    public static Usuario Siguiente() throws SQLException {

        Usuario usuario = new Usuario();

        if (rs != null && rs.next()) {
            Usuario.setNumero(rs.getInt("numero"));
            Usuario.setNombre(rs.getString("Nombre"));
            Usuario.setSueldo(rs.getFloat("sueldo"));
            Usuario.setDNI(rs.getString("nif"));
            Usuario.setImagen(rs.getString("foto"));

        }

        return usuario;

    }

    public static Usuario Atras() throws SQLException {

        Usuario usuario = new Usuario();

        /* if (!rs.isAfterLast()) {

        }*/
        rs.previous();
        Usuario.setNumero(rs.getInt("numero"));
        Usuario.setNombre(rs.getString("Nombre"));
        Usuario.setSueldo(rs.getFloat("sueldo"));
        Usuario.setDNI(rs.getString("nif"));
        Usuario.setImagen(rs.getString("foto"));

        return usuario;

    }
    
    public static boolean ultimoUsuario() throws SQLException {

        if (rs.isLast()) {

            return true;

        } else {

            return false;
        }

    }

    public static boolean primerUsuario() throws SQLException {

        if (rs.isFirst()) {

            return true;

        } else {

            return false;
        }

    }

}
