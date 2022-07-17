package controller;

import dao.impl.AnaquelDaoImpl;
import dao.impl.CategoriaDaoImpl;
import dao.impl.PersonaDaoImpl;
import dao.impl.ProveedorDaoImpl;
import model.Anaquel;
import model.Categoria;
import model.Proveedor;

import javax.swing.*;
import java.util.List;

public class AlmaceneroController extends UsuarioBasicoController {

    public void crearProveedor(String razonSocial, String ruc, String direccion, String telefono, long personaId) {
        Proveedor proveedor = new Proveedor();
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        proveedor.setRazonSocial(razonSocial);
        proveedor.setPersona(personaDao.get(personaId).orElse(null));
        proveedor.setRuc(ruc);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
        proveedorDao.save(proveedor);
    }

    public boolean crearCategoria(String nombre) {
        try {
            CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoriaDao.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean crearAnaquel(String piso, String pasillo, String nivel, String capacidad) {
        try {
            Anaquel anaquel = new Anaquel();
            anaquel.setPiso(Integer.parseInt(piso));
            anaquel.setPasillo(Integer.parseInt(pasillo));
            anaquel.setNivel(Integer.parseInt(nivel));
            anaquel.setCapacidad(Integer.parseInt(capacidad));
            AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
            anaquelDao.save(anaquel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] verProveedores() {
        List<Proveedor> proveedores;
        ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
        proveedores = proveedorDao.getAll();
        String[][] datos = new String[proveedores.size()][2];
        for (Proveedor proveedor : proveedores) {
            datos[proveedores.indexOf(proveedor)][0] = String.valueOf(proveedor.getId());
            datos[proveedores.indexOf(proveedor)][1] = proveedor.getRazonSocial();
        }
        return datos;
    }

    public String[][] verCategorias() {
        List<Categoria> categorias;
        CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
        categorias = categoriaDao.getAll();
        String[][] datos = new String[categorias.size()][2];
        for (Categoria categoria : categorias) {
            datos[categorias.indexOf(categoria)][0] = String.valueOf(categoria.getId());
            datos[categorias.indexOf(categoria)][1] = categoria.getNombre();
        }
        return datos;
    }

    public String[][] verAnaqueles() {
        List<Anaquel> anaqueles;
        AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
        anaqueles = anaquelDao.getAll();
        String[][] datos = new String[anaqueles.size()][5];
        for (Anaquel anaquel : anaqueles) {
            datos[anaqueles.indexOf(anaquel)][0] = String.valueOf(anaquel.getId());
            datos[anaqueles.indexOf(anaquel)][1] = String.valueOf(anaquel.getPiso());
            datos[anaqueles.indexOf(anaquel)][2] = String.valueOf(anaquel.getPasillo());
            datos[anaqueles.indexOf(anaquel)][3] = String.valueOf(anaquel.getNivel());
            datos[anaqueles.indexOf(anaquel)][4] = String.valueOf(anaquel.getCapacidad());

        }
        return datos;
    }

    public boolean eliminarProveedor(String id) {
        try {
            ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
            long idLong = Long.parseLong(id);
            Proveedor proveedor = proveedorDao.get(idLong).orElse(null);
            if (proveedor != null) {
                proveedorDao.delete(proveedor);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean eliminarCategoria(String id) {
        try {
            CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
            long idLong = Long.parseLong(id);
            Categoria categoria = categoriaDao.get(idLong).orElse(null);
            if (categoria != null) {
                categoriaDao.delete(categoria);
                JOptionPane.showMessageDialog(null, "Categoría eliminada");
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean eliminarAnaquel(String id) {
        try {
            AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
            long idLong = Long.parseLong(id);
            Anaquel anaquel = anaquelDao.get(idLong).orElse(null);
            if (anaquel != null) {
                anaquelDao.delete(anaquel);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
