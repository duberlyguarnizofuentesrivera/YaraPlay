package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDaoImpl implements Dao<Supplier> {
    @Override
    public Optional<Supplier> get(long id) {
        List<Supplier> proveedores = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM proveedor WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProveedor(proveedores, resultados);
        return Optional.ofNullable(proveedores.get(0));
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> proveedores = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM proveedor";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProveedor(proveedores, resultados);
        return proveedores;
    }

    @Override
    public void save(Supplier supplier) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO proveedor (persona_id, razonsocial, ruc, direccion, telefono) VALUES ("
                + supplier.getContact().getId() + ", '"
                + supplier.getCompanyName() + "', '"
                + supplier.getRuc() + "', " + "'"
                + supplier.getAddress() + "', '"
                + supplier.getPhone() + "')";
        jdbcConnection.executeUpdate(query);
    }

    public void saveBasic(Supplier supplier) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO proveedor ( razonsocial, ruc) VALUES ('"
                + supplier.getCompanyName() + "', " + "'"
                + supplier.getRuc() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Supplier supplier, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(Supplier supplier) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM proveedor WHERE id = " + supplier.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToProveedor(List<Supplier> proveedores, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            PersonDaoImpl personaDao = new PersonDaoImpl();
            Supplier supplier = new Supplier();
            supplier.setId(Long.parseLong(resultado[0]));
            if (resultado[1] != null) {
                supplier.setContact(personaDao.get(Long.parseLong(resultado[1])).orElse(null));
            }
            supplier.setCompanyName(resultado[2]);
            supplier.setRuc(resultado[3]);
            if (resultado[4] != null) {
                supplier.setAddress(resultado[4]);
            }
            if (resultado[5] != null) {
                supplier.setPhone(resultado[5]);
            }
            proveedores.add(supplier);
        }
    }

    public List<Supplier> findByName(String text) {
        List<Supplier> proveedores = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM proveedor WHERE razonsocial LIKE '%" + text + "%'";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProveedor(proveedores, resultados);
        return proveedores;
    }
}
