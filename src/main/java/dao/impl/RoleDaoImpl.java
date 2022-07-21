package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements Dao<Role> {

    @Override
    public Optional<Role> get(long id) {
        List<Role> roles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM rol WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRol(roles, resultados);
        return Optional.ofNullable(roles.get(0));
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM rol");
        convertToRol(roles, resultados);
        return roles;
    }

    @Override
    public void save(Role role) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO rol (nombre) VALUES ('" + role.getName() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Role role, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(Role role) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM rol WHERE id = " + role.getId();
        jdbcConnection.executeUpdate(query);

    }

    private void convertToRol(List<Role> roles, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Role role = new Role();
            role.setId(Long.parseLong(resultado[0]));
            role.setName(resultado[1]);
            roles.add(role);
        }
    }
}
