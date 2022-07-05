package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDaoImpl implements Dao<Categoria> {
    @Override
    public Optional<Categoria> get(long id) {
        List<Categoria> categorias = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM categoria WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCategoria(categorias, resultados);
        return Optional.ofNullable(categorias.get(0));
    }

    @Override
    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM categoria");
        convertToCategoria(categorias, resultados);
        return categorias;
    }

    @Override
    public void save(Categoria categoria) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO categoria (nombre) VALUES ('" + categoria.getNombre() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Categoria categoria, String[] params) {

    }

    @Override
    public void delete(Categoria categoria) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM categoria WHERE id = " + categoria.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToCategoria(List<Categoria> categorias, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            Categoria categoria = new Categoria();
            categoria.setId(Long.parseLong(resultado[0]));
            categoria.setNombre(resultado[1]);
            categorias.add(categoria);
        }
    }
}
