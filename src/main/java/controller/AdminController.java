package controller;

import util.Configuracion;
import util.Utilidades;

public class AdminController extends AlmaceneroController {
    Configuracion configuracion;
    Utilidades utilidades;

    public void registrarUsuario() {
        System.out.println("Registrar usuario");
    }

    public void eliminarUsuario(int usuario) {
        System.out.println("Eliminar usuario");
    }

    public void resetearCredenciales(int usuario) {
        System.out.println("Resetear credenciales");
    }

    public void crearSucursal() {
        System.out.println("crear sucursal");
    }

    public void aprobarPedido() {
        System.out.println("aprobar pedido");
    }
}
