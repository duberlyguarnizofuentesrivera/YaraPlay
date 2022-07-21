package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilidades {
    private static final Logger log = LoggerFactory.getLogger(Utilidades.class);
    Config config;

    public void encryptData() {
        log.info("Encriptar data");
    }

    public void unEncryptData() {
        log.info("Desencriptar data");
    }

    public void validateConfig() {
        log.info("Validar configuración");
    }
}
