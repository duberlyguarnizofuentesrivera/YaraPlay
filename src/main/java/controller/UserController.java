package controller;

import dao.impl.*;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    final Employee employee = new Employee();
    final RegistroAcceso registroAcceso = new RegistroAcceso();
    final ProductDaoImpl productDao = new ProductDaoImpl();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);



    public void createProduct(JDialog dialog, String productName, String productCategory, String productSupplier) {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        SupplierDaoImpl supplierDao = new SupplierDaoImpl();
        Product product = new Product();
        product.setName(productName);
        //debería devolver solo uno, en cualquier caso se usa el primero devuelto
        List<Category> categoriesList = categoryDao.findByName(productCategory);
        List<Supplier> suppliersList = supplierDao.findByName(productSupplier);
        Category category = new Category();
        Supplier supplier = new Supplier();
        if (categoriesList.isEmpty()) {
            category.setName(productCategory);
            categoryDao.save(category);
        } else {
            category = categoriesList.get(0);
        }
        product.setCategory(category);
        if (suppliersList.isEmpty()) {
            String ruc = JOptionPane.showInputDialog("Ingrese el RUC del nuevo proveedor (11 dígitos):");
            if (ruc.length() != 11) {
                JOptionPane.showMessageDialog(null, "El RUC debe tener 11 dígitos");
                return;
            }
            supplier.setCompanyName(productSupplier);
            supplier.setRuc(ruc);
            supplierDao.saveBasic(supplier);
        } else {
            supplier = suppliersList.get(0);
        }
        product.setSupplier(supplier);
        productDao.saveBasic(product);
        dialog.dispose();
    }

    public void deleteProduct(JDialog dialog, String sId) {
        Product product = productDao.get(Integer.parseInt(sId)).orElse(null);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el producto con el ID ingresado");
        } else {
            productDao.delete(product);
            JOptionPane.showMessageDialog(null, "Producto con ID " + sId + " eliminado!");
            dialog.dispose();
        }
    }

    public String[][] findProducts(String productName, String productCategory, String productState, String productSupplier) {
        List<String[]> searchResults = productDao.findByNameCategoryStateSupplier(
                productName,
                productCategory,
                productState,
                productSupplier);
        log.info("buscando productos con nombre '{}' en la base de datos", productName);
        String[][] datos = new String[searchResults.size()][6];
        for (String[] producto : searchResults) {
            datos[searchResults.indexOf(producto)][0] = producto[0];
            datos[searchResults.indexOf(producto)][1] = producto[1];
            if (producto[2] != null) {
                datos[searchResults.indexOf(producto)][2] = producto[2];
            } else {
                datos[searchResults.indexOf(producto)][2] = "N/A";
            }
            if (producto[3] != null) {
                datos[searchResults.indexOf(producto)][3] = producto[3];
            } else {
                datos[searchResults.indexOf(producto)][3] = "N/A";
            }
            if (producto[4] != null) {
                datos[searchResults.indexOf(producto)][4] = producto[4];
            } else {
                datos[searchResults.indexOf(producto)][4] = "N/A";
            }
            if (producto[5] != null) {
                datos[searchResults.indexOf(producto)][5] = producto[5];
            } else {
                datos[searchResults.indexOf(producto)][5] = "N/A";
            }

        }
        return datos;
    }

    public String[][] findStockOfCategoryByName(String categoryName) {
        CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        List<Category> searchResults = categoryDao.findByName(categoryName);
        log.info("buscando stock de categorías con nombre '{}' en la base de datos", categoryName);
        String[][] datos = new String[searchResults.size()][2];
        for (Category category : searchResults) {
            datos[searchResults.indexOf(category)][0] = category.getName();
            String stock = categoryDao.getStock(category.getId());
            if (stock == null) {
                stock = "N/A";
            }
            datos[searchResults.indexOf(category)][1] = stock;
        }
        return datos;
    }

    public String[][] findStockOfProductsByName(String nombre) {
        List<Product> searchResults = productDao.findByName(nombre);
        log.info("buscando stock de productos con nombre '{}' en la base de datos", nombre);
        String[][] datos = new String[searchResults.size()][3];
        for (Product product : searchResults) {
            datos[searchResults.indexOf(product)][0] = product.getName();
            if (product.getSupplier() != null) {
                datos[searchResults.indexOf(product)][1] = product.getSupplier().getCompanyName();
            } else {
                datos[searchResults.indexOf(product)][1] = "N/A";
            }
            String stock = productDao.getStock(product.getId());
            if (stock == null) {
                stock = "N/A";
            }
            datos[searchResults.indexOf(product)][2] = stock;
        }
        return datos;
    }

    public String[][] listProductInBadCondition() {
        List<Product> productsInBadCondition = productDao.listProductsInBadCondition();
        if (productsInBadCondition.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos mal estado");
        } else {
            String[][] datos = new String[productsInBadCondition.size()][2];
            for (Product product : productsInBadCondition) {
                datos[productsInBadCondition.indexOf(product)][0] = product.getName();
                String estado = product.getState();
                if (estado == null) {
                    estado = "N/A";
                }
                datos[productsInBadCondition.indexOf(product)][1] = estado;
            }
            return datos;
        }
        return new String[0][0];
    }

    public Map<Long, String> listNamesAndID() {
        Map<Long, String> nombresYApellidos = new HashMap<>();
        PersonDaoImpl personaDao = new PersonDaoImpl();
        List<Person> people = personaDao.getAll();
        for (Person person : people) {
            nombresYApellidos.put(person.getId(), person.getName() + " " + person.getLastName());
        }
        return nombresYApellidos;
    }
}
