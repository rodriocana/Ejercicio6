/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONTROLADOR.JavaConnect;
import java.beans.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
//import static org.apache.derby.iapi.reference.ClassName.ResultSet;

/**
 *
 * @author Rodri
 */
public class Usuario {

    static int numero;
    static String nombre;
    static float sueldo;
    static GregorianCalendar fechaalta;
    static String password;
    static String DNI;
    static String imagen;

    public Usuario(int numero, String nombre, float sueldo, String fechaalta, String password, String DNI, String imagen) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.password = password;
        this.DNI = DNI;
        this.imagen = imagen;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaalta = new GregorianCalendar();
            this.fechaalta.setTime(dateFormat.parse(fechaalta));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //constructor vacio
    public Usuario() {

    }

    static public int getNumero() {
        return numero;
    }

    static public void setNumero(int numero) {   // si falla algo, quitar los static de los setter
        Usuario.numero = numero;
    }

    static public String getNombre() {
        return nombre;
    }

    static public void setNombre(String nombre) {
        Usuario.nombre = nombre;
    }

     static public float getSueldo() {
        return sueldo;
    }

    static public void setSueldo(float sueldo) {
        Usuario.sueldo = sueldo;
    }

    static public String getFechaAlta() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(Usuario.fechaalta.getTime()); //return dateFormat.format(this.fechaalta.getTime());
    }

    public void setFechaAlta(GregorianCalendar fechaAlta) {
        this.fechaalta = fechaAlta;
    }

    public String getPassword() { // esto debe ser estatico
        return password;
    }

    public static GregorianCalendar getFechaalta() {
        return fechaalta;
    }

    public static void setFechaalta(GregorianCalendar fechaalta) {
        Usuario.fechaalta = fechaalta;
    }

    static public  String getDNI() {
        return DNI;
    }

    public static void setDNI(String DNI) {
        Usuario.DNI = DNI;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    static public void setImagen(String imagen) {
        Usuario.imagen = imagen;
    }

    static public String getImagen() {
        return imagen;
    }
}
