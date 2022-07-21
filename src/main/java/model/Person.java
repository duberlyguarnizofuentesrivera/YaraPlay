package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {
    private long id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime creationDate;

}
