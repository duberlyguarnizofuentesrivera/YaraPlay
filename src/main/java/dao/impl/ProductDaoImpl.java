package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements Dao<Product> {

    @Override
    public Optional<Product> get(long id) {
        List<Product> products = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(products, resultados);
        return Optional.ofNullable(products.get(0));
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(products, resultados);
        return products;
    }

    @Override
    public void save(Product product) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO producto (anaquel_id, categoria_id, proveedor_id, nombre, estado, stock) VALUES ("
                + product.getShelf().getId() + ", " + "'"
                + product.getCategory().getId() + "', " + "'"
                + product.getSupplier().getId() + "', " + "'"
                + product.getName() + "', " + "'"
                + product.getState() + "', " + "'"
                + product.getStock() + "'"
                + ")";
        jdbcConnection.executeUpdate(query);
    }
    public void saveBasic(Product product) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        product.setStock(0);
        product.setState("ok");
        String query = "INSERT INTO producto ( categoria_id, proveedor_id, nombre, estado, stock) VALUES ("
                + product.getCategory().getId() + ", "
                + product.getSupplier().getId() + ", '"
                + product.getName() + "', '"
                + product.getState() + "', "
                + product.getStock() + ")";
        jdbcConnection.executeUpdate(query);
    }
    @Override
    public void update(Product product, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(Product product) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM producto WHERE id = " + product.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToProducto(List<Product> products, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            ShelfDaoImpl anaquelDao = new ShelfDaoImpl();
            CategoryDaoImpl categoryDao = new CategoryDaoImpl();
            SupplierDaoImpl supplierDao = new SupplierDaoImpl();
            Product product = new Product();
            product.setId(Long.parseLong(resultado[0]));
            if (resultado[1] != null) {
                product.setShelf(anaquelDao.get(Long.parseLong(resultado[1])).orElse(null));
            }
            if (resultado[2] != null) {
                product.setCategory(categoryDao.get(Long.parseLong(resultado[2])).orElse(null));
            }
            if (resultado[3] != null) {
                product.setSupplier(supplierDao.get(Long.parseLong(resultado[3])).orElse(null));
            }
            product.setName(resultado[4]);
            product.setState(resultado[5]);
            product.setStock(Integer.parseInt(resultado[6]));
            products.add(product);
        }
    }

    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto WHERE nombre LIKE '%" + name + "%'";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(products, resultados);
        return products;
    }

    public String getStock(long id) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT stock FROM producto WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        return resultados.get(0)[0];
    }

    public List<Product> listProductsInBadCondition() {
        List<Product> products = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto WHERE estado <> 'ok'";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(products, resultados);
        return products;
    }

    public List<String[]> findByNameCategoryStateSupplier(String nombre, String categoryName, String estado, String supplierName) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String nameQuery;
        String categoryQuery;
        String stateQuery;
        String supplierQuery;

        nameQuery = "producto.nombre LIKE '%" + nombre + "%'";
        categoryQuery = "categoria.nombre LIKE '%" + categoryName + "%'";
        stateQuery = "producto.estado LIKE '%" + estado + "%'";
        supplierQuery = "proveedor.razonsocial LIKE '%" + supplierName + "%'";

        String query = "SELECT producto.id, producto.nombre, categoria.nombre, proveedor.razonsocial, anaquel.id, producto.estado"
                + " FROM producto"
                + " INNER JOIN categoria ON producto.categoria_id = categoria.id"
                + " INNER JOIN proveedor ON producto.proveedor_id = proveedor.id"
                + " INNER JOIN anaquel ON producto.anaquel_id = anaquel.id"
                + " WHERE " + nameQuery + " AND " + categoryQuery + " AND " + stateQuery + " AND " + supplierQuery;

        return jdbcConnection.executeQuery(query);
    }
}
