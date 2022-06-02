package dao.impl;

import dao.Dao;
import model.RegistroSalidas;

import java.util.List;
import java.util.Optional;

public class RegistroSalidasDaoImpl implements Dao<RegistroSalidas> {
    @Override
    public Optional<RegistroSalidas> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<RegistroSalidas> getAll() {
        return null;
    }

    @Override
    public void save(RegistroSalidas registroSalidas) {

    }

    @Override
    public void update(RegistroSalidas registroSalidas, String[] params) {

    }

    @Override
    public void delete(RegistroSalidas registroSalidas) {

    }
}
