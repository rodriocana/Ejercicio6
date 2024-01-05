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
    static byte imagen;

    public Usuario(int numero, String nombre, float sueldo, String fechaalta, String password, byte imagen) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.password = password;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaalta = new GregorianCalendar();
            this.fechaalta.setTime(dateFormat.parse(fechaalta));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    //constructor sin imagen
    public Usuario(int numero, String nombre, float sueldo, String fechaalta, String password) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fechaalta = new GregorianCalendar();
            this.fechaalta.setTime(dateFormat.parse(fechaalta));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.password = password;
    }
    
    //constructor vacio
    public Usuario(){
        
        
        
    }

    static public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    static public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    static public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImagen(byte imagen) {
        this.imagen = imagen;
    }

    public byte getImagen() {
        return imagen;
    }
}
