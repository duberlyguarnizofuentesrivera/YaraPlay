package controller;

import dao.impl.*;
import model.*;
import util.Configuracion;
import util.Utilidades;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdminController extends AlmaceneroController {

    public boolean eliminarUsuario(long id) {
        try {
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            Persona persona = personaDao.get(id).orElse(null);
            personaDao.delete(Objects.requireNonNull(persona));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean crearSucursal(String nombre, String direccion, String telefono, Long personaId) {
        try {
            SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
            Sucursal sucursal = new Sucursal();
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            sucursal.setNombre(nombre);
            sucursal.setDireccion(direccion);
            sucursal.setTelefono(telefono);
            sucursal.setPersona(personaDao.get(personaId).orElse(null));
            sucursalDao.save(sucursal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean crearCredenciales(String userName, String password) {
        try {
            Credenciales credenciales = new Credenciales();
            CredencialesDaoImpl credencialesDao = new CredencialesDaoImpl();
            credenciales.setUserName(userName);
            credenciales.setPassword(password);
            credencialesDao.save(credenciales);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] listarCredenciales() {
        CredencialesDaoImpl credencialesDao = new CredencialesDaoImpl();
        List<Credenciales> credenciales = credencialesDao.getAll();
        String[][] datos = new String[credenciales.size()][2];
        for (Credenciales credencial : credenciales) {
            datos[credenciales.indexOf(credencial)][0] = String.valueOf(credencial.getId());
            datos[credenciales.indexOf(credencial)][1] = credencial.getUserName();
        }
        return datos;
    }

    public boolean eliminarCredenciales(long id) {
        try {
            CredencialesDaoImpl credencialesDao = new CredencialesDaoImpl();
            credencialesDao.delete(Objects.requireNonNull(credencialesDao.get(id).orElse(null)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarSucursal(Long id) {
        try {
            SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
            sucursalDao.delete(Objects.requireNonNull(sucursalDao.get(id).orElse(null)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String[][] listarSucursales() {
        SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
        List<Sucursal> sucursales = sucursalDao.getAll();
        String[][] datos = new String[sucursales.size()][3];
        for (Sucursal sucursal : sucursales) {
            datos[sucursales.indexOf(sucursal)][0] = String.valueOf(sucursal.getId());
            datos[sucursales.indexOf(sucursal)][1] = sucursal.getNombre();
            datos[sucursales.indexOf(sucursal)][2] = sucursal.getPersona().getNombre() + " " + sucursal.getPersona().getApellido();
        }
        return datos;
    }

    public String[][] listarUsuarios() {
        List<Persona> personas;
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        personas = personaDao.getAll();
        String[][] datos = new String[personas.size()][4];
        for (Persona persona : personas) {
            datos[personas.indexOf(persona)][0] = String.valueOf(persona.getId());
            datos[personas.indexOf(persona)][1] = persona.getNombre() + " " + persona.getApellido();
            datos[personas.indexOf(persona)][2] = persona.getDni();
            datos[personas.indexOf(persona)][3] = persona.getTelefono();
        }
        return datos;
    }

    public Map<Long, String> listarRoles() {
        RolDaoImpl rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.getAll();
        Map<Long, String> datos = new java.util.HashMap<>();
        for (Rol rol : roles) {
            datos.put(rol.getId(), rol.getNombre());
        }
        return datos;
    }

    public boolean crearUsuario(String[] identidad, String telefono, String email, String direccion, boolean esEmpleado, long idRol) {
        try {
            PersonaDaoImpl personaDao = new PersonaDaoImpl();
            RolDaoImpl rolDao = new RolDaoImpl();
            Persona persona = new Persona();
            persona.setNombre(identidad[0]); //nombre
            persona.setApellido(identidad[1]); //apellido
            persona.setDni(identidad[2]); //dni
            persona.setTelefono(telefono);
            persona.setEmail(email);
            persona.setDireccion(direccion);
            personaDao.save(persona);
            if (esEmpleado) {
                EmpleadoDaoImpl empleadoDao = new EmpleadoDaoImpl();
                Empleado empleado = new Empleado();
                empleado.setPersona(personaDao.getByDni(persona.getDni()));
                empleado.setRol(rolDao.get(idRol).orElse(null));
                empleadoDao.saveBasics(empleado);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
