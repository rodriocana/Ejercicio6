/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rodri
 */
public class ConsultaUsuario {

    public ConsultaUsuario(JavaConnect con) {

    }

    public static Usuario getUsuario(String nombre, String contraseña) throws SQLException {

        //en esta funcion le pasamos el nombre y contraseña introducidos y sacamos sus datos llamando a la funcion recorrer y pasandole el rs
        /*Statement stmt = JavaConnect.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("Select NUMERO, NOMBRE, SUELDO, FECHAALTA, PASSWORD from USUARIO where NOMBRE = '" + nombre + "'" + " AND PASSWORD = '" + contraseña + "'");
        ResultSetMetaData rsmd = rs.getMetaData();*/
        // resultSetMetaData es una interfaz que proporciona información sobre los metadatos del ResultSet,
        //como el número de columnas, nombres de columnas, tipos de datos, etc.
        //preparedStatement hay que usarlo obligatorio.
        
        String consulta = "select * from usuario where nombre = ? AND password = ? ";  // aqui hacemos la consulta pasandole el nombre y password de los textField
        // del login
        Connection conexion = JavaConnect.getConnection();
        PreparedStatement sentencia = conexion.prepareStatement(consulta);
        sentencia.setString(1, nombre);
        sentencia.setString(2, contraseña);

        ResultSet rs = sentencia.executeQuery();

        return recorrer(rs);
    }

    public static Usuario recorrer(ResultSet rs) throws SQLException {

        Usuario user = null;  // esta variable nos permite crear luego un nuevo usuario (linea 63) y pasarle como parametro los datos de los campos a las
        // variables nuevas creadas (nombre,sueldo,fechaAtla,password);

        // aqui se pasan los datos del usuario introducido a las variables.
        while (rs.next()) {

            int numero = rs.getInt("NUMERO");
            String nombre = rs.getString("NOMBRE");
            Float sueldo = rs.getFloat("SUELDO");
            String fechaAlta = rs.getString("FECHAALTA");
            String password = rs.getString("PASSWORD");
            String dni = rs.getString("NIF");
            String imagen = rs.getString("foto");
            //Byte imagen = rs.getByte("rutaimagen");

            user = new Usuario(numero, nombre, sueldo, fechaAlta, dni, password, imagen); // Aqui ya creamos el nuevo usuario con los datos que tenemos en los campos de la tabla.

            System.out.println("Nombre de usuario: " + nombre + " " + password + " " + sueldo + " " + numero);
            System.out.println("La contraseña del usuario uno desde consultaUsuario es " + user.getPassword());
            System.out.println("el numero del usuario uno desde consultaUsuario es " + user.getNumero());
            
        }
        return user;
    }

    public static ArrayList<Usuario> listaUsuarios() throws SQLException {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Connection conexion = JavaConnect.getConnection();
        
        Statement st = conexion.createStatement();

        ResultSet rs = st.executeQuery("Select IDUSUARIO, USUARIO, CONTRASENIA, FECHAINGRESO, NIF FOTO from USUARIO");
        Usuario user;
        
        while (rs.next()) {

            int numero = rs.getInt("NUMERO");
            String nombre = rs.getString("NOMBRE");
            Float sueldo = rs.getFloat("SUELDO");
            String fechaAlta = rs.getString("FECHAALTA");
            String password = rs.getString("PASSWORD");
            String DNI = rs.getString("NIF");
            String imagen = rs.getString("foto");
            user = new Usuario(numero, nombre, sueldo, fechaAlta, password, DNI, imagen);
            listaUsuarios.add(user);
        }
        return listaUsuarios;

    }

}
