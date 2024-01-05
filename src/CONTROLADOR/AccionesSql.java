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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodri
 */
public class AccionesSql {
    
    static Scanner sc = new Scanner(System.in);

    private static ResultSet rs;

    public static ResultSet EliminarCoche() {
        
        String modelo;
        
        
        System.out.println("Que modelo desea borrar:");
        modelo = sc.nextLine();
        

        try {

            Statement stmt = JavaConnect.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("Delete FROM coche WHERE cod_usuario = " + Usuario.getNumero() + " modelo = " + modelo);   // esta consulta va a eliminar el coche que le pasemos el codigo.
            ResultSetMetaData rsmd = rs.getMetaData();
            


        } catch (SQLException ex) {

            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;  // este return pasa a la variable rs creada en la vistaDetalle

    }

}
