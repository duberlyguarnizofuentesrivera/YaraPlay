package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Proveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProveedorDaoImpl implements Dao<Proveedor> {
    @Override
    public Optional<Proveedor> get(long id) {
        List<Proveedor> proveedores = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM proveedor WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProveedor(proveedores, resultados);
        return Optional.ofNullable(proveedores.get(0));
    }

    @Override
    public List<Proveedor> getAll() {
        List<Proveedor> proveedores = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM proveedor";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToProveedor(proveedores, resultados);
        return proveedores;
    }

    @Override
    public void save(Proveedor proveedor) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO proveedor (id, persona_id, razon_social, ruc, direccion, telefono) VALUES ("
                + proveedor.getId() + ", "
                + proveedor.getPersona().getId() + ", " + "'"
                + proveedor.getRazonSocial() + "', " + "'"
                + proveedor.getRuc() + "', " + "'"
                + proveedor.getDireccion() + "', " + "'"
                + proveedor.getTelefono() + "'"
                + ")";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Proveedor proveedor, String[] params) {

    }

    @Override
    public void delete(Proveedor proveedor) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM proveedor WHERE id = " + proveedor.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToProveedor(List<Proveedor> proveedores, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            Proveedor proveedor = new Proveedor();
            proveedor.setId(Long.parseLong(resultado[0]));
            proveedor.setPersona(personaDao.get(Long.parseLong(resultado[1])).get());
            proveedor.setRazonSocial(resultado[2]);
            proveedor.setRuc(resultado[3]);
            proveedor.setDireccion(resultado[4]);
            proveedor.setTelefono(resultado[5]);
            proveedores.add(proveedor);
        }
    }
}
