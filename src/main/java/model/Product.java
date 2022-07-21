package model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private Shelf shelf;
    private Category category;
    private Supplier supplier;
    private String name;
    private String state;
    private int stock;

}
