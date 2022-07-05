package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.RegistroSalidas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroSalidasDaoImpl implements Dao<RegistroSalidas> {
    @Override
    public Optional<RegistroSalidas> get(long id) {
        List<RegistroSalidas> registroSalidas = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM registro_salidas WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRegistroSalidas(registroSalidas, resultados);
        return Optional.ofNullable(registroSalidas.get(0));
    }

    @Override
    public List<RegistroSalidas> getAll() {
        List<RegistroSalidas> registroSalidas = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM registro_salidas";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToRegistroSalidas(registroSalidas, resultados);
        return registroSalidas;
    }

    @Override
    public void save(RegistroSalidas registroSalidas) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO registro_salidas (sucursal_id, empleado_id, nombre_transportista, dni_transportista, cantidad, fecha, obs) VALUES ("
                + registroSalidas.getSucursal().getId() + ", "
                + registroSalidas.getEmpleado().getId() + ", " + "'"
                + registroSalidas.getNombreTransportista() + "', " + "'"
                + registroSalidas.getDniTransportista() + "', "
                + registroSalidas.getCantidad() + ", " + "'"
                + registroSalidas.getFecha() + "', " + "'"
                + registroSalidas.getObs() + "')";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(RegistroSalidas registroSalidas, String[] params) {

    }

    @Override
    public void delete(RegistroSalidas registroSalidas) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM registro_salidas WHERE id = " + registroSalidas.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToRegistroSalidas(List<RegistroSalidas> registroSalidas, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
            EmpleadoDaoImpl empleadoDao = new EmpleadoDaoImpl();
            RegistroSalidas registroSalida = new RegistroSalidas();
            registroSalida.setId(Long.parseLong(resultado[0]));
            registroSalida.setSucursal(sucursalDao.get(Long.parseLong(resultado[1])).get());
            registroSalida.setEmpleado(empleadoDao.get(Long.parseLong(resultado[2])).get());
            registroSalida.setNombreTransportista(resultado[3]);
            registroSalida.setDniTransportista(resultado[4]);
            registroSalida.setCantidad(Double.parseDouble(resultado[5]));
            registroSalida.setFecha(LocalDateTime.parse(resultado[6]));
            registroSalida.setObs(resultado[7]);
            registroSalidas.add(registroSalida);
        }
    }
}
