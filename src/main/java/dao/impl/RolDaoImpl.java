package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Rol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RolDaoImpl implements Dao<Rol> {

    @Override
    public Optional<Rol> get(long id) {
        List<Rol> roles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM rol WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRol(roles, resultados);
        return Optional.ofNullable(roles.get(0));
    }

    @Override
    public List<Rol> getAll() {
        List<Rol> roles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM rol");
        convertToRol(roles, resultados);
        return roles;
    }

    @Override
    public void save(Rol rol) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO rol (nombre) VALUES ('" + rol.getNombre() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Rol rol, String[] params) {

    }

    @Override
    public void delete(Rol rol) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM rol WHERE id = " + rol.getId();
        jdbcConnection.executeUpdate(query);

    }

    private void convertToRol(List<Rol> roles, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Rol rol = new Rol();
            rol.setId(Long.parseLong(resultado[0]));
            rol.setNombre(resultado[1]);
            roles.add(rol);
        }
    }
}
