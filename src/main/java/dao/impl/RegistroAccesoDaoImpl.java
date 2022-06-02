package dao.impl;

import dao.Dao;
import model.RegistroAcceso;

import java.util.List;
import java.util.Optional;

public class RegistroAccesoDaoImpl implements Dao<RegistroAcceso> {

    @Override
    public Optional<RegistroAcceso> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<RegistroAcceso> getAll() {
        return null;
    }

    @Override
    public void save(RegistroAcceso registroAcceso) {

    }

    @Override
    public void update(RegistroAcceso registroAcceso, String[] params) {

    }

    @Override
    public void delete(RegistroAcceso registroAcceso) {

    }

}
