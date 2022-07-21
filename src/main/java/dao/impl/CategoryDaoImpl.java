package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements Dao<Category> {
    private static final Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);

    @Override
    public Optional<Category> get(long id) {
        List<Category> categories = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM categoria WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCategory(categories, resultados);
        return Optional.ofNullable(categories.get(0));
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM categoria");
        convertToCategory(categories, resultados);
        return categories;
    }

    @Override
    public void save(Category category) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO categoria (nombre) VALUES ('" + category.getName() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Category category, String[] params) {
        // para uso futuro
    }

    @Override
    public void delete(Category category) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM categoria WHERE id = " + category.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToCategory(List<Category> categories, List<String[]> results) {
        for (String[] result : results) {
            Category category = new Category();
            category.setId(Long.parseLong(result[0]));
            category.setName(result[1]);
            categories.add(category);
        }
    }

    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM categoria WHERE nombre LIKE '%" + name + "%'";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToCategory(categories, resultados);
        return categories;
    }

    public String getStock(long id) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT SUM(stock) FROM producto WHERE producto.categoria_id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        log.info("obteniendo stock de categoría. Total: {}", resultados.get(0)[0]);
        return resultados.get(0)[0];
    }
}
