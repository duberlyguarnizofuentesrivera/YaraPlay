package model;

import lombok.Data;

@Data
public class Proveedor {
    private long id;
    private Persona persona;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String telefono;

}
