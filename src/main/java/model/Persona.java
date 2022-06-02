package model;

import lombok.Data;

@Data
public class Persona {
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;

}
