package view;

import controller.AdminController;
import controller.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminPanel extends JPanel implements PanelYara {
    final UserPanel userPanel;

    public AdminPanel(UserPanel userPanel) {
        this.userPanel = userPanel;
    }

    @Override
    public void createControls() {
        // disponible para extender la aplicación agregando más controles según se necesite.
    }

    @Override
    public void createEvents() {
        userPanel.btnAdminCreateStore.addActionListener(e -> createStore());
        userPanel.btnAdminDeleteStore.addActionListener(e -> deleteStore());
        userPanel.btnAdminViewStores.addActionListener(e -> listStores());
        userPanel.btnAdminViewUsers.addActionListener(e -> listUsers());
        userPanel.btnAdminCreateUser.addActionListener(e -> createUser());
        userPanel.btnAdminDeleteUser.addActionListener(e -> deleteUser());
        userPanel.btnAdminCreateCredentials.addActionListener(e -> createCredentials());
        userPanel.btnAdminViewCredentials.addActionListener(e -> viewCredentials());
        userPanel.btnAdminDeleteCredentials.addActionListener(e -> deleteCredentials());

    }

    private void deleteCredentials() {
        String result = JOptionPane.showInputDialog("Busque el ID en el listado de credenciales, y luego ingrese el id de la credencial a eliminar");
        if (result != null) {
            try {
                long id = Long.parseLong(result);
                AdminController controller = new AdminController();
                if (controller.deleteCredentials(id)) {
                    JOptionPane.showMessageDialog(null, "Credencial eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la credencial");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar credenciales: " + e.getMessage());
            }
        }


    }

    private void viewCredentials() {
        AdminController controller = new AdminController();
        String[][] data = controller.listCredentials();
        String[] columns = {"ID", "Nombre de usuario"};
        userPanel.lblResults.setText("Listado de credenciales");
        userPanel.tblResults.setModel(new DefaultTableModel(data, columns));
    }

    private void createCredentials() {
        NewCredentialsDialog newCredentialsDialog = new NewCredentialsDialog();
        newCredentialsDialog.setVisible(true);
    }

    private void deleteUser() {
        String id = JOptionPane.showInputDialog("Busque y luego ingrese el ID del usuario a eliminar");
        if ("".equals(id)) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un ID");
        } else {
            AdminController controller = new AdminController();
            try {
                if (controller.deleteUser(Integer.parseInt(id))) {
                    JOptionPane.showMessageDialog(null, "Usuario eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID ingresado no tiene el formato correcto: ");
            }
        }
    }

    private void createUser() {
        NewUserDialog newUserDialog = new NewUserDialog();
        newUserDialog.setVisible(true);
    }

    private void listUsers() {
        AdminController controller = new AdminController();
        String[][] data = controller.listUsers();
        String[] columns = {"ID", "Nombres", "DNI", "Teléfono"};
        userPanel.lblResults.setText("Listado de usuarios");
        userPanel.tblResults.setModel(new DefaultTableModel(data, columns));
    }

    private void listStores() {
        AdminController controller = new AdminController();
        String[][] data = controller.listStores();
        String[] columns = {"ID", "Nombre", "Contacto"};
        userPanel.lblResults.setText("Listado de sucursales");
        userPanel.tblResults.setModel(new DefaultTableModel(data, columns));
    }

    private void deleteStore() {
        String result = JOptionPane.showInputDialog("Busque el ID en el listado de sucursales, y luego ingrese el id de la sucursal a eliminar");
        if (result != null) {
            try {
                Long id = Long.parseLong(result);
                AdminController controller = new AdminController();
                if (controller.deleteStore(id)) {
                    JOptionPane.showMessageDialog(null, "Sucursal eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la sucursal");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar sucursal: " + e.getMessage());
            }
        }
    }

    private void createStore() {
        NewStoreDialog newStoreDialog = new NewStoreDialog();
        newStoreDialog.setVisible(true);
    }


    @Override
    public void readConfig() {
        //para uso futuro
    }
}

class NewStoreDialog extends JDialog {
    public NewStoreDialog() {
        super();
        setTitle("Nueva Sucursal");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JTextField txtNombre = new JTextField(36);
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtStoreAddress = new JTextField(36);
        txtStoreAddress.setBorder(BorderFactory.createTitledBorder("Dirección"));
        JTextField txtStorePhone = new JTextField(9);
        txtStorePhone.setBorder(BorderFactory.createTitledBorder("Teléfono"));
        JComboBox<String> cmbPersona = new JComboBox<>();
        cmbPersona.setBorder(BorderFactory.createTitledBorder("Contacto"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbPersona.setModel(model);
        UserController controller = new UserController();
        Map<Long, String> personasApellidosYId = controller.listNamesAndID();
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
        add(txtStoreAddress, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(txtStorePhone, constraints);
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
            String name = txtNombre.getText();
            String address = txtStoreAddress.getText();
            String phone = txtStorePhone.getText();
            long idPersona = indices.get(cmbPersona.getSelectedIndex());
            AdminController adminController = new AdminController();
            if (adminController.createStore(name, address, phone, idPersona)) {
                JOptionPane.showMessageDialog(null, "Sucursal creada");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la sucursal");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}

class NewUserDialog extends JDialog {
    public NewUserDialog() {
        super();
        setTitle("Nueva Persona");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JTextField txtPersonName = new JTextField(36);
        txtPersonName.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtPersonLastName = new JTextField(36);
        txtPersonLastName.setBorder(BorderFactory.createTitledBorder("Apellido"));
        JTextField txtPersonDNI = new JTextField(8);
        txtPersonDNI.setBorder(BorderFactory.createTitledBorder("DNI"));
        JTextField txtPersonPhone = new JTextField(9);
        txtPersonPhone.setBorder(BorderFactory.createTitledBorder("Teléfono"));
        JTextField txtPersonEmail = new JTextField(36);
        txtPersonEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        JTextField txtPersonAddress = new JTextField(36);
        txtPersonAddress.setBorder(BorderFactory.createTitledBorder("Dirección"));

        JCheckBox chkIsEmployee = new JCheckBox("Es usuario?");
        JComboBox<String> cmbRole = new JComboBox<>();
        cmbRole.setBorder(BorderFactory.createTitledBorder("Rol"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbRole.setModel(model);
        AdminController controller = new AdminController();
        Map<Long, String> roles = controller.listRoles();

        List<Long> indices = new ArrayList<>(roles.keySet());
        for (String role : roles.values()) {
            model.addElement(role);
        }
        cmbRole.setEnabled(false);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(txtPersonName, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(txtPersonLastName, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtPersonDNI, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtPersonPhone, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(txtPersonEmail, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        add(txtPersonAddress, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(chkIsEmployee, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(cmbRole, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        add(btnAceptar, constraints);
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        add(btnCancelar, constraints);
        pack();
        chkIsEmployee.addActionListener(e -> cmbRole.setEnabled(chkIsEmployee.isSelected()));
        btnCancelar.addActionListener(e -> dispose());
        btnAceptar.addActionListener(e -> {
            String name = txtPersonName.getText();
            String lastName = txtPersonLastName.getText();
            String dni = txtPersonDNI.getText();
            String phone = txtPersonPhone.getText();
            String email = txtPersonEmail.getText();
            String address = txtPersonAddress.getText();
            boolean isEmployee = chkIsEmployee.isSelected();
            long idRole = indices.get(cmbRole.getSelectedIndex());
            if (!"".equals(name) && !"".equals(lastName) && !"".equals(dni) && !"".equals(phone) && !"".equals(email) && !"".equals(address)) {
                AdminController adminController = new AdminController();
                if (adminController.crearUsuario(new String[]{name, lastName, dni}, phone, email, address, isEmployee, idRole)) {
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

class NewCredentialsDialog extends JDialog {
    public NewCredentialsDialog() {
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
        String[][] empleados = controller.listEmployees();
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
            if (adminController.createCredentials(usuario, password, empleadoID, rolID)) {
                JOptionPane.showMessageDialog(null, "Credenciales creadas");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear las credenciales de acceso");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}