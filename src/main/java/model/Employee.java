package model;

import lombok.Data;

@Data
public class Employee {
    private long id;
    private Role role;
    private Person person;
    private RegistroAcceso registroAcceso;
    private RegistroIngresos registroIngresos;
    private RegistroSalidas registroSalidas;
}
