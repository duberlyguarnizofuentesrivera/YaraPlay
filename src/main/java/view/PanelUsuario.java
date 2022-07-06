package view;


import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JPanel implements PanelYara {
    private final JTextField txtProductoNombre = new JTextField();
    private final JTextField txtProductoAnaquel = new JTextField();
    private final JTextField txtProductoEstado = new JTextField();
    private final JTextField txtProductoCategoria = new JTextField();
    private final JTextField txtProductoProveedor = new JTextField();
    private final JButton btnProductoBuscar = new JButton("Buscar");
    private final JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
    private final JButton btnRegistrarSalida = new JButton("Registrar Salida");
    private final JButton btnAgregarProducto = new JButton("Agregar Producto");
    private final JButton btnEliminarProducto = new JButton("Eliminar Producto");
    private final JButton btnLogin = new JButton("Login");
    JFrame frame;

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
        GridBagConstraints mainConstraints = new GridBagConstraints();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Buscar
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
        txtProductoNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtProductoAnaquel.setBorder(BorderFactory.createTitledBorder("Anaquel"));
        txtProductoCategoria.setBorder(BorderFactory.createTitledBorder("Categoria"));
        txtProductoEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        txtProductoProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));
        btnProductoBuscar.setMargin(new Insets(10, 0, 10, 0));
        btnProductoBuscar.setMaximumSize(new Dimension(150, 30));
        JTextArea txtResultados = new JTextArea(10, 25);
        txtResultados.setEditable(false);
        txtResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JPanel panelBuscarControles = new JPanel();
        panelBuscarControles.setLayout(new BoxLayout(panelBuscarControles, BoxLayout.Y_AXIS));
        panelBuscarControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarControles.add(labelBienvenido);
        panelBuscarControles.add(txtProductoNombre);
        panelBuscarControles.add(txtProductoAnaquel);
        panelBuscarControles.add(txtProductoCategoria);
        panelBuscarControles.add(txtProductoEstado);
        panelBuscarControles.add(txtProductoProveedor);

        JPanel panelBuscarBotones = new JPanel();
        panelBuscarBotones.setLayout(new BoxLayout(panelBuscarBotones, BoxLayout.Y_AXIS));
        panelBuscarBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarBotones.add(btnProductoBuscar);
        JPanel panelBuscarResultados = new JPanel();
        panelBuscarResultados.setLayout(new BoxLayout(panelBuscarResultados, BoxLayout.Y_AXIS));
        panelBuscarResultados.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarResultados.add(txtResultados);

        panelBuscar.add(panelBuscarControles);
        panelBuscar.add(panelBuscarBotones);
        panelBuscar.add(panelBuscarResultados);
        // Fin panel Buscar
        GridLayout gridLayout = new GridLayout(3, 1);
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
        JLabel labelUsuario = new JLabel("Modo de Usuario: Usuario");
        JLabel labelUsuarioNombre = new JLabel("Nombre: Juan Luis Satalaya Vladivieso");
        JLabel labelUsuarioRol = new JLabel("Rol: Administrador");
        JLabel labelUsuarioFecha = new JLabel("Login: 01/01/2019");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelEntradaSalida.add(btnRegistrarSalida, constraints);
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

        panelOperaciones.add(panelDatosUsuario);
        panelOperaciones.add(panelEntradaSalida);
        panelOperaciones.add(panelAgregarEliminarProducto);
        mainConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 3;
        this.add(panelBuscar, mainConstraints);
        mainConstraints.gridx = 3;
        mainConstraints.gridy = 0;
        mainConstraints.gridwidth = 2;
        mainConstraints.weightx = 1;
        this.add(panelOperaciones, mainConstraints);
    }

    @Override
    public void crearEventos() {

        btnLogin.addActionListener(e -> {
            PanelLogin ventanaLogin = new PanelLogin(this.frame);
            ventanaLogin.setVisible(true);
            if (ventanaLogin.loginCorrecto()) {
                btnLogin.setText("Hola " + ventanaLogin.getUsername() + "!");
                lockUI(false);
            } else {
                lockUI(true);
            }
        });


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

    private void lockUI(boolean lock) {
        btnProductoBuscar.setEnabled(!lock);
        btnRegistrarEntrada.setEnabled(!lock);
        btnRegistrarSalida.setEnabled(!lock);
        btnAgregarProducto.setEnabled(!lock);
        btnEliminarProducto.setEnabled(!lock);
        txtProductoAnaquel.setEnabled(!lock);
        txtProductoCategoria.setEnabled(!lock);
        txtProductoNombre.setEnabled(!lock);
        txtProductoEstado.setEnabled(!lock);
        txtProductoProveedor.setEnabled(!lock);

    }
}
