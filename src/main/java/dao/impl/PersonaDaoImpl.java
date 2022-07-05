package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Persona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaDaoImpl implements Dao<Persona> {
    @Override
    public Optional<Persona> get(long id) {
        List<Persona> personas = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM persona WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToPersona(personas, resultados);
        return Optional.ofNullable(personas.get(0));
    }

    @Override
    public List<Persona> getAll() {
        List<Persona> personas = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM persona");
        convertToPersona(personas, resultados);
        return personas;
    }

    @Override
    public void save(Persona persona) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO persona (nombre, apellido, dni, telefono, email, direccion, fechacreacion) VALUES"
                + " ('" + persona.getNombre() + "', '"
                + persona.getApellido() + "', '"
                + persona.getDni() + "', '"
                + persona.getTelefono() + "','"
                + persona.getEmail() + "', '"
                + persona.getDireccion() + "', '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Persona persona, String[] params) {

    }

    @Override
    public void delete(Persona persona) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM persona WHERE id = " + persona.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToPersona(List<Persona> personas, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Persona persona = new Persona();
            persona.setId(Long.parseLong(resultado[0]));
            persona.setNombre(resultado[1]);
            persona.setApellido(resultado[2]);
            persona.setDni(resultado[3]);
            persona.setTelefono(resultado[4]);
            persona.setEmail(resultado[5]);
            persona.setDireccion(resultado[6]);
            persona.setFechaCreacion(LocalDateTime.parse(resultado[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            personas.add(persona);
        }
    }
}
