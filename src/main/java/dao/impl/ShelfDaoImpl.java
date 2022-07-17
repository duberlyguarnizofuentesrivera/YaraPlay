package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShelfDaoImpl implements Dao<Shelf> {

    @Override
    public Optional<Shelf> get(long id) {
        List<Shelf> anaqueles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM anaquel WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToAnaquel(anaqueles, resultados);
        return Optional.ofNullable(anaqueles.get(0));
    }

    @Override
    public List<Shelf> getAll() {
        List<Shelf> anaqueles = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM anaquel");
        convertToAnaquel(anaqueles, resultados);
        return anaqueles;
    }

    @Override
    public void save(Shelf shelf) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO anaquel (capacidad, piso, pasillo, nivel) VALUES ('" + shelf.getCapacity() + "', '" + shelf.getFloor() + "', '" + shelf.getHallway() + "', '" + shelf.getLevel() + "')";
        jdbcConnection.executeUpdate(query);

    }

    @Override
    public void update(Shelf shelf, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(Shelf shelf) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM anaquel WHERE id = " + shelf.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToAnaquel(List<Shelf> anaqueles, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Shelf shelf = new Shelf();
            shelf.setId(Long.parseLong(resultado[0]));
            shelf.setCapacity(Integer.parseInt(resultado[1]));
            shelf.setFloor(Integer.parseInt(resultado[2]));
            shelf.setHallway(Integer.parseInt(resultado[3]));
            shelf.setLevel(Integer.parseInt(resultado[4]));
            anaqueles.add(shelf);
        }
    }
}
