package view;

import controller.AdminController;
import controller.UsuarioBasicoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelAdmin extends JPanel implements PanelYara {
    final PanelUsuario panelUsuario;

    public PanelAdmin(PanelUsuario panelUsuario) {
        this.panelUsuario = panelUsuario;
    }

    @Override
    public void crearControles() {
        // disponible para extender la aplicación agregando más controles según se necesite.
    }

    @Override
    public void crearEventos() {
        panelUsuario.btnAdminCrearSucursal.addActionListener(e -> crearSucursal());
        panelUsuario.btnAdminEliminarSucursal.addActionListener(e -> eliminarSucursal());
        panelUsuario.btnAdminVerSucursales.addActionListener(e -> listarSucursales());
        panelUsuario.btnAdminVerUsuarios.addActionListener(e -> listarUsuarios());
        panelUsuario.btnAdminCrearUsuario.addActionListener(e -> crearUsuario());
        panelUsuario.btnAdminEliminarUsuario.addActionListener(e -> eliminarUsuario());
        panelUsuario.btnAdminCrearCredenciales.addActionListener(e -> crearCredenciales());
        panelUsuario.btnAdminVerCredenciales.addActionListener(e -> verCredenciales());
        panelUsuario.btnAdminEliminarCredenciales.addActionListener(e -> eliminarCredenciales());

    }

    private void eliminarCredenciales() {
        String result = JOptionPane.showInputDialog("Busque el ID en el listado de credenciales, y luego ingrese el id de la credencial a eliminar");
        if (result != null) {
            try {
                long id = Long.parseLong(result);
                AdminController controller = new AdminController();
                if (controller.eliminarCredenciales(id)) {
                    JOptionPane.showMessageDialog(null, "Credencial eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la credencial");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar credenciales: " + e.getMessage());
            }
        }


    }

    private void verCredenciales() {
        AdminController controller = new AdminController();
        String[][] datos = controller.listarCredenciales();
        String[] columnas = {"ID", "Nombre de usuario"};
        panelUsuario.lblResultados.setText("Listado de credenciales");
        panelUsuario.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void crearCredenciales() {
        CrearCredencialesDialog crearCredencialesDialog = new CrearCredencialesDialog();
        crearCredencialesDialog.setVisible(true);
    }

    private void eliminarUsuario() {
        String id = JOptionPane.showInputDialog("Busque y luego ingrese el ID del usuario a eliminar");
        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID");
        } else {
            AdminController controller = new AdminController();
            if (controller.eliminarUsuario(Integer.parseInt(id))) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
            }
        }
    }

    private void crearUsuario() {
        CrearUsuarioDialog crearUsuarioDialog = new CrearUsuarioDialog();
        crearUsuarioDialog.setVisible(true);
    }

    private void listarUsuarios() {
        AdminController controller = new AdminController();
        String[][] datos = controller.listarUsuarios();
        String[] columnas = {"ID", "Nombres", "DNI", "Teléfono"};
        panelUsuario.lblResultados.setText("Listado de usuarios");
        panelUsuario.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void listarSucursales() {
        AdminController controller = new AdminController();
        String[][] datos = controller.listarSucursales();
        String[] columnas = {"ID", "Nombre", "Contacto"};
        panelUsuario.lblResultados.setText("Listado de sucursales");
        panelUsuario.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void eliminarSucursal() {
        String result = JOptionPane.showInputDialog("Busque el ID en el listado de sucursales, y luego ingrese el id de la sucursal a eliminar");
        if (result != null) {
            try {
                Long id = Long.parseLong(result);
                AdminController controller = new AdminController();
                if (controller.eliminarSucursal(id)) {
                    JOptionPane.showMessageDialog(null, "Sucursal eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la sucursal");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar sucursal: " + e.getMessage());
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
        UsuarioBasicoController controller = new UsuarioBasicoController();
        Map<Long, String> personasApellidosYId = controller.listarNombresYApellidosYId();
        for (String nombres : personasApellidosYId.values()) {
            model.addElement(nombres);
        }
        List<Long> indices = new ArrayList<>(personasApellidosYId.keySet());
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
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            long idPersona = indices.get(cmbPersona.getSelectedIndex());
            AdminController adminController = new AdminController();
            if (adminController.crearSucursal(nombre, direccion, telefono, idPersona)) {
                JOptionPane.showMessageDialog(null, "Sucursal creada");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la sucursal");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}

class CrearUsuarioDialog extends JDialog {
    public CrearUsuarioDialog() {
        super();
        setTitle("Nueva Persona");
        setLocationRelativeTo(null);
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
        AdminController controller = new AdminController();
        Map<Long, String> roles = controller.listarRoles();

        List<Long> indices = new ArrayList<>(roles.keySet());
        for (String rol : roles.values()) {
            model.addElement(rol);
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
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String dni = txtDni.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String direccion = txtDireccion.getText();
            boolean esEmpleado = chkEmpleado.isSelected();
            long idRol = indices.get(cmbRol.getSelectedIndex());
            if (!"".equals(nombre) && !"".equals(apellido) && !"".equals(dni) && !"".equals(telefono) && !"".equals(email) && !"".equals(direccion)) {
                AdminController adminController = new AdminController();
                if (adminController.crearUsuario(new String[]{nombre, apellido, dni}, telefono, email, direccion, esEmpleado, idRol)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el usuario");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            }
        });
    }
}

class CrearCredencialesDialog extends JDialog {
    public CrearCredencialesDialog() {
        super();
        setTitle("Nueva Credencial");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JTextField txtUserName = new JTextField(36);
        txtUserName.setBorder(BorderFactory.createTitledBorder("Nombre de usuario"));
        JPasswordField txtPassword = new JPasswordField(36);
        txtPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));

        JComboBox<String> cmbEmpleado = new JComboBox<>();
        cmbEmpleado.setBorder(BorderFactory.createTitledBorder("Empleado asociado"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbEmpleado.setModel(model);
        AdminController controller = new AdminController();
        String[][] empleados = controller.listarEmpleados();
        List<String> indicesEmpleadoID = new ArrayList<>();
        List<String> indicesRolID = new ArrayList<>();
        for (String[] empleado : empleados) {
            model.addElement(empleado[1] + " - " + empleado[3]);
            indicesEmpleadoID.add(empleado[0]);
            indicesRolID.add(empleado[2]);
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
        add(txtUserName, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtPassword, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(cmbEmpleado, constraints);
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
            String usuario = txtUserName.getText();
            String password = String.copyValueOf(txtPassword.getPassword());
            AdminController adminController = new AdminController();
            int empleadoID = Integer.parseInt(indicesEmpleadoID.get(cmbEmpleado.getSelectedIndex()));
            int rolID = Integer.parseInt(indicesRolID.get(cmbEmpleado.getSelectedIndex()));
            if (adminController.crearCredenciales(usuario, password, empleadoID, rolID)) {
                JOptionPane.showMessageDialog(null, "Credenciales creadas");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear las credenciales de acceso");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}