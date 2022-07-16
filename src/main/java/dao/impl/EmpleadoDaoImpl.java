package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDaoImpl implements Dao<Empleado> {


    @Override
    public Optional<Empleado> get(long id) {
        List<Empleado> empleados = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM empleado WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToEmpleado(empleados, resultados);
        return Optional.ofNullable(empleados.get(0));
    }

    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM empleado";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToEmpleado(empleados, resultados);
        return empleados;
    }

    @Override
    public void save(Empleado empleado) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO empleado ( rol_id, persona_id, registro_acceso_id, registro_ingresos_id, registro_salidas_id) VALUES ("
                + empleado.getRol().getId() + ", "
                + empleado.getPersona().getId() + ", "
                + empleado.getRegistroAcceso().getId() + ", "
                + empleado.getRegistroIngresos().getId() + ", "
                + empleado.getRegistroSalidas().getId() + ")";
        jdbcConnection.executeUpdate(query);
    }

    public void saveBasics(Empleado empleado) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO empleado (rol_id, persona_id) VALUES ("
                + empleado.getRol().getId() + ", "
                + empleado.getPersona().getId() + ")";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Empleado empleado, String[] params) {

    }

    @Override
    public void delete(Empleado empleado) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM empleado WHERE id = " + empleado.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToEmpleado(List<Empleado> empleados, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            RolDaoImpl rolDao = new RolDaoImpl();
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            RegistroAccesoDaoImpl registroAccesoDao = new RegistroAccesoDaoImpl();
            RegistroIngresosDaoImpl registroIngresosDao = new RegistroIngresosDaoImpl();
            RegistroSalidasDaoImpl registroSalidasDao = new RegistroSalidasDaoImpl();
            Empleado empleado = new Empleado();
            empleado.setId(Long.parseLong(resultado[0]));
            empleado.setRol(rolDao.get(Long.parseLong(resultado[1])).orElse(null));
            empleado.setPersona(personaDao.get(Long.parseLong(resultado[2])).orElse(null));
            empleado.setRegistroAcceso(registroAccesoDao.get(Long.parseLong(resultado[3])).orElse(null));
            empleado.setRegistroIngresos(registroIngresosDao.get(Long.parseLong(resultado[4])).orElse(null));
            empleado.setRegistroSalidas(registroSalidasDao.get(Long.parseLong(resultado[5])).orElse(null));
            empleados.add(empleado);
        }
    }
}
