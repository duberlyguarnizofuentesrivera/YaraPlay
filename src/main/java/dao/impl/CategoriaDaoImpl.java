package dao.impl;

import dao.Dao;
import model.Categoria;

import java.util.List;
import java.util.Optional;

public class CategoriaDaoImpl implements Dao<Categoria> {
    @Override
    public Optional<Categoria> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Categoria> getAll() {
        return null;
    }

    @Override
    public void save(Categoria categoria) {

    }

    @Override
    public void update(Categoria categoria, String[] params) {

    }

    @Override
    public void delete(Categoria categoria) {

    }
}
