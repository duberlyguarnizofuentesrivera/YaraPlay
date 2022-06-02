package model;

import lombok.Data;

@Data
public class Producto {
    private long id;
    private Anaquel anaquel;
    private Categoria categoria;
    private Proveedor proveedor;
    private String nombre;
    private String estado;
    private int stock;

}
