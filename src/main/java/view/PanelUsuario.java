package view;

import controller.UsuarioBasicoController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PanelUsuario extends JPanel implements PanelYara {
    private final JTextField txtProductoNombre = new JTextField();
    private final JTextField txtProductoEstado = new JTextField();
    private final JTextField txtProductoCategoria = new JTextField();
    private final JTextField txtProductoProveedor = new JTextField();
    final JTable txtResultados = new JTable();
    final JLabel lblResultados = new JLabel();
    private final JButton btnProductoBuscar = new JButton("Buscar");
    private final JButton btnProductoStock = new JButton("Stock de producto");
    private final JButton btnCategoriaStock = new JButton("Stock de categoría");
    private final JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
    private final JButton btnRegistrarSalida = new JButton("Registrar Salida");
    private final JButton btnRevisarRegistros = new JButton("Revisar Registros");
    private final JButton btnAgregarProducto = new JButton("Agregar Producto");
    private final JButton btnEliminarProducto = new JButton("Eliminar Producto");
    final JButton btnVerificarProductosMalEstado = new JButton("Ver productos por vencer");
    final JButton btnLogin = new JButton("Login");

    final JLabel labelUsuarioNombre = new JLabel("Nombre: (inicie sesión)");
    final JLabel labelUsuarioRol = new JLabel("Rol: (inicie sesión)");
    final JLabel labelUsuarioFecha = new JLabel("Login: --/--/--");

    //----Controles de panel supervisor---------------
    final JButton btnSuperCrearAnaquel = new JButton("Crear Anaquel");
    final JButton btnSuperCrearProveedor = new JButton("Crear Proveedor");
    final JButton btnSuperCrearCategoria = new JButton("Crear Categoría");
    final JButton btnSuperEliminarAnaquel = new JButton("Eliminar Anaquel");
    final JButton btnSuperEliminarProveedor = new JButton("Eliminar Proveedor");
    final JButton btnSuperEliminarCategoria = new JButton("Eliminar Categoría");
    final JButton btnSuperVerAnaqueles = new JButton("Ver Anaqueles");
    final JButton btnSuperVerProveedores = new JButton("Ver Proveedores");
    final JButton btnSuperVerCategorias = new JButton("Ver Categorías");
    //----Controles de panel admin---------------
    final JButton btnAdminCrearUsuario = new JButton("Crear Contacto/Empleado");
    final JButton btnAdminEliminarUsuario = new JButton("Eliminar Usuario");
    final JButton btnAdminVerCredenciales = new JButton("Listar Credenciales");
    final JButton btnAdminCrearSucursal = new JButton("Crear Sucursal");
    final JButton btnAdminEliminarSucursal = new JButton("Eliminar Sucursal");
    final JButton btnAdminCrearCredenciales = new JButton("Crear Credenciales");
    final JButton btnAdminEliminarCredenciales = new JButton("Eliminar Credenciales");
    final JButton btnAdminVerUsuarios = new JButton("Ver Usuarios");
    final JButton btnAdminVerEmpleados = new JButton("Ver Empleados");
    final JButton btnAdminVerSucursales = new JButton("Ver Sucursales");

    final JFrame frame;
    final JTabbedPane tabs = new JTabbedPane();
    final GridBagConstraints mainConstraints = new GridBagConstraints();

    final PanelSupervisor supervisor = new PanelSupervisor(this);
    final PanelAdmin admin = new PanelAdmin(this);

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
        lblResultados.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarResultados.add(lblResultados);
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
        panelSupervisor.add(btnSuperEliminarAnaquel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCrearCategoria, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperEliminarCategoria, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCrearProveedor, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperEliminarProveedor, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperVerAnaqueles, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperVerCategorias, constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperVerProveedores, constraints);
        tabs.addTab("Supervisión", panelSupervisor);
        supervisor.crearEventos();
    }

    private void crearControlesAdmin() {
        JPanel panelAdmin = new JPanel();
        panelAdmin.setBorder(BorderFactory.createTitledBorder("Opciones De Administrador"));
        panelAdmin.setLayout(new GridBagLayout());
        btnAdminEliminarSucursal.setBackground(Color.RED);
        btnAdminEliminarSucursal.setForeground(Color.WHITE);
        btnAdminEliminarUsuario.setBackground(Color.RED);
        btnAdminEliminarUsuario.setForeground(Color.WHITE);
        btnAdminEliminarCredenciales.setBackground(Color.RED);
        btnAdminEliminarCredenciales.setForeground(Color.WHITE);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminVerUsuarios, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCrearUsuario, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminEliminarUsuario, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminVerSucursales, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCrearSucursal, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminEliminarSucursal, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminVerCredenciales, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCrearCredenciales, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminEliminarCredenciales, constraints);
        tabs.addTab("Administración", panelAdmin);
        this.frame.pack();
        admin.crearEventos();
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
        UsuarioBasicoController controller = new UsuarioBasicoController();
        String[][] datos = controller.listarProductosMalEstado();
        String[] columnas = {"Producto", "Estado"};
        lblResultados.setText("Lista de productos en mal estado");
        txtResultados.setModel(new DefaultTableModel(datos, columnas));
    }


    private void buscarStockDeCategoriaPorNombre() {
        String nombre = txtProductoCategoria.getText();
        if (!nombre.isEmpty()) {
            String[] columnas = {"Nombre", "Stock"};
            UsuarioBasicoController controller = new UsuarioBasicoController();
            lblResultados.setText("Stock de la categoría " + nombre);
            txtResultados.setModel(new DefaultTableModel(controller.buscarStockDeCategoriaPorNombre(nombre), columnas));
        }
    }

    private void buscarStockDeProductoPorNombre() {
        String nombre = txtProductoNombre.getText();
        if (!nombre.isEmpty()) {
            UsuarioBasicoController controller = new UsuarioBasicoController();
            String[][] datos = controller.buscarStockDeProductoPorNombre(nombre);
            String[] columnas = {"Producto", "Proveedor", "Stock"};
            lblResultados.setText("Stock de producto " + nombre);
            txtResultados.setModel(new DefaultTableModel(datos, columnas));
        }
    }

    private void buscarProductoPorNombre() {
        String nombre = txtProductoNombre.getText();
        String categoria = txtProductoCategoria.getText();
        String estado = txtProductoEstado.getText();
        String proveedor = txtProductoProveedor.getText();
        String[] columnas = {"ID", "Nombre", "Categoría", "Proveedor", "Anaquel", "Estado"};
        UsuarioBasicoController controller = new UsuarioBasicoController();
        String[][] datos = controller.buscarProductos(nombre, categoria, estado, proveedor);
        lblResultados.setText("Resultados de la búsqueda de productos");
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
                    borrarTabs(1);
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
                    borrarTabs(2);
                }
                labelUsuarioFecha.setText("Login: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            } else {
                borrarTabs(1);
                lockUI(true);
            }
        } else {
            borrarTabs(0);
            btnLogin.setText("Login");
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setBackground(Color.RED);
            lockUI(true);
        }
    }

    private void borrarTabs(int tipoUsuario) {
        //tipoUsuario: 0 = sin sesión, 1 = usuario, 2 = supervisor, 3 = administrador
        int tabsNumber = tabs.getTabCount();
        if (tabsNumber > tipoUsuario + 2) {
            for (int i = tipoUsuario; i < tabsNumber; i++) {
                if (tabsNumber != 2) {
                    tabs.remove(tabsNumber - 1);
                }
            }
        }
    }

    @Override
    public void leerConfiguracion() {
        //Para extender la configuración de la aplicación en un futuro
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
                UsuarioBasicoController controller = new UsuarioBasicoController();
                controller.crearProducto(this, txtNombre.getText(), txtCategoria.getText(), txtProveedor.getText());
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
                UsuarioBasicoController controller = new UsuarioBasicoController();
                controller.eliminarProducto(this, txtId.getText());
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }


}
