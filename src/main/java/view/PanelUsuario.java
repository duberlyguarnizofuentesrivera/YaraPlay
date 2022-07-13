package view;


import dao.impl.ProductoDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PanelUsuario extends JPanel implements PanelYara {
    private final JTextField txtProductoNombre = new JTextField();
    private final JTextField txtProductoAnaquel = new JTextField();
    private final JTextField txtProductoEstado = new JTextField();
    private final JTextField txtProductoCategoria = new JTextField();
    private final JTextField txtProductoProveedor = new JTextField();
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

        supervisorAdminPanel.setLayout(new BoxLayout(supervisorAdminPanel, BoxLayout.Y_AXIS));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
        txtProductoNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtProductoAnaquel.setBorder(BorderFactory.createTitledBorder("Anaquel"));
        txtProductoCategoria.setBorder(BorderFactory.createTitledBorder("Categoria"));
        txtProductoEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        txtProductoProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));

        JTextArea txtResultados = new JTextArea(10, 25);
        //txtResultados.setEditable(false);
        txtResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JPanel panelBuscarControles = new JPanel();
        panelBuscarControles.setLayout(new BoxLayout(panelBuscarControles, BoxLayout.Y_AXIS));
        panelBuscarControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarControles.add(labelBienvenido);
        panelBuscarControles.add(txtProductoNombre);
        panelBuscarControles.add(txtProductoCategoria);
        panelBuscarControles.add(txtProductoAnaquel);
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
        panelBuscarResultados.add(txtResultados);

        panelBuscar.add(panelBuscarControles);
        panelBuscar.add(panelBuscarBotones);
        panelBuscar.add(panelBuscarResultados);
        // Fin panel Buscar
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(15);
        gridLayout.setHgap(20);

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
        //mainConstraints.weightx = 1;
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
        panelSupervisor.setLayout(new GridLayout(0,2));
        panelSupervisor.add(btnSuperCrearAnaquel);
        panelSupervisor.add(btnSuperCrearProveedor);
        panelSupervisor.add(btnSuperCrearCategoria);
        panelSupervisor.add(btnSuperEliminarAnaquel);
        panelSupervisor.add(btnSuperEliminarProveedor);
        panelSupervisor.add(btnSuperEliminarCategoria);
        mainConstraints.fill = GridBagConstraints.VERTICAL;
        mainConstraints.gridx = 6;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 2;
        //mainConstraints.weightx = 1;
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
        mainConstraints.fill = GridBagConstraints.VERTICAL;
        mainConstraints.gridx = 6;
        mainConstraints.gridy = 2;
        mainConstraints.gridwidth = 2;
        mainConstraints.weighty = 1;
        supervisorAdminPanel.add(panelAdmin);
        this.frame.pack();
    }

    @Override
    public void crearEventos() {

        btnLogin.addActionListener(e -> {
            activateUserUI();
            System.out.println("login from userpanel");
        });
        btnProductoBuscar.addActionListener(e -> {
            String nombre = txtProductoNombre.getText();
            if(!nombre.isEmpty()) {
                ProductoDaoImpl productoDao = new ProductoDaoImpl();
                productoDao.findByName(txtProductoNombre.getText());
                System.out.println("buscando productos con nombre '" + nombre + "' en la base de datos");
            }
        });

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
                labelUsuarioNombre.setText("Nombre: Juan Luis Satalaya");
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
        txtProductoAnaquel.setEnabled(!lock);
        txtProductoCategoria.setEnabled(!lock);
        txtProductoNombre.setEnabled(!lock);
        txtProductoEstado.setEnabled(!lock);
        txtProductoProveedor.setEnabled(!lock);
    }
}
