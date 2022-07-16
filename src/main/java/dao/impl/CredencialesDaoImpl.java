package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Credenciales;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CredencialesDaoImpl implements Dao<Credenciales> {
    @Override
    public void save(Credenciales credenciales) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO credenciales (usuario, password) VALUES ('" + credenciales.getUserName() + "', '" + credenciales.getPassword() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Credenciales credenciales, String[] params) {

    }

    @Override
    public void delete(Credenciales credenciales) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM credenciales WHERE id = " + credenciales.getId();
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public Optional<Credenciales> get(long id) {
        List<Credenciales> credenciales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM credenciales WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCredencial(credenciales, resultados);
        return Optional.ofNullable(credenciales.get(0));
    }

    private void convertToCredencial(List<Credenciales> credenciales, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Credenciales credencial = new Credenciales();
            credencial.setId(Long.parseLong(resultado[0]));
            credencial.setUserName(resultado[1]);
            credencial.setPassword(resultado[2]);
            credenciales.add(credencial);
        }
    }

    @Override
    public List<Credenciales> getAll() {
        List<Credenciales> credenciales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM credenciales");
        convertToCredencial(credenciales, resultados);
        return credenciales;
    }
}
