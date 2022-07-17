package dao.impl;

import dao.Dao;
import dao.JdbcConnection;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements Dao<Employee> {


    @Override
    public Optional<Employee> get(long id) {
        List<Employee> employees = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM empleado WHERE id = " + id;
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToEmpleado(employees, resultados);
        if (!employees.isEmpty()) {
            return Optional.ofNullable(employees.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT * FROM empleado";
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToEmpleado(employees, resultados);
        return employees;
    }

    @Override
    public void save(Employee employee) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO empleado ( rol_id, persona_id, registro_acceso_id, registro_ingresos_id, registro_salidas_id) VALUES ("
                + employee.getRole().getId() + ", "
                + employee.getPerson().getId() + ", "
                + employee.getRegistroAcceso().getId() + ", "
                + employee.getRegistroIngresos().getId() + ", "
                + employee.getRegistroSalidas().getId() + ")";
        jdbcConnection.executeUpdate(query);
    }

    public void saveBasics(Employee employee) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "INSERT INTO empleado (rol_id, persona_id) VALUES ("
                + employee.getRole().getId() + ", "
                + employee.getPerson().getId() + ")";
        jdbcConnection.executeUpdate(query);
    }

    @Override
    public void update(Employee employee, String[] params) {
        List<Employee> employees = new ArrayList<>();
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "SELECT persona_id FROM empleado Where id = " + employee.getId();
        List<String[]> resultados = jdbcConnection.executeQuery(query);
        convertToEmpleado(employees, resultados);
    }

    @Override
    public void delete(Employee employee) {
        JdbcConnection jdbcConnection = new JdbcConnection();
        String query = "DELETE FROM empleado WHERE id = " + employee.getId();
        jdbcConnection.executeUpdate(query);
    }

    private void convertToEmpleado(List<Employee> employees, List<String[]> resultados) {
        for (String[] resultado : resultados) {
            RoleDaoImpl rolDao = new RoleDaoImpl();
            PersonDaoImpl personaDao = new PersonDaoImpl();
            RegistroAccesoDaoImpl registroAccesoDao = new RegistroAccesoDaoImpl();
            RegistroIngresosDaoImpl registroIngresosDao = new RegistroIngresosDaoImpl();
            RegistroSalidasDaoImpl registroSalidasDao = new RegistroSalidasDaoImpl();
            Employee employee = new Employee();
            employee.setId(Long.parseLong(resultado[0]));
            employee.setRole(rolDao.get(Long.parseLong(resultado[1])).orElse(null));
            if (resultado[2] != null) {
                employee.setPerson(personaDao.get(Long.parseLong(resultado[2])).orElse(null));
            }
            if (resultado[3] != null) {
                employee.setRegistroAcceso(registroAccesoDao.get(Long.parseLong(resultado[3])).orElse(null));
            }
            if (resultado[4] != null) {
                employee.setRegistroIngresos(registroIngresosDao.get(Long.parseLong(resultado[4])).orElse(null));
            }
            if (resultado[5] != null) {
                employee.setRegistroSalidas(registroSalidasDao.get(Long.parseLong(resultado[5])).orElse(null));
            }
            employees.add(employee);
        }
    }
}
