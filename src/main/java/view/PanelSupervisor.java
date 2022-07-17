package view;

import controller.AlmaceneroController;
import controller.UsuarioBasicoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelSupervisor extends JPanel implements PanelYara {
    final PanelUsuario mainPanel;


    public PanelSupervisor(PanelUsuario mainPanel) {
        super();
        this.mainPanel = mainPanel;
    }

    @Override
    public void crearControles() {
        // Utilizado para extender controles sobre el panel principal
    }

    @Override
    public void crearEventos() {
        mainPanel.btnSuperCrearAnaquel.addActionListener(e -> crearAnaquel());
        mainPanel.btnSuperCrearCategoria.addActionListener(e -> crearCategoria());
        mainPanel.btnSuperCrearProveedor.addActionListener(e -> crearProveedor());
        mainPanel.btnSuperEliminarProveedor.addActionListener(e -> eliminarProveedor());
        mainPanel.btnSuperEliminarCategoria.addActionListener(e -> eliminarCategoria());
        mainPanel.btnSuperEliminarAnaquel.addActionListener(e -> eliminarAnaquel());
        mainPanel.btnSuperVerAnaqueles.addActionListener(e -> verAnaqueles());
        mainPanel.btnSuperVerCategorias.addActionListener(e -> verCategorias());
        mainPanel.btnSuperVerProveedores.addActionListener(e -> verProveedores());

    }



    private void verProveedores() {
        AlmaceneroController controller = new AlmaceneroController();
        String[][] datos = controller.verProveedores();
        String[] columnas = {"ID", "Razón Social"};
        mainPanel.lblResultados.setText("Listado de proveedores");
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }


    private void verCategorias() {
        AlmaceneroController controller = new AlmaceneroController();
        String[][] datos = controller.verCategorias();
        String[] columnas = {"ID", "Nombre"};
        mainPanel.lblResultados.setText("Listado de categorías");
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void verAnaqueles() {
        AlmaceneroController controller = new AlmaceneroController();
        String[][] datos = controller.verAnaqueles();
        String[] columnas = {"ID", "Piso", "Pasaje", "Nivel", "Capacidad"};
        mainPanel.lblResultados.setText("Listado de anaqueles");
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }


    private void eliminarProveedor() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de proveedores, luego ingrese el id del proveedor a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id de proveedor");
            } else {
                AlmaceneroController controller = new AlmaceneroController();
                if (controller.eliminarProveedor(id)) {
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor");
                }
            }
        }
    }

    private void crearProveedor() {
        NuevoProveedorDialog nuevoProveedorDialog = new NuevoProveedorDialog();
        nuevoProveedorDialog.setVisible(true);
    }

    private void crearCategoria() {
        NuevaCategoriaDialog nuevaCategoriaDialog = new NuevaCategoriaDialog();
    }

    private void eliminarCategoria() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de categorías, luego ingrese el id de la categoría a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                AlmaceneroController controller = new AlmaceneroController();
                if (controller.eliminarCategoria(id)) {
                    JOptionPane.showMessageDialog(null, "Categoría eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoría");
                }
            }
        }
    }

    private void crearAnaquel() {
        NuevoAnaquelDialog dialog = new NuevoAnaquelDialog();
        dialog.setVisible(true);
    }

    private void eliminarAnaquel() {
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de anaqueles, luego ingrese el id del anaquel a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                AlmaceneroController controller = new AlmaceneroController();
                if (controller.eliminarAnaquel(id)) {
                    JOptionPane.showMessageDialog(null, "Anaquel eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el anaquel");
                }
            }
        }
    }


    @Override
    public void leerConfiguracion() {
        // Utilizado para extender configuraciones sobre el panel principal
    }


}

class NuevoAnaquelDialog extends JDialog {
    public NuevoAnaquelDialog() {
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
                AlmaceneroController controller = new AlmaceneroController();
                if (controller.crearAnaquel(piso, pasillo, nivel, capacidad)) {
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

class NuevaCategoriaDialog {
    public NuevaCategoriaDialog() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la nueva categoría: ");
        if (nombre != null) {
            if (nombre.length() > 0) {
                AlmaceneroController controller = new AlmaceneroController();
                if (controller.crearCategoria(nombre)) {
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

class NuevoProveedorDialog extends JDialog {
    public NuevoProveedorDialog() {
        super();
        setTitle("Nuevo Proveedor");
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        JTextField txtRazonsocial = new JTextField(36);
        txtRazonsocial.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtRuc = new JTextField(11);
        txtRuc.setBorder(BorderFactory.createTitledBorder("RUC (11 dígitos)"));

        JComboBox<String> cmbPersona = new JComboBox<>();
        cmbPersona.setBorder(BorderFactory.createTitledBorder("Contacto"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbPersona.setModel(model);
        UsuarioBasicoController controller = new UsuarioBasicoController();
        Map<Long, String> listaNombresYApellidos = controller.listarNombresYApellidosYId();
        for (String nombres : listaNombresYApellidos.values()) {
            model.addElement(nombres);
        }
        List<Long> indices = new ArrayList<>(listaNombresYApellidos.keySet());
        JTextField txtDireccion = new JTextField(36);
        txtDireccion.setBorder(BorderFactory.createTitledBorder("Dirección"));
        JTextField txtTelefono = new JTextField(9);
        txtTelefono.setBorder(BorderFactory.createTitledBorder("Teléfono (9 dígitos)"));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.setForeground(Color.BLACK);
        JButton btnCancelar = new JButton("Cancelar");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(txtRazonsocial, constraints);
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
        add(txtDireccion, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(txtTelefono, constraints);
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
            String razonSocial = txtRazonsocial.getText();
            String ruc = txtRuc.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            Long idPersona = indices.get(cmbPersona.getSelectedIndex());
            if (!"".equals(razonSocial) && !"".equals(ruc) && !"".equals(direccion) && !"".equals(telefono)) {
                AlmaceneroController almaceneroController = new AlmaceneroController();
                almaceneroController.crearProveedor(razonSocial, ruc, direccion, telefono, idPersona);
                JOptionPane.showMessageDialog(null, "Proveedor creado correctamente");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}
