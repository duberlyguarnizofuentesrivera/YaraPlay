package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.RegistroIngresos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroIngresosDaoImpl implements Dao<RegistroIngresos> {
    @Override
    public Optional<RegistroIngresos> get(long id) {
        List<RegistroIngresos> registroIngresos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM registro_ingresos WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRegistroIngresos(registroIngresos, resultados);
        return Optional.ofNullable(registroIngresos.get(0));
    }

    @Override
    public List<RegistroIngresos> getAll() {
        List<RegistroIngresos> registroIngresos = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        List<String[]> resultados = jdbcConnection.executeQuery("SELECT * FROM registro_ingresos");
        convertToRegistroIngresos(registroIngresos, resultados);
        return registroIngresos;
    }

    @Override
    public void save(RegistroIngresos registroIngreso) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO registro_ingresos (empleado_id, nombre_transportista, dni_transportista, cantidad, fecha, obs) VALUES ("
                + registroIngreso.getEmployee().getId() + ", '"
                + registroIngreso.getNombreTransportista() + "', '"
                + registroIngreso.getDniTransportista() + "', "
                + registroIngreso.getCantidad() + ", '"
                + registroIngreso.getFecha() + "', '"
                + registroIngreso.getObs() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(RegistroIngresos registroIngreso, String[] params) {
        //para uso futuro
    }

    @Override
    public void delete(RegistroIngresos registroIngreso) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM registro_ingresos WHERE id = " + registroIngreso.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToRegistroIngresos(List<RegistroIngresos> registroIngresos, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            EmployeeDaoImpl empleadoDao = new EmployeeDaoImpl();
            RegistroIngresos registroIngreso = new RegistroIngresos();
            registroIngreso.setId(Long.parseLong(resultado[0]));
            registroIngreso.setEmployee(empleadoDao.get(Long.parseLong(resultado[1])).orElse(null));
            registroIngreso.setNombreTransportista(resultado[2]);
            registroIngreso.setDniTransportista(resultado[3]);
            registroIngreso.setCantidad(Double.parseDouble(resultado[4]));
            registroIngreso.setFecha(LocalDateTime.parse(resultado[5]));
            registroIngreso.setObs(resultado[6]);
            registroIngresos.add(registroIngreso);
        }
    }
}
