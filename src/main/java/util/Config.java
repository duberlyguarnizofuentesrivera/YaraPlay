package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public void readConfig() {
        log.info("Leer configuración");
    }

    public void saveConfig() {
        log.info("Guardar configuración");
    }

    public void createConfigFile() {
        log.info("Crear archivo configuración");
    }
}
