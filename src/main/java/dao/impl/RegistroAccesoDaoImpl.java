package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.RegistroAcceso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroAccesoDaoImpl implements Dao<RegistroAcceso> {

    @Override
    public Optional<RegistroAcceso> get(long id) {
        List<RegistroAcceso> registroAccesos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM registro_acceso WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRegistroAcceso(registroAccesos, resultados);
        return Optional.ofNullable(registroAccesos.get(0));
    }

    @Override
    public List<RegistroAcceso> getAll() {
        List<RegistroAcceso> registroAccesos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM registro_acceso");
        convertToRegistroAcceso(registroAccesos, resultados);
        return registroAccesos;
    }

    @Override
    public void save(RegistroAcceso registroAcceso) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO registro_acceso (fecha_login, fecha_logout) VALUES ('"
                + registroAcceso.getFechaLogin() + "', '"
                + registroAcceso.getFechaLogout() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(RegistroAcceso registroAcceso, String[] params) {

    }

    @Override
    public void delete(RegistroAcceso registroAcceso) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM registro_acceso WHERE id = " + registroAcceso.getId();
        jdbcConnection.executeUpdate(query);
    }


    private void convertToRegistroAcceso(List<RegistroAcceso> registroAccesos, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            RegistroAcceso registroAcceso = new RegistroAcceso();
            registroAcceso.setId(Long.parseLong(resultado[0]));
            registroAcceso.setFechaLogin(LocalDateTime.parse(resultado[1]));
            registroAcceso.setFechaLogout(LocalDateTime.parse(resultado[2]));
            registroAccesos.add(registroAcceso);
        }
    }
}
