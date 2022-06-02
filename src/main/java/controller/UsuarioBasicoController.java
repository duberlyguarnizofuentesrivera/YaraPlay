package controller;

import java.time.LocalDateTime;

public class UsuarioBasicoController {
    public void ingresarProductos() {
        System.out.println("Ingresar productos");
    }

    public void sacarProductos() {
        System.out.println("Sacar productos");
    }

    public void registrarAccesoSistema() {
        System.out.println("Registrar acceso");
    }

    public void registrarSalidaSistema() {
        System.out.println("Registrar salida");
    }

    public String verificarStockProducto(int producto, LocalDateTime desde, LocalDateTime hasta) {
        System.out.println("verificar stock");
        return null;
    }

    public String verificarStockCategoria(int categoria, LocalDateTime desde, LocalDateTime hasta) {
        System.out.println("verificar stock");
        return null;
    }

    public String[] verificarRegistrosSalidasProductos(int producto, LocalDateTime desde, LocalDateTime hasta) {
        System.out.println("verificar registros de salidas");
        return null;
    }

    public String[] verificarRegistrosEntradasProductos(int producto, LocalDateTime desde, LocalDateTime hasta) {
        System.out.println("verificar registros de salidas");
        return null;
    }

    public String verificarEmpleadoDeTurno(int empleado, LocalDateTime desde, LocalDateTime hasta) {
        System.out.println("verificar empleado de turno");
        return null;
    }

    public String verificarProductosMalEstado() {
        System.out.println("verificar productos mal estado");
        return null;
    }

    public String verificarLocalizacionProductos(int[] producto) {
        System.out.println("verificar localizacion productos");
        return null;
    }



}
