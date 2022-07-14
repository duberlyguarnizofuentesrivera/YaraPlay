package view;

import dao.impl.EmpleadoDaoImpl;
import dao.impl.PersonaDaoImpl;
import dao.impl.RolDaoImpl;
import dao.impl.SucursalDaoImpl;
import model.Empleado;
import model.Persona;
import model.Rol;
import model.Sucursal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelAdmin extends JPanel implements PanelYara {
    PanelUsuario panelUsuario;

    public PanelAdmin(PanelUsuario panelUsuario) {
        this.panelUsuario = panelUsuario;
    }


    @Override
    public void crearControles() {

    }

    @Override
    public void crearEventos() {
        panelUsuario.btnAdminCrearSucursal.addActionListener(e -> crearSucursal());
        panelUsuario.btnAdminEliminarSucursal.addActionListener(e -> eliminarSucursal());
        panelUsuario.btnAdminVerSucursales.addActionListener(e -> listarSucursales());
        panelUsuario.btnAdminVerUsuarios.addActionListener(e -> listarUsuarios());
        panelUsuario.btnAdminCrearUsuario.addActionListener(e -> crearUsuario());

    }

    private void crearUsuario() {
        CrearUsuarioDialog crearUsuarioDialog = new CrearUsuarioDialog();
        crearUsuarioDialog.setVisible(true);
    }

    private void listarUsuarios() {
        List<Persona> personas;
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        personas = personaDao.getAll();
        String[] columnas = {"ID", "Nombres", "DNI", "Teléfono"};
        String[][] datos = new String[personas.size()][4];
        for (Persona persona : personas) {
            datos[personas.indexOf(persona)][0] = String.valueOf(persona.getId());
            datos[personas.indexOf(persona)][1] = persona.getNombre() + " " + persona.getApellido();
            datos[personas.indexOf(persona)][2] = persona.getDni();
            datos[personas.indexOf(persona)][3] = persona.getTelefono();
        }
        panelUsuario.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void listarSucursales() {
        SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
        List<Sucursal> sucursales = sucursalDao.getAll();
        String[] columnas = {"ID", "Nombre", "Contacto"};
        String[][] datos = new String[sucursales.size()][3];
        for (Sucursal sucursal : sucursales) {
            datos[sucursales.indexOf(sucursal)][0] = String.valueOf(sucursal.getId());
            datos[sucursales.indexOf(sucursal)][1] = sucursal.getNombre();
            datos[sucursales.indexOf(sucursal)][2] = sucursal.getPersona().getNombre() + " " + sucursal.getPersona().getApellido();
        }
        panelUsuario.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void eliminarSucursal() {
        String result = JOptionPane.showInputDialog("Busque el ID en el listado de sucursales, y luego ingrese el id de la sucursal a eliminar");
        if (result != null) {
            Long id = Long.parseLong(result);
            if (id != null) {
                SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
                sucursalDao.delete(sucursalDao.get(id).get());
            }
        }
    }

    private void crearSucursal() {
        NuevaSucursalDialog nuevaSucursalDialog = new NuevaSucursalDialog();
        nuevaSucursalDialog.setVisible(true);

    }


    @Override
    public void leerConfiguracion() {

    }
}

class NuevaSucursalDialog extends JDialog {
    public NuevaSucursalDialog() {
        super();
        setTitle("Nueva Sucursal");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JTextField txtNombre = new JTextField(36);
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtDireccion = new JTextField(36);
        txtDireccion.setBorder(BorderFactory.createTitledBorder("Dirección"));
        JTextField txtTelefono = new JTextField(9);
        txtTelefono.setBorder(BorderFactory.createTitledBorder("Teléfono"));
        JComboBox<String> cmbPersona = new JComboBox<>();
        cmbPersona.setBorder(BorderFactory.createTitledBorder("Contacto"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbPersona.setModel(model);
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        List<Persona> personas = personaDao.getAll();
        List<Long> indices = new ArrayList<>();
        for (Persona persona : personas) {
            model.addElement(persona.getNombre() + " " + persona.getApellido());
            indices.add(persona.getId());
        }
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtNombre, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(cmbPersona, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtDireccion, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(txtTelefono, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(btnAceptar, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(btnCancelar, constraints);
        pack();
        btnAceptar.addActionListener(e -> {
            SucursalDaoImpl sucursalDao = new SucursalDaoImpl();
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(txtNombre.getText());
            sucursal.setDireccion(txtDireccion.getText());
            sucursal.setTelefono(txtTelefono.getText());
            sucursal.setPersona(personaDao.get(indices.get(cmbPersona.getSelectedIndex())).get());
            sucursalDao.save(sucursal);
            dispose();
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}

class CrearUsuarioDialog extends JDialog {
    public CrearUsuarioDialog() {
        super();
        setTitle("Nueva Persona");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JTextField txtNombre = new JTextField(36);
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtApellido = new JTextField(36);
        txtApellido.setBorder(BorderFactory.createTitledBorder("Apellido"));
        JTextField txtDni = new JTextField(8);
        txtDni.setBorder(BorderFactory.createTitledBorder("DNI"));
        JTextField txtTelefono = new JTextField(9);
        txtTelefono.setBorder(BorderFactory.createTitledBorder("Teléfono"));
        JTextField txtEmail = new JTextField(36);
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        JTextField txtDireccion = new JTextField(36);
        txtDireccion.setBorder(BorderFactory.createTitledBorder("Dirección"));

        JCheckBox chkEmpleado = new JCheckBox("Es usuario?");
        JComboBox<String> cmbRol = new JComboBox<>();
        cmbRol.setBorder(BorderFactory.createTitledBorder("Rol"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbRol.setModel(model);
        RolDaoImpl rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.getAll();
        List<Long> indices = new ArrayList<>();
        for (Rol rol : roles) {
            model.addElement(rol.getNombre());
            indices.add(rol.getId());
        }
        cmbRol.setEnabled(false);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(txtNombre, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(txtApellido, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtDni, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtTelefono, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(txtEmail, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        add(txtDireccion, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(chkEmpleado, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(cmbRol, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        add(btnAceptar, constraints);
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        add(btnCancelar, constraints);
        pack();
        chkEmpleado.addActionListener(e -> cmbRol.setEnabled(chkEmpleado.isSelected()));
        btnCancelar.addActionListener(e -> dispose());
        btnAceptar.addActionListener(e -> {
            if (!"".equals(txtNombre) && !"".equals(txtApellido) && !"".equals(txtDni) && !"".equals(txtTelefono) && !"".equals(txtEmail) && !"".equals(txtDireccion)) {
                PersonaDaoImpl personaDao = new PersonaDaoImpl();
                Persona persona = new Persona();
                persona.setNombre(txtNombre.getText());
                persona.setApellido(txtApellido.getText());
                persona.setDni(txtDni.getText());
                persona.setTelefono(txtTelefono.getText());
                persona.setEmail(txtEmail.getText());
                persona.setDireccion(txtDireccion.getText());
                personaDao.save(persona);
                if (chkEmpleado.isSelected()) {
                    EmpleadoDaoImpl empleadoDao = new EmpleadoDaoImpl();
                    Empleado empleado = new Empleado();
                    empleado.setPersona(personaDao.getByDni(persona.getDni()));
                    empleado.setRol(rolDao.get(indices.get(cmbRol.getSelectedIndex())).get());
                    empleadoDao.saveBasics(empleado);
                }
                JOptionPane.showMessageDialog(this, "Persona creada correctamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            }


        });
    }
}