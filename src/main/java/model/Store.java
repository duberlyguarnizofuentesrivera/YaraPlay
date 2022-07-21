package model;

import lombok.Data;

@Data
public class Store {
    private long id;
    private Person personName;
    private String name;
    private String address;
    private String phone;
}
