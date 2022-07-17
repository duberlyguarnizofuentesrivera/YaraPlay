package controller;

import dao.impl.*;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminController extends SupervisorController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final StoreDaoImpl storeDao = new StoreDaoImpl();
    private final PersonDaoImpl personDao = new PersonDaoImpl();
    private final EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private final CredentialsDaoImpl credentialsDao = new CredentialsDaoImpl();

    public boolean deleteUser(long id) {
        try {
            Person person = personDao.get(id).orElse(null);
            personDao.delete(Objects.requireNonNull(person));
            return true;
        } catch (Exception e) {
            log.warn("Error al eliminar el usuario con id: {}", id);
            return false;
        }
    }

    public boolean createStore(String name, String address, String phone, Long personId) {
        try {
            Store store = new Store();
            store.setName(name);
            store.setAddress(address);
            store.setPhone(phone);
            store.setPersonName(personDao.get(personId).orElse(null));
            storeDao.save(store);
            return true;
        } catch (Exception e) {
            log.warn("Error al crear la sucursal: {}", e.getMessage());
            return false;
        }
    }

    public boolean createCredentials(String userName, String password, int empleadoId, int rolId) {
        try {
            Credentials credentials = new Credentials();
            credentials.setUserName(userName);
            credentials.setPassword(password);
            credentials.setEmployeeID(empleadoId);
            credentials.setRole(rolId);
            credentialsDao.save(credentials);
            return true;
        } catch (Exception e) {
            log.warn("Error al crear credenciales: {}", e.getMessage());
            return false;
        }
    }

    public String[][] listCredentials() {
        List<Credentials> credentials = credentialsDao.getAll();
        String[][] data = new String[credentials.size()][2];
        for (Credentials credencial : credentials) {
            data[credentials.indexOf(credencial)][0] = String.valueOf(credencial.getId());
            data[credentials.indexOf(credencial)][1] = credencial.getUserName();
        }
        return data;
    }

    public boolean deleteCredentials(long id) {
        try {
            credentialsDao.delete(Objects.requireNonNull(credentialsDao.get(id).orElse(null)));
            return true;
        } catch (Exception e) {
            log.warn("Error al eliminar credenciales con id {}", id);
            return false;
        }
    }

    public boolean deleteStore(Long id) {
        try {
            storeDao.delete(Objects.requireNonNull(storeDao.get(id).orElse(null)));
            return true;
        } catch (Exception e) {
            log.warn("Error al eliminar sucursal con id: {}", id);
            return false;
        }
    }

    public String[][] listStores() {
        List<Store> stores = storeDao.getAll();
        String[][] data = new String[stores.size()][3];
        for (Store store : stores) {
            data[stores.indexOf(store)][0] = String.valueOf(store.getId());
            data[stores.indexOf(store)][1] = store.getName();
            data[stores.indexOf(store)][2] = store.getPersonName().getName() + " " + store.getPersonName().getLastName();
        }
        return data;
    }

    public String[][] listUsers() {
        List<Person> people;
        people = personDao.getAll();
        String[][] data = new String[people.size()][4];
        for (Person person : people) {
            data[people.indexOf(person)][0] = String.valueOf(person.getId());
            data[people.indexOf(person)][1] = person.getName() + " " + person.getLastName();
            data[people.indexOf(person)][2] = person.getDni();
            data[people.indexOf(person)][3] = person.getPhone();
        }
        return data;
    }

    public Map<Long, String> listRoles() {
        RoleDaoImpl rolDao = new RoleDaoImpl();
        List<Role> roles = rolDao.getAll();
        Map<Long, String> data = new java.util.HashMap<>();
        for (Role role : roles) {
            data.put(role.getId(), role.getName());
        }
        return data;
    }

    public boolean crearUsuario(String[] identity, String phone, String email, String address, boolean isEmployee, long idRole) {
        try {
            RoleDaoImpl rolDao = new RoleDaoImpl();
            Person person = new Person();
            person.setName(identity[0]); //nombre
            person.setLastName(identity[1]); //apellido
            person.setDni(identity[2]); //dni
            person.setPhone(phone);
            person.setEmail(email);
            person.setAddress(address);
            personDao.save(person);
            if (isEmployee) {
                Employee employee = new Employee();
                employee.setPerson(personDao.getByDni(person.getDni()));
                employee.setRole(rolDao.get(idRole).orElse(null));
                employeeDao.saveBasics(employee);
            }
            return true;
        } catch (Exception e) {
            log.warn("Error al crear usuario: {}", e.getMessage());
            return false;
        }
    }

    public int[] credentialsExist(String username, String password) {
        int userAuthenticated = 0;
        int userID = 0;
        int userRole = 0; //1 = admin, 2 = usuario básico, 3 = supervisor
        Credentials credentials = credentialsDao.getByUserName(username).orElse(null);
        String pass = credentials != null ? credentials.getPassword() : "";
        if (pass != null && pass.equals(password)) {
            userAuthenticated = 1;
        }
        if (credentials != null) {
            Employee employee = employeeDao.get(credentials.getEmployeeID()).orElse(null);
            userRole = (int) (employee != null ? employee.getRole().getId() : 0);
        }
        log.info("userAuthenticated: {}", userAuthenticated);
        return new int[]{userAuthenticated, userID, userRole};
    }

    public String getCompleteNameFromUsername(String username) {
        CredentialsDaoImpl credencialesDao = new CredentialsDaoImpl();
        return credencialesDao.getNombresPersona(username).orElse("");
    }

    public String[][] listEmployees() {
        List<Employee> employees;
        employees = employeeDao.getAll();
        String[][] data = new String[employees.size()][4];
        for (Employee employee : employees) {
            data[employees.indexOf(employee)][0] = String.valueOf(employee.getId());
            Person p = personDao.get(employee.getPerson().getId()).orElse(null);
            String name = p != null ? p.getName() : "";
            String lastName = p != null ? p.getLastName() : "";
            data[employees.indexOf(employee)][1] = name + " " + lastName;
            data[employees.indexOf(employee)][2] = String.valueOf(employee.getRole().getId());
            data[employees.indexOf(employee)][3] = employee.getRole().getName();
        }
        return data;
    }
}
