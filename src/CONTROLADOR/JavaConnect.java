package CONTROLADOR;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaConnect {

    private static Connection con;
    private static final Logger logger = MyLogger.getLogger();

    public static Connection connectdb() throws MiExcepcion {
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver encontrado");
        } catch (ClassNotFoundException e) {
            int codigoError = 101;  // aqui declaro una variable con el codigo especifico del error para pasarlo luego como parametro.
            String MensajeError = MError.getMensaje(codigoError);   // aqui uso ese codigo declarado arriba para recoger el mensaje.
            logger.log(Level.SEVERE, MensajeError, e); // aqui guardamos en el archivo log.app el error en cuestion, si no lo comentamos saltará
            // una excepcion y cerrará el programa.
            throw new MiExcepcion(codigoError, MensajeError);  // aqui lanzamos una nueva excepcion personalizada por nosotros
            // una vez salta la excepcion classnotfound.
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Concesionario?zeroDateTimeBehavior=convertToNull", "root", "admin");
            System.out.println("Conectado");
        } catch (SQLException e) {  // preguntar a tomas si puedo poner directamente MiExcepcion en este catch
            int codigoError = 102;
            String MensajeError = MError.getMensaje(codigoError);
            logger.log(Level.SEVERE, MensajeError, e);
            throw new MiExcepcion(codigoError, MensajeError); // esta excepcion se maneja en el frameprincipal cuando conectamos la base de datos
            // si no, el programa dejaria de funcionar.
        }

        return con;
    }

    public static Connection getConnection() {
        return con;
    }

    public static void cerrarConexion() throws MiExcepcion {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException ex) {
                int codigoError = 103;
                String MensajeError = MError.getMensaje(codigoError);
                 logger.log(Level.SEVERE, MensajeError,ex);
                throw new MiExcepcion(codigoError, MensajeError);
            }
        }
    }
}