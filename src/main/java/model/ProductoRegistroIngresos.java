package model;

import lombok.Data;

@Data
public class ProductoRegistroIngresos {
    private RegistroIngresos[] registroIngresos;
    private Producto[] producto;
}
