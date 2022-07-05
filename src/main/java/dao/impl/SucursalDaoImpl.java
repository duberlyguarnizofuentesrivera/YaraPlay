package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SucursalDaoImpl implements Dao<Sucursal> {

    @Override
    public Optional<Sucursal> get(long id) {
        List<Sucursal> sucursales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM sucursal WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToSucursal(sucursales, resultados);
        return Optional.ofNullable(sucursales.get(0));
    }

    @Override
    public List<Sucursal> getAll() {
        List<Sucursal> sucursales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM sucursal");
        convertToSucursal(sucursales, resultados);
        return sucursales;
    }

    @Override
    public void save(Sucursal sucursal) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO sucursal (persona_id, nombre, direccion, telefono) VALUES ("
                + sucursal.getPersona().getId() + ", '"
                + sucursal.getNombre() + "', '"
                + sucursal.getDireccion() + "', '"
                + sucursal.getTelefono() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Sucursal sucursal, String[] params) {

    }

    @Override
    public void delete(Sucursal sucursal) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM sucursal WHERE id = " + sucursal.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToSucursal(List<Sucursal> sucursales, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            Sucursal sucursal = new Sucursal();
            sucursal.setId(Long.parseLong(resultado[0]));
            sucursal.setPersona(personaDao.get(Long.parseLong(resultado[1])).get());
            sucursal.setNombre(resultado[2]);
            sucursal.setDireccion(resultado[3]);
            sucursal.setTelefono(resultado[4]);
            sucursales.add(sucursal);
        }
    }
}
