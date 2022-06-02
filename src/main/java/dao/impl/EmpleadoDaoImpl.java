package dao.impl;

import dao.Dao;
import model.Empleado;

import java.util.List;
import java.util.Optional;

public class EmpleadoDaoImpl implements Dao<Empleado> {


    @Override
    public Optional<Empleado> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Empleado> getAll() {
        return null;
    }

    @Override
    public void save(Empleado empleado) {

    }

    @Override
    public void update(Empleado empleado, String[] params) {

    }

    @Override
    public void delete(Empleado empleado) {

    }
}
