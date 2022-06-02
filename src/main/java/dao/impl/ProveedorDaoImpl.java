package dao.impl;

import dao.Dao;
import model.Proveedor;

import java.util.List;
import java.util.Optional;

public class ProveedorDaoImpl implements Dao<Proveedor> {
    @Override
    public Optional<Proveedor> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Proveedor> getAll() {
        return null;
    }

    @Override
    public void save(Proveedor proveedor) {

    }

    @Override
    public void update(Proveedor proveedor, String[] params) {

    }

    @Override
    public void delete(Proveedor proveedor) {

    }
}
