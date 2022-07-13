package controller;

import dao.impl.ProductoDaoImpl;
import dao.impl.RegistroAccesoDaoImpl;
import model.Empleado;
import model.Producto;
import model.RegistroAcceso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioBasicoController {
    Empleado empleado = new Empleado();
    RegistroAcceso registroAcceso = new RegistroAcceso();
    ProductoDaoImpl productoDao = new ProductoDaoImpl();

    public void ingresarProductos() {
        System.out.println("Ingresar productos");
    }

    public void sacarProductos() {
        System.out.println("Sacar productos");
    }

    public void registrarAccesoSistema() {
        registroAcceso.setFechaLogin(LocalDateTime.now());
        empleado.setRegistroAcceso(registroAcceso);
    }

    public void registrarSalidaSistema() {
        registroAcceso.setFechaLogout(LocalDateTime.now());
        RegistroAccesoDaoImpl registroAccesoDao = new RegistroAccesoDaoImpl();
        empleado.setRegistroAcceso(registroAcceso);
        registroAccesoDao.save(registroAcceso);
    }

    public String verificarStockProducto(int producto, LocalDateTime desde, LocalDateTime hasta) {
        AtomicInteger stock = new AtomicInteger();
        List<Producto> productos = productoDao.getAll();
        for (Producto producto1 : productos) {
            if (Objects.equals(producto1.getId(), producto)) {
                stock.set(producto1.getStock());
            }
        }
        return String.valueOf(stock);
    }

    public String verificarStockCategoria(int categoria, LocalDateTime desde, LocalDateTime hasta) {
        AtomicInteger stock = new AtomicInteger();
        productoDao.getAll().forEach(producto -> {
            if (Objects.equals(producto.getCategoria().getId(), categoria)) {
                stock.addAndGet(producto.getStock());
            }
        });
        return String.valueOf(stock);
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
        List<String> productos = new ArrayList<>();
        productoDao.getAll().forEach(producto -> {
            if (Objects.equals(producto.getEstado(), "MAL ESTADO")) {
                productos.add(producto.getNombre() + " " + producto.getStock());
            }
        });
        return productos.toString();
    }

    public String verificarLocalizacionProductos(long producto) {
        String resultado = "";

        List<Producto> productos = productoDao.getAll();
        for (Producto prod : productos) {
            if (prod.getId() == producto) {
                resultado = prod.getAnaquel().getPiso() + " - "
                        + prod.getAnaquel().getPasillo() + " - "
                        + prod.getAnaquel().getNivel();
            }
        }
        System.out.println("verificar localizacion productos");
        return resultado;
    }
}
