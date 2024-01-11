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

    public ConsultaResumen() {
    }
    
    public static ResultSet getResultSet(Usuario user) {

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM usuario");  

            return rs;

        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public static Usuario inicial() throws SQLException {

        Usuario usuario = new Usuario();

        rs.first();
        Usuario.setNombre(rs.getString("Nombre"));
        Usuario.setNumero(rs.getInt("cod_usuario"));
        //coche.setColor(rs.getString("COLOR"));
        //coche.setModelo(rs.getString("MODELO"));

        return usuario;
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
    
    
    
}


