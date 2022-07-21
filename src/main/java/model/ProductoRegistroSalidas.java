package model;

import lombok.Data;

@Data
public class ProductoRegistroSalidas {
    private RegistroSalidas[] registroSalidas;
    private Product[] product;
}
