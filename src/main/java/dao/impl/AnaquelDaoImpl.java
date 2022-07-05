package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Anaquel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnaquelDaoImpl implements Dao<Anaquel> {

    @Override
    public Optional<Anaquel> get(long id) {
        List<Anaquel> anaqueles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM anaquel WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToAnaquel(anaqueles, resultados);
        return Optional.ofNullable(anaqueles.get(0));
    }

    @Override
    public List<Anaquel> getAll() {
        List<Anaquel> anaqueles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM anaquel");
        convertToAnaquel(anaqueles, resultados);
        return anaqueles;
    }

    @Override
    public void save(Anaquel anaquel) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO anaquel (capacidad, piso, pasillo, nivel) VALUES ('" + anaquel.getCapacidad() + "', '" + anaquel.getPiso() + "', '" + anaquel.getPasillo() + "', '" + anaquel.getNivel() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Anaquel anaquel, String[] params) {

    }

    @Override
    public void delete(Anaquel anaquel) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM anaquel WHERE id = " + anaquel.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToAnaquel(List<Anaquel> anaqueles, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Anaquel anaquel = new Anaquel();
            anaquel.setId(Long.parseLong(resultado[0]));
            anaquel.setCapacidad(Integer.parseInt(resultado[1]));
            anaquel.setPiso(Integer.parseInt(resultado[2]));
            anaquel.setPasillo(Integer.parseInt(resultado[3]));
            anaquel.setNivel(Integer.parseInt(resultado[4]));
            anaqueles.add(anaquel);
        }
    }
}
