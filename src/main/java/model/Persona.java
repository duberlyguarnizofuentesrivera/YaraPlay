package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Persona {
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;
    private LocalDateTime fechaCreacion;

}
