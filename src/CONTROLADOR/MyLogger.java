/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);  // aqui creamos un archivo llamado app.log para guardar ahi los errores.
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}