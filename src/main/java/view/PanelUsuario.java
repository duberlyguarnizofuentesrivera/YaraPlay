package view;


import dao.impl.CategoriaDaoImpl;
import dao.impl.ProductoDaoImpl;
import dao.impl.ProveedorDaoImpl;
import model.Categoria;
import model.Producto;
import model.Proveedor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PanelUsuario extends JPanel implements PanelYara {
    private final JTextField txtProductoNombre = new JTextField();
    private final JTextField txtProductoEstado = new JTextField();
    private final JTextField txtProductoCategoria = new JTextField();
    private final JTextField txtProductoProveedor = new JTextField();
    JTable txtResultados = new JTable();
    private final JButton btnProductoBuscar = new JButton("Buscar");
    private final JButton btnProductoStock = new JButton("Stock de producto");
    private final JButton btnCategoriaStock = new JButton("Stock de categoría");
    private final JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
    private final JButton btnRegistrarSalida = new JButton("Registrar Salida");
    private final JButton btnRevisarRegistros = new JButton("Revisar Registros");
    private final JButton btnAgregarProducto = new JButton("Agregar Producto");
    private final JButton btnEliminarProducto = new JButton("Eliminar Producto");
    JButton btnVerificarProductosMalEstado = new JButton("Ver productos por vencer");
    final JButton btnLogin = new JButton("Login");

    JLabel labelUsuarioNombre = new JLabel("Nombre: (inicie sesión)");
    JLabel labelUsuarioRol = new JLabel("Rol: (inicie sesión)");
    JLabel labelUsuarioFecha = new JLabel("Login: --/--/--");

    //----Controles de panel supervisor---------------
    JButton btnSuperCrearAnaquel = new JButton("Crear Anaquel");
    JButton btnSuperCrearProveedor = new JButton("Crear Proveedor");
    JButton btnSuperCrearCategoria = new JButton("Crear Categoría");
    JButton btnSuperEliminarAnaquel = new JButton("Eliminar Anaquel");
    JButton btnSuperEliminarProveedor = new JButton("Eliminar Proveedor");
    JButton btnSuperEliminarCategoria = new JButton("Eliminar Categoría");
    //----Controles de panel admin---------------
    JButton btnAdminCrearUsuario = new JButton("Crear Usuario");
    JButton btnAdminEliminarUsuario = new JButton("Eliminar Usuario");
    JButton btnAdminResetCredenciales = new JButton("Resetear Credenciales");
    JButton btnAdminCrearSucursal = new JButton("Crear Sucursal");
    JButton btnAdminEliminarSucursal = new JButton("Eliminar Sucursal");
    JFrame frame;
    JTabbedPane tabs = new JTabbedPane();
    GridBagConstraints mainConstraints = new GridBagConstraints();

    PanelSupervisor Supervisor = new PanelSupervisor(this);

    public PanelUsuario(JFrame frame) {
        super();
        leerConfiguracion();
        crearControles();
        crearEventos();
        lockUI(true);
        this.frame = frame;
    }

    @Override
    public void crearControles() {

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Buscar
        JPanel userContainerPanel = new JPanel();
        userContainerPanel.setLayout(new GridBagLayout());
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
        labelBienvenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtProductoNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtProductoCategoria.setBorder(BorderFactory.createTitledBorder("Categoría"));
        txtProductoEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        txtProductoProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));

        JPanel panelBuscarControles = new JPanel();
        panelBuscarControles.setLayout(new BoxLayout(panelBuscarControles, BoxLayout.Y_AXIS));
        panelBuscarControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarControles.add(labelBienvenido);
        panelBuscarControles.add(txtProductoNombre);
        panelBuscarControles.add(txtProductoCategoria);
        panelBuscarControles.add(txtProductoEstado);
        panelBuscarControles.add(txtProductoProveedor);

        JPanel panelBuscarBotones = new JPanel();
        panelBuscarBotones.setLayout(new BoxLayout(panelBuscarBotones, BoxLayout.X_AXIS));
        panelBuscarBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarBotones.add(btnProductoBuscar);
        panelBuscarBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        panelBuscarBotones.add(btnProductoStock);
        panelBuscarBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        panelBuscarBotones.add(btnCategoriaStock);
        JPanel panelBuscarResultados = new JPanel();
        panelBuscarResultados.setLayout(new BoxLayout(panelBuscarResultados, BoxLayout.Y_AXIS));
        panelBuscarResultados.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtResultados.setBounds(30, 40, 150, 75);
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        panelBuscarResultados.add(scrollResultados);

        panelBuscar.add(panelBuscarControles);
        panelBuscar.add(panelBuscarBotones);
        // Fin panel Buscar

        //Inicio de datos de usuario
        JPanel panelDatosUsuario = new JPanel();
        panelDatosUsuario.setLayout(new GridBagLayout());
        GridBagConstraints currentUserConstraints = new GridBagConstraints();
        panelDatosUsuario.setBorder(BorderFactory.createTitledBorder("Usuario Actual"));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.WHITE);
        currentUserConstraints.fill = GridBagConstraints.VERTICAL;
        currentUserConstraints.gridy = 0;
        panelDatosUsuario.add(labelUsuarioNombre, currentUserConstraints);
        currentUserConstraints.gridy = 1;
        panelDatosUsuario.add(labelUsuarioRol, currentUserConstraints);
        currentUserConstraints.gridy = 2;
        panelDatosUsuario.add(labelUsuarioFecha, currentUserConstraints);
        currentUserConstraints.gridy = 3;
        currentUserConstraints.insets = new Insets(10, 0, 10, 0);
        panelDatosUsuario.add(btnLogin, currentUserConstraints);
        //Fin de datos de usuario


        mainConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 3;
        userContainerPanel.add(panelBuscar, mainConstraints);

        mainConstraints.gridx = 3;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 2;

        tabs.addTab("Login", panelDatosUsuario);
        tabs.addTab("Buscar", userContainerPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(tabs);
        this.add(panelBuscarResultados);
    }

    private void crearControlesUsuario() {
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(15);
        gridLayout.setHgap(20);
        JPanel panelOperaciones = new JPanel();
        panelOperaciones.setLayout(gridLayout);
        panelOperaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Registrar Entradas/Salidas
        JPanel panelEntradaSalida = new JPanel();
        panelEntradaSalida.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panelEntradaSalida.setBorder(BorderFactory.createTitledBorder("Registrar Entrada/Salida De Productos"));
        btnRegistrarEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarEntrada.setMaximumSize(new Dimension(150, 30));
        btnRegistrarSalida.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelEntradaSalida.add(btnRegistrarEntrada, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelEntradaSalida.add(btnRegistrarSalida, constraints);
        panelEntradaSalida.add(btnRevisarRegistros);
        // Fin panel Registrar Entradas/Salidas

        // Inicio panel Agregar/Eliminar Productos
        JPanel panelAgregarEliminarProducto = new JPanel();
        panelAgregarEliminarProducto.setLayout(new GridBagLayout());
        panelAgregarEliminarProducto.setBorder(BorderFactory.createTitledBorder("Agregar/Eliminar Productos"));

        btnAgregarProducto.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnEliminarProducto.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelAgregarEliminarProducto.add(btnAgregarProducto, constraints);
        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelAgregarEliminarProducto.add(btnEliminarProducto, constraints);
        // Fin panel Agregar/Eliminar Productos

        //Inicio panel Otras opciones
        JPanel panelOtrasOpciones = new JPanel();
        panelOtrasOpciones.setLayout(new GridBagLayout());
        GridBagConstraints constraintsOtrasOpciones = new GridBagConstraints();
        panelOtrasOpciones.setBorder(BorderFactory.createTitledBorder("Otras Opciones"));
        btnVerificarProductosMalEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraintsOtrasOpciones.insets = new Insets(10, 10, 5, 10);
        constraintsOtrasOpciones.gridx = 0;
        constraintsOtrasOpciones.gridy = 0;
        constraintsOtrasOpciones.gridwidth = 1;
        constraintsOtrasOpciones.gridheight = 1;
        constraintsOtrasOpciones.fill = GridBagConstraints.HORIZONTAL;
        panelOtrasOpciones.add(btnVerificarProductosMalEstado, constraintsOtrasOpciones);

        //Fin panel Otras opciones

        panelOperaciones.add(panelEntradaSalida);
        panelOperaciones.add(panelAgregarEliminarProducto);
        panelOperaciones.add(panelOtrasOpciones);
        tabs.addTab("Operaciones", panelOperaciones);
    }

    private void crearControlesSupervisor() {

        JPanel panelSupervisor = new JPanel();
        panelSupervisor.setBorder(BorderFactory.createTitledBorder("Opciones De Supervisor"));
        panelSupervisor.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCrearAnaquel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCrearProveedor, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCrearCategoria, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperEliminarAnaquel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperEliminarProveedor, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperEliminarCategoria, constraints);
        tabs.addTab("Supervisión", panelSupervisor);
        Supervisor.crearEventos();
    }

    private void crearControlesAdmin() {
        JPanel panelAdmin = new JPanel();
        panelAdmin.setBorder(BorderFactory.createTitledBorder("Opciones De Administrador"));
        panelAdmin.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCrearUsuario, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCrearSucursal, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminEliminarUsuario, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminResetCredenciales, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminEliminarSucursal, constraints);
        tabs.addTab("Administración", panelAdmin);
        this.frame.pack();
    }

    @Override
    public void crearEventos() {

        btnLogin.addActionListener(e -> activateUserUI());
        btnProductoBuscar.addActionListener(e -> buscarProductoPorNombre());
        btnCategoriaStock.addActionListener(e -> buscarStockDeCategoriaPorNombre());
        btnProductoStock.addActionListener(e -> buscarStockDeProductoPorNombre());
        btnVerificarProductosMalEstado.addActionListener(e -> listarProductosMalEstado());
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        btnEliminarProducto.addActionListener(e -> eliminarProducto());
    }

    private void eliminarProducto() {
        EliminarProductoDialog eliminarProductoDialog = new EliminarProductoDialog(this.frame, true);
        eliminarProductoDialog.setVisible(true);
    }

    private void agregarProducto() {
        NuevoProductoDialog nuevoProductoDialog = new NuevoProductoDialog(this.frame, true);
        nuevoProductoDialog.setVisible(true);
    }

    private void listarProductosMalEstado() {
        ProductoDaoImpl productoDao = new ProductoDaoImpl();

        List<Producto> productosMalEstado = productoDao.listarProductosMalEstado();
        if (productosMalEstado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos mal estado");
        } else {
            String[] columnas = {"Producto", "Estado"};
            String[][] datos = new String[productosMalEstado.size()][2];
            for (Producto producto : productosMalEstado) {
                datos[productosMalEstado.indexOf(producto)][0] = producto.getNombre();
                String estado = producto.getEstado();
                if (estado == null) {
                    estado = "N/A";
                }
                datos[productosMalEstado.indexOf(producto)][1] = estado;
            }
            txtResultados.setModel(new DefaultTableModel(datos, columnas));
        }
    }

    private void buscarStockDeCategoriaPorNombre() {
        String nombre = txtProductoCategoria.getText();
        if (!nombre.isEmpty()) {
            CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
            List<Categoria> resultadosDeBusqueda = categoriaDao.findByName(txtProductoCategoria.getText());
            System.out.println("buscando categorías con nombre '" + nombre + "' en la base de datos");
            String[] columnas = {"Nombre", "Stock"};
            String[][] datos = new String[resultadosDeBusqueda.size()][2];
            for (Categoria categoria : resultadosDeBusqueda) {
                datos[resultadosDeBusqueda.indexOf(categoria)][0] = categoria.getNombre();
                String stock = categoriaDao.getStock(categoria.getId());
                if (stock == null) {
                    stock = "N/A";
                }
                datos[resultadosDeBusqueda.indexOf(categoria)][1] = stock;
            }
            txtResultados.setModel(new DefaultTableModel(datos, columnas));
        }
    }

    private void buscarStockDeProductoPorNombre() {
        String nombre = txtProductoNombre.getText();
        if (!nombre.isEmpty()) {
            ProductoDaoImpl productoDao = new ProductoDaoImpl();
            List<Producto> resultadosDeBusqueda = productoDao.findByName(txtProductoNombre.getText());
            System.out.println("buscando productos con nombre '" + nombre + "' en la base de datos");
            String[] columnas = {"Producto", "Proveedor", "Stock"};
            String[][] datos = new String[resultadosDeBusqueda.size()][3];
            for (Producto producto : resultadosDeBusqueda) {
                datos[resultadosDeBusqueda.indexOf(producto)][0] = producto.getNombre();
                if (producto.getProveedor() != null) {
                    datos[resultadosDeBusqueda.indexOf(producto)][1] = producto.getProveedor().getRazonSocial();
                } else {
                    datos[resultadosDeBusqueda.indexOf(producto)][1] = "N/A";
                }
                String stock = productoDao.getStock(producto.getId());
                if (stock == null) {
                    stock = "N/A";
                }
                datos[resultadosDeBusqueda.indexOf(producto)][2] = stock;
            }
            txtResultados.setModel(new DefaultTableModel(datos, columnas));
        }
    }

    private void buscarProductoPorNombre() {
        String nombre = txtProductoNombre.getText();
        ProductoDaoImpl productoDao = new ProductoDaoImpl();
        List<String[]> resultadosDeBusqueda = productoDao.findByNameCategoryStateSupplier(
                txtProductoNombre.getText(),
                txtProductoCategoria.getText(),
                txtProductoEstado.getText(),
                txtProductoProveedor.getText());
        System.out.println("buscando productos con nombre '" + nombre + "' en la base de datos");
        String[] columnas = {"ID", "Nombre", "Categoría", "Proveedor", "Anaquel", "Estado"};
        String[][] datos = new String[resultadosDeBusqueda.size()][6];
        for (String[] producto : resultadosDeBusqueda) {
            datos[resultadosDeBusqueda.indexOf(producto)][0] = producto[0];
            datos[resultadosDeBusqueda.indexOf(producto)][1] = producto[1];
            if (producto[2] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][2] = producto[2];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][2] = "N/A";
            }
            if (producto[3] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][3] = producto[3];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][3] = "N/A";
            }
            if (producto[4] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][4] = producto[4];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][4] = "N/A";
            }
            if (producto[5] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][5] = producto[5];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][5] = "N/A";
            }

        }
        txtResultados.setModel(new DefaultTableModel(datos, columnas));

    }

    protected void activateUserUI() {
        if (Objects.equals(btnLogin.getText(), "Login")) {
            PanelLogin ventanaLogin = new PanelLogin(this.frame);
            ventanaLogin.setVisible(true);
            if (ventanaLogin.loginCorrecto()) {
                btnLogin.setText("Hola " + ventanaLogin.getUsername() + "!");
                lockUI(false);
                btnLogin.setForeground(Color.BLACK);
                btnLogin.setBackground(Color.GREEN);
                btnLogin.setText("Cerrar Sesión");
                labelUsuarioNombre.setText("Nombre: Juan Luis Guerra");
                if (ventanaLogin.userCorrecto()) {
                    labelUsuarioRol.setText("Rol: USUARIO");
                    crearControlesUsuario();
                    if (tabs.getTabCount() == 5) {
                        tabs.remove(4);
                        tabs.remove(3);
                    }
                    if (tabs.getTabCount() == 4) {
                        tabs.remove(3);
                    }
                }
                if (ventanaLogin.adminCorrecto()) {
                    labelUsuarioRol.setText("Rol: ADMINISTRADOR");
                    crearControlesUsuario();
                    crearControlesSupervisor();
                    crearControlesAdmin();
                }
                if (ventanaLogin.supervisorCorrecto()) {
                    labelUsuarioRol.setText("Rol: SUPERVISOR");
                    crearControlesUsuario();
                    crearControlesSupervisor();
                    if (tabs.getTabCount() == 5) {
                        tabs.remove(4);
                    }
                }
                labelUsuarioFecha.setText("Login: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            } else {
                if (tabs.getTabCount() == 5) {
                    tabs.remove(4);
                    tabs.remove(3);
                    tabs.remove(2);
                }
                if (tabs.getTabCount() == 4) {
                    tabs.remove(3);
                    tabs.remove(2);
                }

                lockUI(true);
            }
        } else {
            if (tabs.getTabCount() == 5) {
                tabs.remove(4);
                tabs.remove(3);
                tabs.remove(2);
            }
            if (tabs.getTabCount() == 4) {
                tabs.remove(3);
                tabs.remove(2);
            }
            if (tabs.getTabCount() == 3) {
                tabs.remove(2);
            }
            btnLogin.setText("Login");
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setBackground(Color.RED);
            lockUI(true);
        }
    }


    @Override
    public void leerConfiguracion() {
        // TODO document why this method is empty
    }

    protected void lockUI(boolean lock) {
        btnProductoBuscar.setEnabled(!lock);
        btnRegistrarEntrada.setEnabled(!lock);
        btnRegistrarSalida.setEnabled(!lock);
        btnAgregarProducto.setEnabled(!lock);
        btnEliminarProducto.setEnabled(!lock);
        btnProductoStock.setEnabled(!lock);
        btnCategoriaStock.setEnabled(!lock);
        btnRevisarRegistros.setEnabled(!lock);
        btnVerificarProductosMalEstado.setEnabled(!lock);
        txtProductoCategoria.setEnabled(!lock);
        txtProductoNombre.setEnabled(!lock);
        txtProductoEstado.setEnabled(!lock);
        txtProductoProveedor.setEnabled(!lock);
    }
}

