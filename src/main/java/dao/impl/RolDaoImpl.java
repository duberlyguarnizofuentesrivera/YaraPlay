package dao.impl;

import dao.Dao;
import model.Rol;

import java.util.List;
import java.util.Optional;

public class RolDaoImpl implements Dao<Rol> {

    @Override
    public Optional<Rol> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Rol> getAll() {
        return null;
    }

    @Override
    public void save(Rol rol) {

    }

    @Override
    public void update(Rol rol, String[] params) {

    }

    @Override
    public void delete(Rol rol) {

    }
}
