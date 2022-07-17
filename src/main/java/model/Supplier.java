package model;

import lombok.Data;

@Data
public class Supplier {
    private long id;
    private Person contact;
    private String companyName;
    private String ruc;
    private String address;
    private String phone;

}
