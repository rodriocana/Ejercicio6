package CONTROLADOR;

public class MiExcepcion extends Exception {

    private int codigo;
    private String mensaje;

    public MiExcepcion(int codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
