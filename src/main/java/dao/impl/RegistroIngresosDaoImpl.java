package dao.impl;

import dao.Dao;
import model.RegistroIngresos;

import java.util.List;
import java.util.Optional;

public class RegistroIngresosDaoImpl implements Dao<RegistroIngresos> {
    @Override
    public Optional<RegistroIngresos> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<RegistroIngresos> getAll() {
        return null;
    }

    @Override
    public void save(RegistroIngresos registroIngreso) {

    }

    @Override
    public void update(RegistroIngresos registroIngreso, String[] params) {

    }

    @Override
    public void delete(RegistroIngresos registroIngreso) {

    }
}
