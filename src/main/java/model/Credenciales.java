package model;

import lombok.Data;

@Data
public class Credenciales {
    long id;
    String userName;
    String password;
    int rol;
    int Empleado;
}
