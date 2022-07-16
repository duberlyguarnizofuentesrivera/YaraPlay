package controller;

import dao.impl.*;
import model.*;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioBasicoController {
    final Empleado empleado = new Empleado();
    final RegistroAcceso registroAcceso = new RegistroAcceso();
    final ProductoDaoImpl productoDao = new ProductoDaoImpl();


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
        return new String[0];
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
        System.out.println("verificar localización productos");
        return resultado;
    }

    public void crearProducto(JDialog dialog, String sNombre, String sCategoria, String sProveedor) {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
        ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
        Producto producto = new Producto();
        producto.setNombre(sNombre);
        //debería devolver solo uno, en todo caso se usa el primero devuelto
        List<Categoria> categoriaList = categoriaDao.findByName(sCategoria);
        List<Proveedor> proveedorList = proveedorDao.findByName(sProveedor);
        Categoria categoria = new Categoria();
        Proveedor proveedor = new Proveedor();
        if (categoriaList.isEmpty()) {
            categoria.setNombre(sCategoria);
            categoriaDao.save(categoria);
        } else {
            categoria = categoriaList.get(0);
        }
        producto.setCategoria(categoria);
        if (proveedorList.isEmpty()) {
            String ruc = JOptionPane.showInputDialog("Ingrese el RUC del nuevo proveedor (11 dígitos):");
            if (ruc.length() != 11) {
                JOptionPane.showMessageDialog(null, "El RUC debe tener 11 dígitos");
                return;
            }
            proveedor.setRazonSocial(sProveedor);
            proveedor.setRuc(ruc);
            proveedorDao.saveBasic(proveedor);
        } else {
            proveedor = proveedorList.get(0);
        }
        producto.setProveedor(proveedor);
        productoDao.saveBasic(producto);
        dialog.dispose();
    }

    public void eliminarProducto(JDialog dialog, String sId) {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        Producto producto = productoDao.get(Integer.parseInt(sId)).orElse(null);
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el producto con el ID ingresado");
        } else {
            productoDao.delete(producto);
            JOptionPane.showMessageDialog(null, "Producto con ID " + sId + " eliminado!");
            dialog.dispose();
        }
    }

    public String[][] buscarProductos(String nombre, String categoria, String estado, String proveedor) {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        List<String[]> resultadosDeBusqueda = productoDao.findByNameCategoryStateSupplier(
                nombre,
                categoria,
                estado,
                proveedor);
        System.out.println("buscando productos con nombre '" + nombre + "' en la base de datos");
        String[][] datos = new String[resultadosDeBusqueda.size()][6];
        for (String[] producto : resultadosDeBusqueda) {
            datos[resultadosDeBusqueda.indexOf(producto)][0] = producto[0];
            datos[resultadosDeBusqueda.indexOf(producto)][1] = producto[1];
            if (producto[2] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][2] = producto[2];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][2] = "N/A";
            }
            if (producto[3] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][3] = producto[3];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][3] = "N/A";
            }
            if (producto[4] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][4] = producto[4];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][4] = "N/A";
            }
            if (producto[5] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][5] = producto[5];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][5] = "N/A";
            }

        }
        return datos;
    }

    public String[][] buscarStockDeCategoriaPorNombre(String nombre) {
        CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
        List<Categoria> resultadosDeBusqueda = categoriaDao.findByName(nombre);
        System.out.println("buscando categorías con nombre '" + nombre + "' en la base de datos");
        String[][] datos = new String[resultadosDeBusqueda.size()][2];
        for (Categoria categoria : resultadosDeBusqueda) {
            datos[resultadosDeBusqueda.indexOf(categoria)][0] = categoria.getNombre();
            String stock = categoriaDao.getStock(categoria.getId());
            if (stock == null) {
                stock = "N/A";
            }
            datos[resultadosDeBusqueda.indexOf(categoria)][1] = stock;
        }
        return datos;
    }

    public String[][] buscarStockDeProductoPorNombre(String nombre) {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        List<Producto> resultadosDeBusqueda = productoDao.findByName(nombre);
        System.out.println("buscando productos con nombre '" + nombre + "' en la base de datos");
        String[][] datos = new String[resultadosDeBusqueda.size()][3];
        for (Producto producto : resultadosDeBusqueda) {
            datos[resultadosDeBusqueda.indexOf(producto)][0] = producto.getNombre();
            if (producto.getProveedor() != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][1] = producto.getProveedor().getRazonSocial();
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][1] = "N/A";
            }
            String stock = productoDao.getStock(producto.getId());
            if (stock == null) {
                stock = "N/A";
            }
            datos[resultadosDeBusqueda.indexOf(producto)][2] = stock;
        }
        return datos;
    }

    public String[][] listarProductosMalEstado() {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        List<Producto> productosMalEstado = productoDao.listarProductosMalEstado();
        if (productosMalEstado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos mal estado");
        } else {
            String[][] datos = new String[productosMalEstado.size()][2];
            for (Producto producto : productosMalEstado) {
                datos[productosMalEstado.indexOf(producto)][0] = producto.getNombre();
                String estado = producto.getEstado();
                if (estado == null) {
                    estado = "N/A";
                }
                datos[productosMalEstado.indexOf(producto)][1] = estado;
            }
            return datos;
        }
        return new String[0][0];
    }

    public Map<Long, String> listarNombresYApellidosYId() {
        Map<Long, String> nombresYApellidos = new HashMap<>();
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        List<Persona> personas = personaDao.getAll();
        for (Persona persona : personas) {
            nombresYApellidos.put(persona.getId(), persona.getNombre() + " " + persona.getApellido());
        }
        return nombresYApellidos;
    }
}
