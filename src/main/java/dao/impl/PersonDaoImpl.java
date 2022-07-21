package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements Dao<Person> {
    @Override
    public Optional<Person> get(long id) {
        List<Person> people = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM persona WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToPersona(people, resultados);
        return Optional.ofNullable(people.get(0));
    }

    @Override
    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM persona");
        convertToPersona(people, resultados);
        return people;
    }
    public Person getByDni(String dni) {
        List<Person> people = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM persona WHERE dni = '" + dni + "'");
        convertToPersona(people, resultados);
        return people.get(0);
    }

    @Override
    public void save(Person person) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO persona (nombre, apellido, dni, telefono, email, direccion, fechacreacion) VALUES"
                + " ('" + person.getName() + "', '"
                + person.getLastName() + "', '"
                + person.getDni() + "', '"
                + person.getPhone() + "','"
                + person.getEmail() + "', '"
                + person.getAddress() + "', '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Person person, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(Person person) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM persona WHERE id = " + person.getId();
        jdbcConnection.executeUpdate(query);
    }


    private void convertToPersona(List<Person> people, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Person person = new Person();
            person.setId(Long.parseLong(resultado[0]));
            person.setName(resultado[1]);
            person.setLastName(resultado[2]);
            person.setDni(resultado[3]);
            person.setPhone(resultado[4]);
            person.setEmail(resultado[5]);
            person.setAddress(resultado[6]);
            person.setCreationDate(LocalDateTime.parse(resultado[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            people.add(person);
        }
    }
}
