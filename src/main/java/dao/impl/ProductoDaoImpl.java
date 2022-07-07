package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDaoImpl implements Dao<Producto> {

    @Override
    public Optional<Producto> get(long id) {
        List<Producto> productos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(productos, resultados);
        return Optional.ofNullable(productos.get(0));
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM producto";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProducto(productos, resultados);
        return productos;
    }

    @Override
    public void save(Producto producto) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO producto (anaquel_id, categoria_id, proveedor_id, nombre, estado, stock) VALUES ("
                + producto.getAnaquel().getId() + ", " + "'"
                + producto.getCategoria().getId() + "', " + "'"
                + producto.getProveedor().getId() + "', " + "'"
                + producto.getNombre() + "', " + "'"
                + producto.getEstado() + "', " + "'"
                + producto.getStock() + "'"
                + ")";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Producto producto, String[] params) {

    }

    @Override
    public void delete(Producto producto) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM producto WHERE id = " + producto.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToProducto(List<Producto> productos, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
            CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
            ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
            Producto producto = new Producto();
            producto.setId(Long.parseLong(resultado[0]));
            producto.setAnaquel(anaquelDao.get(Long.parseLong(resultado[1])).get());
            producto.setCategoria(categoriaDao.get(Long.parseLong(resultado[2])).get());
            producto.setProveedor(proveedorDao.get(Long.parseLong(resultado[3])).get());
            producto.setNombre(resultado[4]);
            producto.setEstado(resultado[5]);
            producto.setStock(Integer.parseInt(resultado[6]));
            productos.add(producto);
        }
    }
}
