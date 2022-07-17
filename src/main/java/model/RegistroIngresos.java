package model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegistroIngresos {
    private long id;
    private Employee employee;
    private String nombreTransportista;
    private String dniTransportista;
    private double cantidad;
    private LocalDateTime fecha;
    private String obs;
}
