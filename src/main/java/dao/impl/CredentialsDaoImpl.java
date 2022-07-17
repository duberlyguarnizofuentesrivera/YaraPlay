package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CredentialsDaoImpl implements Dao<Credentials> {
    @Override
    public void save(Credentials credentials) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO credenciales (usuario, password, rol_id, empleado_id) VALUES ('" + credentials.getUserName() + "', '" + credentials.getPassword() + "','"+ credentials.getRole()+"','"+ credentials.getEmployeeID()+"')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Credentials credentials, String[] params) {
        //para utilización en el futuro
    }

    @Override
    public void delete(Credentials credentials) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM credenciales WHERE id = " + credentials.getId();
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public Optional<Credentials> get(long id) {
        List<Credentials> credenciales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM credenciales WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCredencial(credenciales, resultados);
        return Optional.ofNullable(credenciales.get(0));
    }

    private void convertToCredencial(List<Credentials> credenciales, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Credentials credencial = new Credentials();
            credencial.setId(Long.parseLong(resultado[0]));
            credencial.setUserName(resultado[1]);
            credencial.setPassword(resultado[2]);
            if (resultado[3] != null) {
                credencial.setRole(Integer.parseInt(resultado[3]));
            }
            if (resultado[4] != null) {
                credencial.setEmployeeID(Integer.parseInt(resultado[4]));
            }
            credenciales.add(credencial);
        }
    }

    @Override
    public List<Credentials> getAll() {
        List<Credentials> credenciales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM credenciales");
        convertToCredencial(credenciales, resultados);
        return credenciales;
    }

    public Optional<Credentials> getByUserName(String username) {
        List<Credentials> credenciales = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM credenciales WHERE usuario = '" + username + "'";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCredencial(credenciales, resultados);
        return Optional.ofNullable(credenciales.get(0));
    }

    public Optional<String> getNombresPersona(String username) {
        String query = "select concat(persona.nombre, ' ', persona.apellido) from credenciales inner join empleado on credenciales.empleado_id= empleado.id inner join persona on persona.id = empleado.persona_id where credenciales.usuario = '" + username + "';";
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        return resultados.isEmpty() ? Optional.empty() : Optional.ofNullable(resultados.get(0)[0]);
    }
}
