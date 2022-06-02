package model;

import lombok.Data;

@Data
public class Sucursal {
    private long id;
    private Persona persona;
    private String nombre;
    private String direccion;
    private String telefono;
}
