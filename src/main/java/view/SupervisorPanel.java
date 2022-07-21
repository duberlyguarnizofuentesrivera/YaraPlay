package view;

import controller.SupervisorController;
import controller.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupervisorPanel extends JPanel implements PanelYara {
    final UserPanel mainPanel;


    public SupervisorPanel(UserPanel mainPanel) {
        super();
        this.mainPanel = mainPanel;
    }

    @Override
    public void createControls() {
        // Utilizado para extender controles sobre el panel principal
    }

    @Override
    public void createEvents() {
        mainPanel.btnSuperCreateShelf.addActionListener(e -> createShelf());
        mainPanel.btnSuperCreateCategory.addActionListener(e -> createCategory());
        mainPanel.btnSuperCreateSupplier.addActionListener(e -> createSupplier());
        mainPanel.btnSuperDeleteSupplier.addActionListener(e -> deleteSupplier());
        mainPanel.btnSuperDeleteCategory.addActionListener(e -> deleteCategory());
        mainPanel.btnSuperDeleteShelf.addActionListener(e -> deleteShelf());
        mainPanel.btnSuperViewShelves.addActionListener(e -> viewShelves());
        mainPanel.btnSuperViewCategories.addActionListener(e -> viewCategories());
        mainPanel.btnSuperViewSuppliers.addActionListener(e -> viewSuppliers());

    }



    private void viewSuppliers() {
        SupervisorController controller = new SupervisorController();
        String[][] datos = controller.viewSuppliers();
        String[] columnas = {"ID", "Razón Social"};
        mainPanel.lblResults.setText("Listado de proveedores");
        mainPanel.tblResults.setModel(new DefaultTableModel(datos, columnas));
    }


    private void viewCategories() {
        SupervisorController controller = new SupervisorController();
        String[][] datos = controller.viewCategories();
        String[] columnas = {"ID", "Nombre"};
        mainPanel.lblResults.setText("Listado de categorías");
        mainPanel.tblResults.setModel(new DefaultTableModel(datos, columnas));
    }

    private void viewShelves() {
        SupervisorController controller = new SupervisorController();
        String[][] datos = controller.viewShelves();
        String[] columnas = {"ID", "Piso", "Pasaje", "Nivel", "Capacidad"};
        mainPanel.lblResults.setText("Listado de anaqueles");
        mainPanel.tblResults.setModel(new DefaultTableModel(datos, columnas));
    }


    private void deleteSupplier() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de proveedores, luego ingrese el id del proveedor a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id de proveedor");
            } else {
                SupervisorController controller = new SupervisorController();
                if (controller.deleteSupplier(id)) {
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor");
                }
            }
        }
    }

    private void createSupplier() {
        NewSupplierDialog newSupplierDialog = new NewSupplierDialog();
        newSupplierDialog.setVisible(true);
    }

    private void createCategory() {
        NewCategoryDialog newCategoryDialog = new NewCategoryDialog();
    }

    private void deleteCategory() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de categorías, luego ingrese el id de la categoría a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                SupervisorController controller = new SupervisorController();
                if (controller.deleteCategory(id)) {
                    JOptionPane.showMessageDialog(null, "Categoría eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoría");
                }
            }
        }
    }

    private void createShelf() {
        NewShelfDialog dialog = new NewShelfDialog();
        dialog.setVisible(true);
    }

    private void deleteShelf() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de anaqueles, luego ingrese el id del anaquel a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                SupervisorController controller = new SupervisorController();
                if (controller.deleteShelf(id)) {
                    JOptionPane.showMessageDialog(null, "Anaquel eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el anaquel");
                }
            }
        }
    }


    @Override
    public void readConfig() {
        // Utilizado para extender configuraciones sobre el panel principal
    }


}

class NewShelfDialog extends JDialog {
    public NewShelfDialog() {
        super();
        setTitle("Nuevo Anaquel");
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        JTextField txtPiso = new JTextField(16);
        txtPiso.setBorder(BorderFactory.createTitledBorder("Piso (número)"));
        JTextField txtPasillo = new JTextField(16);
        txtPasillo.setBorder(BorderFactory.createTitledBorder("Pasillo (número)"));
        JTextField txtNivel = new JTextField(16);
        txtNivel.setBorder(BorderFactory.createTitledBorder("Nivel (número)"));
        JTextField txtCapacidad = new JTextField(16);
        txtCapacidad.setBorder(BorderFactory.createTitledBorder("Capacidad (Kg)"));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtPiso, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(txtPasillo, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtNivel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        add(txtCapacidad, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(btnAceptar, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(btnCancelar, constraints);
        pack();
        btnAceptar.addActionListener(e -> {
            String piso = txtPiso.getText();
            String pasillo = txtPasillo.getText();
            String nivel = txtNivel.getText();
            String capacidad = txtCapacidad.getText();
            if ("".equals(piso) || "".equals(pasillo) || "".equals(nivel) || "".equals(capacidad)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos");
            } else {
                SupervisorController controller = new SupervisorController();
                if (controller.createShelf(piso, pasillo, nivel, capacidad)) {
                    JOptionPane.showMessageDialog(null, "Anaquel creado");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el anaquel");
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}

class NewCategoryDialog {
    public NewCategoryDialog() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la nueva categoría: ");
        if (nombre != null) {
            if (nombre.length() > 0) {
                SupervisorController controller = new SupervisorController();
                if (controller.createCategory(nombre)) {
                    JOptionPane.showMessageDialog(null, "Categoría creada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear la categoría");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            }
        }
    }
}

class NewSupplierDialog extends JDialog {
    public NewSupplierDialog() {
        super();
        setTitle("Nuevo Proveedor");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        JTextField txtSupplierName = new JTextField(36);
        txtSupplierName.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtRuc = new JTextField(11);
        txtRuc.setBorder(BorderFactory.createTitledBorder("RUC (11 dígitos)"));

        JComboBox<String> cmbPersona = new JComboBox<>();
        cmbPersona.setBorder(BorderFactory.createTitledBorder("Contacto"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbPersona.setModel(model);
        UserController controller = new UserController();
        Map<Long, String> listaNombresYApellidos = controller.listNamesAndID();
        for (String nombres : listaNombresYApellidos.values()) {
            model.addElement(nombres);
        }
        List<Long> indices = new ArrayList<>(listaNombresYApellidos.keySet());
        JTextField txtSupplierAddress = new JTextField(36);
        txtSupplierAddress.setBorder(BorderFactory.createTitledBorder("Dirección"));
        JTextField txtSupplierPhone = new JTextField(9);
        txtSupplierPhone.setBorder(BorderFactory.createTitledBorder("Teléfono (9 dígitos)"));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtSupplierName, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtRuc, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(cmbPersona, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(txtSupplierAddress, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtSupplierPhone, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        add(btnAceptar, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        add(btnCancelar, constraints);
        this.pack();
        btnAceptar.addActionListener(e -> {
            String companyName = txtSupplierName.getText();
            String ruc = txtRuc.getText();
            String companyAddress = txtSupplierAddress.getText();
            String companyPhone = txtSupplierPhone.getText();
            Long idPersona = indices.get(cmbPersona.getSelectedIndex());
            if (!"".equals(companyName) && !"".equals(ruc) && !"".equals(companyAddress) && !"".equals(companyPhone)) {
                SupervisorController supervisorController = new SupervisorController();
                supervisorController.createSupplier(companyName, ruc, companyAddress, companyPhone, idPersona);
                JOptionPane.showMessageDialog(null, "Proveedor creado correctamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}
