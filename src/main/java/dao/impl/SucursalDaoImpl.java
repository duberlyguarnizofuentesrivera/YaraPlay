package dao.impl;

import dao.Dao;
import model.Sucursal;

import java.util.List;
import java.util.Optional;

public class SucursalDaoImpl implements Dao<Sucursal> {

    @Override
    public Optional<Sucursal> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Sucursal> getAll() {
        return null;
    }

    @Override
    public void save(Sucursal sucursal) {

    }

    @Override
    public void update(Sucursal sucursal, String[] params) {

    }

    @Override
    public void delete(Sucursal sucursal) {

    }
}
