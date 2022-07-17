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
        String query = "INSERT INTO credenciales (usuario, password, rol_id, empleado_id) VALUES ('" + credenciales.getUserName() + "', '" + credenciales.getPassword() + "','"+credenciales.getRol()+"','"+credenciales.getEmpleado()+"')";
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
            if (resultado[3] != null) {
                credencial.setRol(Integer.parseInt(resultado[3]));
            }
            if (resultado[4] != null) {
                credencial.setEmpleado(Integer.parseInt(resultado[4]));
            }
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

    public Optional<Credenciales> getByUserName(String username) {
        List<Credenciales> credenciales = new ArrayList<>();
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
