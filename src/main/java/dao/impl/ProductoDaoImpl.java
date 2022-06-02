package dao.impl;

import dao.Dao;
import model.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoDaoImpl implements Dao<Producto> {

        @Override
        public Optional<Producto> get(long id) {
            return Optional.empty();
        }

        @Override
        public List<Producto> getAll() {
            return null;
        }

        @Override
        public void save(Producto producto) {

        }

        @Override
        public void update(Producto producto, String[] params) {

        }

        @Override
        public void delete(Producto producto) {

        }
}
