package model;

import lombok.Data;

@Data
public class Credentials {
    long id;
    String userName;
    String password;
    int role;
    int employeeID;
}
