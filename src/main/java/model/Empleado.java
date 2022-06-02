package model;

import lombok.Data;

@Data
public class Empleado {
    private long id;
    private Rol rol;
    private Persona persona;
    private RegistroAcceso registroAcceso;
    private RegistroIngresos registroIngresos;
    private RegistroSalidas registroSalidas;
}
