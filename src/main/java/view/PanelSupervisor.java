package view;

import dao.impl.AnaquelDaoImpl;
import dao.impl.CategoriaDaoImpl;
import dao.impl.PersonaDaoImpl;
import dao.impl.ProveedorDaoImpl;
import model.Anaquel;
import model.Categoria;
import model.Persona;
import model.Proveedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelSupervisor extends JPanel implements PanelYara {
    PanelUsuario mainPanel;


    public PanelSupervisor(PanelUsuario mainPanel) {
        super();
        this.mainPanel = mainPanel;
    }

    @Override
    public void crearControles() {

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
        List<Proveedor> proveedores;
        ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
        proveedores = proveedorDao.getAll();
        String[] columnas = {"ID", "Nombre"};
        String[][] datos = new String[proveedores.size()][2];
        for (Proveedor proveedor : proveedores) {
            datos[proveedores.indexOf(proveedor)][0] = String.valueOf(proveedor.getId());
            datos[proveedores.indexOf(proveedor)][1] = proveedor.getRazonSocial();
        }
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }


    private void verCategorias() {
        List<Categoria> categorias;
        CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
        categorias = categoriaDao.getAll();
        String[] columnas = {"ID", "Nombre"};
        String[][] datos = new String[categorias.size()][2];
        for (Categoria categoria : categorias) {
            datos[categorias.indexOf(categoria)][0] = String.valueOf(categoria.getId());
            datos[categorias.indexOf(categoria)][1] = categoria.getNombre();
        }
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }

    private void verAnaqueles() {
        List<Anaquel> anaqueles;
        AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
        anaqueles = anaquelDao.getAll();
        String[] columnas = {"ID", "Piso", "Pasaje", "Nivel", "Capacidad"};
        String[][] datos = new String[anaqueles.size()][5];
        for (Anaquel anaquel : anaqueles) {
            datos[anaqueles.indexOf(anaquel)][0] = String.valueOf(anaquel.getId());
            datos[anaqueles.indexOf(anaquel)][1] = String.valueOf(anaquel.getPiso());
            datos[anaqueles.indexOf(anaquel)][2] = String.valueOf(anaquel.getPasillo());
            datos[anaqueles.indexOf(anaquel)][3] = String.valueOf(anaquel.getNivel());
            datos[anaqueles.indexOf(anaquel)][4] = String.valueOf(anaquel.getCapacidad());

        }
        mainPanel.txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }


    private void eliminarProveedor() {
        ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de proveedores, luego ingrese el id del proveedor a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                try {
                    long idLong = Long.parseLong(id);
                    Proveedor proveedor = proveedorDao.get(idLong).orElse(null);
                    if (proveedor != null) {
                        proveedorDao.delete(proveedor);
                        JOptionPane.showMessageDialog(null, "Proveedor eliminado");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el proveedor");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un id valido");
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
        CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de categorías, luego ingrese el id de la categoría a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                try {
                    long idLong = Long.parseLong(id);
                    Categoria categoria = categoriaDao.get(idLong).orElse(null);
                    if (categoria != null) {
                        categoriaDao.delete(categoria);
                        JOptionPane.showMessageDialog(null, "Categoría eliminada");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la categoría");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un id valido");
                }
            }
        }
    }

    private void crearAnaquel() {
        NuevoAnaquelDialog dialog = new NuevoAnaquelDialog();
        dialog.setVisible(true);
    }

    private void eliminarAnaquel() {
        AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
        String id = JOptionPane.showInputDialog(null, "Busque en el listado de anaqueles, luego ingrese el id del anaquel a eliminar");
        if (id != null) {
            if ("".equals(id)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un id");
            } else {
                try {
                    long idLong = Long.parseLong(id);
                    Anaquel anaquel = anaquelDao.get(idLong).orElse(null);
                    if (anaquel != null) {
                        anaquelDao.delete(anaquel);
                        JOptionPane.showMessageDialog(null, "Anaquel eliminado");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el anaquel");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un id valido");
                }
            }
        }
    }


    @Override
    public void leerConfiguracion() {

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
            Anaquel anaquel = new Anaquel();
            if (!Objects.equals(txtPiso.getText(), "") && !Objects.equals(txtPasillo.getText(), "") && !Objects.equals(txtNivel.getText(), "") && !Objects.equals(txtCapacidad.getText(), "")) {
                anaquel.setPiso(Integer.parseInt(txtPiso.getText()));
                anaquel.setPasillo(Integer.parseInt(txtPasillo.getText()));
                anaquel.setNivel(Integer.parseInt(txtNivel.getText()));
                anaquel.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                AnaquelDaoImpl anaquelDao = new AnaquelDaoImpl();
                anaquelDao.save(anaquel);
                JOptionPane.showMessageDialog(this, "Anaquel creado correctamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
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
                CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
                Categoria categoria = new Categoria();
                categoria.setNombre(nombre);
                categoriaDao.save(categoria);
                JOptionPane.showMessageDialog(null, "Categoría creada correctamente");
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
        PersonaDaoImpl personaDao = new PersonaDaoImpl();
        List<Persona> personas = personaDao.getAll();
        List<Long> indices = new ArrayList<>();
        for (Persona persona : personas) {
            model.addElement(persona.getNombre() + " " + persona.getApellido());
            indices.add(persona.getId());
        }
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
            Proveedor proveedor = new Proveedor();
            if (!Objects.equals(txtRazonsocial.getText(), "") && !Objects.equals(txtRuc.getText(), "") && !Objects.equals(txtDireccion.getText(), "") && !Objects.equals(txtTelefono.getText(), "")) {
                proveedor.setRazonSocial(txtRazonsocial.getText());
                proveedor.setPersona(personaDao.get(indices.get(cmbPersona.getSelectedIndex())).get());
                proveedor.setRuc(txtRuc.getText());
                proveedor.setDireccion(txtDireccion.getText());
                proveedor.setTelefono(txtTelefono.getText());
                ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
                proveedorDao.save(proveedor);
                JOptionPane.showMessageDialog(this, "Proveedor creado correctamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}
