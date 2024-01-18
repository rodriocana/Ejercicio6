/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Coche;
import MODELO.Usuario;
import VISTA.JPanelEntrar;
import VISTA.PanelDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private final PanelDetalle panelDetalle;

    public AccionesSql(PanelDetalle panelDetalle) {
        this.panelDetalle = panelDetalle;

    }

    static Scanner sc = new Scanner(System.in);

    private static ResultSet rs;

    /*public static ResultSet EliminarCoche() {

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

    }*/
    public static void insertarCoche(Usuario usuario) {

        PanelDetalle detalle = null;
        int codigo_coche = 120;
        String color = "azul";
        String modelo = "ferrari";

        /*int codigo_coche = 0;
        String color = null;
        String modelo = null;

        detalle.RecogerDatosModelo(codigo_coche, color, modelo);*/
        
       
        
        

        String sql = "INSERT INTO coche (cod_coche, COLOR, MODELO, cod_usuario) VALUES (?, ?, ?, ?)";

        try (Connection connection = JavaConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, codigo_coche);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, modelo);
            preparedStatement.setInt(4, Usuario.getNumero());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Fila insertada exitosamente en la tabla coche.");
            } else {
                System.out.println("No se pudo insertar la fila en la tabla coche.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrarCoche(Usuario usuario) {

        int cod_coche = 120;
        String sql = "DELETE FROM coche WHERE cod_usuario = ? AND cod_coche = ?";

        try (Connection connection = JavaConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, usuario.getNumero());
            preparedStatement.setInt(2, cod_coche);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Fila eliminada exitosamente de la tabla coche.");
            } else {
                System.out.println("No se pudo eliminar la fila de la tabla coche. Verifica el código del coche o el usuario.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ActualizarCoche(Usuario usuario) {

        String color = "Rojo";
        String modelo = "Lamborghini";
        int cod_coche = 120;

        String sql = "UPDATE coche SET COLOR = ?, MODELO = ?, WHERE cod_coche = ?";

        try (Connection connection = JavaConnect.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, color);
            preparedStatement.setString(2, modelo);
            preparedStatement.setInt(3, cod_coche);

            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Fila actualizada exitosamente de la tabla coche.");

            } else {
                System.out.println("No se pudo actualizar la fila de la tabla coche. Verifica el código del coche o el usuario.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelEntrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
