/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Rodri
 */
public class Ruta {

    static int cod_ruta;
    static int distancia_km;
    static String origen;
    public static String destino;

    private int codigo_coche; // FK

    public Ruta(int cod_ruta, int distancia_km, String origen, String destino, int codigo_coche) {
        this.cod_ruta = cod_ruta;
        this.distancia_km = distancia_km;
        this.origen = origen;
        this.destino = destino;
        this.codigo_coche = codigo_coche;
    }

    public Ruta() {

    }

    public static int getCod_ruta() {
        return cod_ruta;
    }

    public static void setCod_ruta(int cod_ruta) {
        Ruta.cod_ruta = cod_ruta;
    }

    public static int getDistancia_km() {
        return distancia_km;
    }

    public static void setDistancia_km(int distancia_km) {
        Ruta.distancia_km = distancia_km;
    }

    public static String getOrigen() {
        return origen;
    }

    public static void setOrigen(String origen) {
        Ruta.origen = origen;
    }

    public static String getDestino() {
        return destino;
    }

    public static void setDestino(String destino) {
        Ruta.destino = destino;
    }

    public int getCodigo_coche() {
        return codigo_coche;
    }

    public void setCodigo_coche(int codigo_coche) {
        this.codigo_coche = codigo_coche;
    }

}
