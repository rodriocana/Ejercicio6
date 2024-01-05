/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Rodri
 */
public class Coche {
    
        static int cod_coche;
        static String modelo;
        static String color;
        
        private int codigo_usuario; // FK 

    public Coche(int cod_coche, String modelo, String color, int codigo_usuario) {
        this.cod_coche = cod_coche;
        this.modelo = modelo;
        this.color = color;
        this.codigo_usuario = codigo_usuario;
    }

    public int getCodigo_usuario() {
        return codigo_usuario;
    }
    
    public Coche(){
        
        
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public static int getCod_coche() {
        return cod_coche;
    }

    public void setCod_coche(int cod_coche) {
        this.cod_coche = cod_coche;
    }

    public static  String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public static String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
        
    
}
