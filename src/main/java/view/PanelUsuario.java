package view;


import dao.impl.CategoriaDaoImpl;
import dao.impl.ProductoDaoImpl;
import model.Categoria;
import model.Producto;

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
    JPanel supervisorAdminPanel = new JPanel();
    GridBagConstraints mainConstraints = new GridBagConstraints();

    public PanelUsuario(JFrame frame) {
        super();
        leerConfiguracion();
        crearControles();
        crearEventos();
        crearLayout();
        inicializar();
        lockUI(true);
        this.frame = frame;
    }

    @Override
    public void crearControles() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Buscar
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
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
        panelBuscar.add(panelBuscarResultados);
        // Fin panel Buscar
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(15);
        gridLayout.setHgap(20);
        supervisorAdminPanel.setLayout(gridLayout);
        JPanel panelOperaciones = new JPanel();
        panelOperaciones.setLayout(gridLayout);
        panelOperaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        panelOperaciones.add(panelDatosUsuario);
        panelOperaciones.add(panelEntradaSalida);
        panelOperaciones.add(panelAgregarEliminarProducto);
        panelOperaciones.add(panelOtrasOpciones);

        mainConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 3;
        this.add(panelBuscar, mainConstraints);

        mainConstraints.gridx = 3;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 2;
        this.add(panelOperaciones, mainConstraints);

        mainConstraints.gridx = 5;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 2;
        this.add(supervisorAdminPanel);
    }

    private void crearControlesSupervisor() {
        JPanel panelSupervisor = new JPanel();
        supervisorAdminPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelSupervisor.setBorder(BorderFactory.createTitledBorder("Opciones De Supervisor"));
        panelSupervisor.setLayout(new GridLayout(0, 2));
        panelSupervisor.add(btnSuperCrearAnaquel);
        panelSupervisor.add(btnSuperCrearProveedor);
        panelSupervisor.add(btnSuperCrearCategoria);
        panelSupervisor.add(btnSuperEliminarAnaquel);
        panelSupervisor.add(btnSuperEliminarProveedor);
        panelSupervisor.add(btnSuperEliminarCategoria);
        supervisorAdminPanel.add(panelSupervisor);
        this.frame.pack();
    }

    private void crearControlesAdmin() {
        JPanel panelAdmin = new JPanel();
        supervisorAdminPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelAdmin.setBorder(BorderFactory.createTitledBorder("Opciones De Administrador"));
        panelAdmin.setLayout(new GridLayout(0, 2));
        panelAdmin.add(btnAdminCrearUsuario);
        panelAdmin.add(btnAdminCrearSucursal);
        panelAdmin.add(btnAdminEliminarUsuario);
        panelAdmin.add(btnAdminResetCredenciales);
        panelAdmin.add(btnAdminEliminarSucursal);
        supervisorAdminPanel.add(panelAdmin);
        this.frame.pack();
    }

    @Override
    public void crearEventos() {

        btnLogin.addActionListener(e -> activateUserUI());
        btnProductoBuscar.addActionListener(e -> buscarProductoPorNombre());
        btnCategoriaStock.addActionListener(e -> buscarStockDeCategoriaPorNombre());
        btnProductoStock.addActionListener(e -> buscarStockDeProductoPorNombre());
        btnVerificarProductosMalEstado.addActionListener(e -> listarProductosMalEstado());

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
        String[] columnas = {"Nombre", "Categoría", "Proveedor", "Anaquel", "Estado"};
        String[][] datos = new String[resultadosDeBusqueda.size()][5];
        for (String[] producto : resultadosDeBusqueda) {
            datos[resultadosDeBusqueda.indexOf(producto)][0] = producto[0];
            if (producto[1] != null) {
                datos[resultadosDeBusqueda.indexOf(producto)][1] = producto[1];
            } else {
                datos[resultadosDeBusqueda.indexOf(producto)][1] = "N/A";
            }
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
                }
                if (ventanaLogin.adminCorrecto()) {
                    labelUsuarioRol.setText("Rol: ADMINISTRADOR");
                    crearControlesSupervisor();
                    crearControlesAdmin();
                }
                if (ventanaLogin.supervisorCorrecto()) {
                    labelUsuarioRol.setText("Rol: SUPERVISOR");
                    crearControlesSupervisor();
                }
                labelUsuarioFecha.setText("Login: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            } else {
                lockUI(true);
            }
        } else {
            btnLogin.setText("Login");
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setBackground(Color.RED);
            lockUI(true);
        }
    }

    @Override
    public void crearLayout() {

    }

    @Override
    public void inicializar() {

    }

    @Override
    public void leerConfiguracion() {

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