class NuevoProductoDialog extends JDialog {
    private final JTextField txtNombre = new JTextField(24);
    private final JTextField txtCategoria = new JTextField(24);
    private final JTextField txtProveedor = new JTextField(36);

    public NuevoProductoDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Nuevo Producto");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtCategoria.setBorder(BorderFactory.createTitledBorder("Categoría"));
        txtProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(15);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(txtNombre);
        panel.add(txtCategoria);
        panel.add(txtProveedor);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(Color.GREEN);
        btnAgregar.setForeground(Color.BLACK);
        panel.add(btnAgregar);
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);
        this.add(panel);
        this.pack();

        btnAgregar.addActionListener(e -> {
            if (txtNombre.getText().isEmpty() || txtCategoria.getText().isEmpty() || txtProveedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            } else {
                ProductoDaoImpl productoDao = new ProductoDaoImpl();
                CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
                ProveedorDaoImpl proveedorDao = new ProveedorDaoImpl();
                Producto producto = new Producto();
                producto.setNombre(txtNombre.getText());
                //debería devolver solo uno, en todo caso se usa el primero devuelto
                List<Categoria> categoriaList = categoriaDao.findByName(txtCategoria.getText());
                List<Proveedor> proveedorList = proveedorDao.findByName(txtProveedor.getText());
                Categoria categoria = new Categoria();
                Proveedor proveedor = new Proveedor();
                if (categoriaList.isEmpty()) {
                    categoria.setNombre(txtCategoria.getText());
                    categoriaDao.save(categoria);
                } else {
                    categoria = categoriaList.get(0);
                }
                producto.setCategoria(categoria);
                if (proveedorList.isEmpty()) {
                    String ruc = JOptionPane.showInputDialog("Ingrese el RUC del nuevo proveedor (11 dígitos):");
                    if (ruc.length() != 11) {
                        JOptionPane.showMessageDialog(this, "El RUC debe tener 11 dígitos");
                        return;
                    }
                    proveedor.setRazonSocial(txtProveedor.getText());
                    proveedor.setRuc(ruc);
                    proveedorDao.saveBasic(proveedor);
                } else {
                    proveedor = proveedorList.get(0);
                }
                producto.setProveedor(proveedor);
                productoDao.saveBasic(producto);
                dispose();
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}

class EliminarProductoDialog extends JDialog {
    public EliminarProductoDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Eliminar Producto");
        JLabel label = new JLabel("<html>Primero busque el ID del producto en la sección  'buscar',<br> luego ingrese el id del producto a eliminar en el cuadro de abajo</html>");
        JTextField txtId = new JTextField(16);
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(Color.RED);
        btnEliminar.setForeground(Color.WHITE);
        JButton btnCancelar = new JButton("Cancelar");
        txtId.setBorder(BorderFactory.createTitledBorder("ID del producto"));
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(15);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(label);
        panel.add(txtId);
        panel.add(btnEliminar);
        panel.add(btnCancelar);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo ID es obligatorio");
            } else {
                ProductoDaoImpl productoDao = new ProductoDaoImpl();
                Producto producto = productoDao.get(Integer.parseInt(txtId.getText())).orElse(null);
                if (producto == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró el producto con el ID ingresado");
                } else {
                    productoDao.delete(producto);
                    JOptionPane.showMessageDialog(this, "Producto con ID " + txtId.getText() + " eliminado!");
                    dispose();
                }
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }
}
