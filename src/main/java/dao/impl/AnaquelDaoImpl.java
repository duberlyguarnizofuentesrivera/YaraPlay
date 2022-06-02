package dao.impl;

import dao.Dao;
import model.Anaquel;

import java.util.List;
import java.util.Optional;

public class AnaquelDaoImpl implements Dao<Anaquel> {

    @Override
    public Optional<Anaquel> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Anaquel> getAll() {
        return null;
    }

    @Override
    public void save(Anaquel anaquel) {

    }

    @Override
    public void update(Anaquel anaquel, String[] params) {

    }

    @Override
    public void delete(Anaquel anaquel) {

    }
}
