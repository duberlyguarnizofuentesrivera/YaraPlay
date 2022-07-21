package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreDaoImpl implements Dao<Store> {

    @Override
    public Optional<Store> get(long id) {
        List<Store> sucursales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM sucursal WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToSucursal(sucursales, resultados);
        return Optional.ofNullable(sucursales.get(0));
    }

    @Override
    public List<Store> getAll() {
        List<Store> sucursales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM sucursal");
        convertToSucursal(sucursales, resultados);
        return sucursales;
    }

    @Override
    public void save(Store store) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO sucursal (persona_id, nombre, direccion, telefono) VALUES ("
                + store.getPersonName().getId() + ", '"
                + store.getName() + "', '"
                + store.getAddress() + "', '"
                + store.getPhone() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Store store, String[] params) {
        // para uso futuro
    }

    @Override
    public void delete(Store store) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM sucursal WHERE id = " + store.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToSucursal(List<Store> sucursales, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            PersonDaoImpl personaDao = new PersonDaoImpl();
            Store store = new Store();
            store.setId(Long.parseLong(resultado[0]));
            store.setPersonName(personaDao.get(Long.parseLong(resultado[1])).orElse(null));
            store.setName(resultado[2]);
            store.setAddress(resultado[3]);
            store.setPhone(resultado[4]);
            sucursales.add(store);
        }
    }
}
