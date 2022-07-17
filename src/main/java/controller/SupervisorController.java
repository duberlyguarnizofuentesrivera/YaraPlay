package controller;

import dao.impl.ShelfDaoImpl;
import dao.impl.CategoryDaoImpl;
import dao.impl.PersonDaoImpl;
import dao.impl.SupplierDaoImpl;
import model.Shelf;
import model.Category;
import model.Supplier;

import javax.swing.*;
import java.util.List;

public class SupervisorController extends UserController {

    public void createSupplier(String companyName, String ruc, String address, String companyPhone, long personaId) {
        Supplier supplier = new Supplier();
        PersonDaoImpl personaDao = new PersonDaoImpl();
        supplier.setCompanyName(companyName);
        supplier.setContact(personaDao.get(personaId).orElse(null));
        supplier.setRuc(ruc);
        supplier.setAddress(address);
        supplier.setPhone(companyPhone);
        SupplierDaoImpl proveedorDao = new SupplierDaoImpl();
        proveedorDao.save(supplier);
    }

    public boolean createCategory(String nombre) {
        try {
            CategoryDaoImpl categoryDao = new CategoryDaoImpl();
            Category category = new Category();
            category.setName(nombre);
            categoryDao.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean createShelf(String piso, String pasillo, String nivel, String capacidad) {
        try {
            Shelf shelf = new Shelf();
            shelf.setFloor(Integer.parseInt(piso));
            shelf.setHallway(Integer.parseInt(pasillo));
            shelf.setLevel(Integer.parseInt(nivel));
            shelf.setCapacity(Integer.parseInt(capacidad));
            ShelfDaoImpl anaquelDao = new ShelfDaoImpl();
            anaquelDao.save(shelf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] viewSuppliers() {
        List<Supplier> proveedores;
        SupplierDaoImpl proveedorDao = new SupplierDaoImpl();
        proveedores = proveedorDao.getAll();
        String[][] datos = new String[proveedores.size()][2];
        for (Supplier supplier : proveedores) {
            datos[proveedores.indexOf(supplier)][0] = String.valueOf(supplier.getId());
            datos[proveedores.indexOf(supplier)][1] = supplier.getCompanyName();
        }
        return datos;
    }

    public String[][] viewCategories() {
        List<Category> categories;
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        categories = categoryDao.getAll();
        String[][] datos = new String[categories.size()][2];
        for (Category category : categories) {
            datos[categories.indexOf(category)][0] = String.valueOf(category.getId());
            datos[categories.indexOf(category)][1] = category.getName();
        }
        return datos;
    }

    public String[][] viewShelves() {
        List<Shelf> anaqueles;
        ShelfDaoImpl anaquelDao = new ShelfDaoImpl();
        anaqueles = anaquelDao.getAll();
        String[][] datos = new String[anaqueles.size()][5];
        for (Shelf shelf : anaqueles) {
            datos[anaqueles.indexOf(shelf)][0] = String.valueOf(shelf.getId());
            datos[anaqueles.indexOf(shelf)][1] = String.valueOf(shelf.getFloor());
            datos[anaqueles.indexOf(shelf)][2] = String.valueOf(shelf.getHallway());
            datos[anaqueles.indexOf(shelf)][3] = String.valueOf(shelf.getLevel());
            datos[anaqueles.indexOf(shelf)][4] = String.valueOf(shelf.getCapacity());

        }
        return datos;
    }

    public boolean deleteSupplier(String id) {
        try {
            SupplierDaoImpl proveedorDao = new SupplierDaoImpl();
            long idLong = Long.parseLong(id);
            Supplier supplier = proveedorDao.get(idLong).orElse(null);
            if (supplier != null) {
                proveedorDao.delete(supplier);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean deleteCategory(String id) {
        try {
            CategoryDaoImpl categoryDao = new CategoryDaoImpl();
            long idLong = Long.parseLong(id);
            Category category = categoryDao.get(idLong).orElse(null);
            if (category != null) {
                categoryDao.delete(category);
                JOptionPane.showMessageDialog(null, "Categoría eliminada");
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean deleteShelf(String id) {
        try {
            ShelfDaoImpl anaquelDao = new ShelfDaoImpl();
            long idLong = Long.parseLong(id);
            Shelf shelf = anaquelDao.get(idLong).orElse(null);
            if (shelf != null) {
                anaquelDao.delete(shelf);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
