package view;

import controller.UserController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserPanel extends JPanel implements PanelYara {
    private final JTextField txtProductName = new JTextField();
    private final JTextField txtProductState = new JTextField();
    private final JTextField txtProductCategory = new JTextField();
    private final JTextField txtProductSupplier = new JTextField();
    final JTable tblResults = new JTable();
    final JLabel lblResults = new JLabel();
    private final JButton btnProductSearch = new JButton("Buscar");
    private final JButton btnProductStock = new JButton("Stock de producto");
    private final JButton btnCategoryStock = new JButton("Stock de categoría");
    private final JButton btnRecordIncomingProduct = new JButton("Registrar Entrada");
    private final JButton btnRecordOutgoingProduct = new JButton("Registrar Salida");
    private final JButton btnViewRecords = new JButton("Revisar Registros");
    private final JButton btnProductAdd = new JButton("Agregar Producto");
    private final JButton btnProductDelete = new JButton("Eliminar Producto");
    final JButton btnProductViewBadState = new JButton("Ver productos por vencer");
    final JButton btnLogin = new JButton("Login");

    final JLabel labelUsuarioNombre = new JLabel("Nombre: (inicie sesión)");
    final JLabel labelUsuarioRol = new JLabel("Rol: (inicie sesión)");
    final JLabel labelUsuarioFecha = new JLabel("Login: --/--/--");

    //----Controles de panel supervisor---------------
    final JButton btnSuperCreateShelf = new JButton("Crear Anaquel");
    final JButton btnSuperCreateSupplier = new JButton("Crear Proveedor");
    final JButton btnSuperCreateCategory = new JButton("Crear Categoría");
    final JButton btnSuperDeleteShelf = new JButton("Eliminar Anaquel");
    final JButton btnSuperDeleteSupplier = new JButton("Eliminar Proveedor");
    final JButton btnSuperDeleteCategory = new JButton("Eliminar Categoría");
    final JButton btnSuperViewShelves = new JButton("Ver Anaqueles");
    final JButton btnSuperViewSuppliers = new JButton("Ver Proveedores");
    final JButton btnSuperViewCategories = new JButton("Ver Categorías");
    //----Controles de panel admin---------------
    final JButton btnAdminCreateUser = new JButton("Crear Contacto/Empleado");
    final JButton btnAdminDeleteUser = new JButton("Eliminar Usuario");
    final JButton btnAdminViewCredentials = new JButton("Listar Credenciales");
    final JButton btnAdminCreateStore = new JButton("Crear Sucursal");
    final JButton btnAdminDeleteStore = new JButton("Eliminar Sucursal");
    final JButton btnAdminCreateCredentials = new JButton("Crear Credenciales");
    final JButton btnAdminDeleteCredentials = new JButton("Eliminar Credenciales");
    final JButton btnAdminViewUsers = new JButton("Ver Usuarios");
    final JButton btnAdminViewStores = new JButton("Ver Sucursales");

    final JFrame frame;
    final JTabbedPane tabs = new JTabbedPane();
    final GridBagConstraints mainConstraints = new GridBagConstraints();

    final SupervisorPanel supervisor = new SupervisorPanel(this);
    final AdminPanel admin = new AdminPanel(this);

    public UserPanel(JFrame frame) {
        super();
        readConfig();
        createControls();
        createEvents();
        lockUI(true);
        this.frame = frame;
    }

    @Override
    public void createControls() {

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Buscar
        JPanel userContainerPanel = new JPanel();
        userContainerPanel.setLayout(new GridBagLayout());
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
        labelBienvenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtProductName.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtProductCategory.setBorder(BorderFactory.createTitledBorder("Categoría"));
        txtProductState.setBorder(BorderFactory.createTitledBorder("Estado"));
        txtProductSupplier.setBorder(BorderFactory.createTitledBorder("Proveedor"));

        JPanel panelBuscarControles = new JPanel();
        panelBuscarControles.setLayout(new BoxLayout(panelBuscarControles, BoxLayout.Y_AXIS));
        panelBuscarControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarControles.add(labelBienvenido);
        panelBuscarControles.add(txtProductName);
        panelBuscarControles.add(txtProductCategory);
        panelBuscarControles.add(txtProductState);
        panelBuscarControles.add(txtProductSupplier);

        JPanel panelBuscarBotones = new JPanel();
        panelBuscarBotones.setLayout(new BoxLayout(panelBuscarBotones, BoxLayout.X_AXIS));
        panelBuscarBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarBotones.add(btnProductSearch);
        panelBuscarBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        panelBuscarBotones.add(btnProductStock);
        panelBuscarBotones.add(Box.createRigidArea(new Dimension(5, 0)));
        panelBuscarBotones.add(btnCategoryStock);
        JPanel panelBuscarResultados = new JPanel();
        panelBuscarResultados.setLayout(new BoxLayout(panelBuscarResultados, BoxLayout.Y_AXIS));
        panelBuscarResultados.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tblResults.setBounds(30, 40, 150, 75);
        JScrollPane scrollResultados = new JScrollPane(tblResults);
        lblResults.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBuscarResultados.add(lblResults);
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
        btnRecordIncomingProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRecordIncomingProduct.setMaximumSize(new Dimension(150, 30));
        btnRecordOutgoingProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelEntradaSalida.add(btnRecordIncomingProduct, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelEntradaSalida.add(btnRecordOutgoingProduct, constraints);
        panelEntradaSalida.add(btnViewRecords);
        // Fin panel Registrar Entradas/Salidas

        // Inicio panel Agregar/Eliminar Productos
        JPanel panelAgregarEliminarProducto = new JPanel();
        panelAgregarEliminarProducto.setLayout(new GridBagLayout());
        panelAgregarEliminarProducto.setBorder(BorderFactory.createTitledBorder("Agregar/Eliminar Productos"));

        btnProductAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnProductDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelAgregarEliminarProducto.add(btnProductAdd, constraints);
        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelAgregarEliminarProducto.add(btnProductDelete, constraints);
        // Fin panel Agregar/Eliminar Productos

        //Inicio panel Otras opciones
        JPanel panelOtrasOpciones = new JPanel();
        panelOtrasOpciones.setLayout(new GridBagLayout());
        GridBagConstraints constraintsOtrasOpciones = new GridBagConstraints();
        panelOtrasOpciones.setBorder(BorderFactory.createTitledBorder("Otras Opciones"));
        btnProductViewBadState.setAlignmentX(Component.CENTER_ALIGNMENT);
        constraintsOtrasOpciones.insets = new Insets(10, 10, 5, 10);
        constraintsOtrasOpciones.gridx = 0;
        constraintsOtrasOpciones.gridy = 0;
        constraintsOtrasOpciones.gridwidth = 1;
        constraintsOtrasOpciones.gridheight = 1;
        constraintsOtrasOpciones.fill = GridBagConstraints.HORIZONTAL;
        panelOtrasOpciones.add(btnProductViewBadState, constraintsOtrasOpciones);

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
        panelSupervisor.add(btnSuperCreateShelf, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperDeleteShelf, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCreateCategory, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperDeleteCategory, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperCreateSupplier, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperDeleteSupplier, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperViewShelves, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperViewCategories, constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panelSupervisor.add(btnSuperViewSuppliers, constraints);
        tabs.addTab("Supervisión", panelSupervisor);
        supervisor.createEvents();
    }

    private void crearControlesAdmin() {
        JPanel panelAdmin = new JPanel();
        panelAdmin.setBorder(BorderFactory.createTitledBorder("Opciones De Administrador"));
        panelAdmin.setLayout(new GridBagLayout());
        btnAdminDeleteStore.setBackground(Color.RED);
        btnAdminDeleteStore.setForeground(Color.WHITE);
        btnAdminDeleteUser.setBackground(Color.RED);
        btnAdminDeleteUser.setForeground(Color.WHITE);
        btnAdminDeleteCredentials.setBackground(Color.RED);
        btnAdminDeleteCredentials.setForeground(Color.WHITE);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminViewUsers, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCreateUser, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminDeleteUser, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminViewStores, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCreateStore, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminDeleteStore, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminViewCredentials, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminCreateCredentials, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panelAdmin.add(btnAdminDeleteCredentials, constraints);
        tabs.addTab("Administración", panelAdmin);
        this.frame.pack();
        admin.createEvents();
    }

    @Override
    public void createEvents() {

        btnLogin.addActionListener(e -> activateUserUI());
        btnProductSearch.addActionListener(e -> searchProductByName());
        btnCategoryStock.addActionListener(e -> searchCategoryStockByName());
        btnProductStock.addActionListener(e -> searchProductStockByName());
        btnProductViewBadState.addActionListener(e -> listarProductosMalEstado());
        btnProductAdd.addActionListener(e -> agregarProducto());
        btnProductDelete.addActionListener(e -> eliminarProducto());
    }

    private void eliminarProducto() {
        DeleteProductDialog deleteProductDialog = new DeleteProductDialog(this.frame, true);
        deleteProductDialog.setVisible(true);
    }

    private void agregarProducto() {
        NewProductDialog newProductDialog = new NewProductDialog(this.frame, true);
        newProductDialog.setVisible(true);
    }

    private void listarProductosMalEstado() {
        UserController controller = new UserController();
        String[][] datos = controller.listProductInBadCondition();
        String[] columnas = {"Producto", "Estado"};
        lblResults.setText("Lista de productos en mal estado");
        tblResults.setModel(new DefaultTableModel(datos, columnas));
    }


    private void searchCategoryStockByName() {
        String nombre = txtProductCategory.getText();
        if (!nombre.isEmpty()) {
            String[] columnas = {"Nombre", "Stock"};
            UserController controller = new UserController();
            lblResults.setText("Stock de la categoría " + nombre);
            tblResults.setModel(new DefaultTableModel(controller.findStockOfCategoryByName(nombre), columnas));
        }
    }

    private void searchProductStockByName() {
        String nombre = txtProductName.getText();
        if (!nombre.isEmpty()) {
            UserController controller = new UserController();
            String[][] datos = controller.findStockOfProductsByName(nombre);
            String[] columnas = {"Producto", "Proveedor", "Stock"};
            lblResults.setText("Stock de producto " + nombre);
            tblResults.setModel(new DefaultTableModel(datos, columnas));
        }
    }

    private void searchProductByName() {
        String productName = txtProductName.getText();
        String productCategory = txtProductCategory.getText();
        String productState = txtProductState.getText();
        String productSupplier = txtProductSupplier.getText();
        String[] columnas = {"ID", "Nombre", "Categoría", "Proveedor", "Anaquel", "Estado"};
        UserController controller = new UserController();
        String[][] datos = controller.findProducts(productName, productCategory, productState, productSupplier);
        lblResults.setText("Resultados de la búsqueda de productos");
        tblResults.setModel(new DefaultTableModel(datos, columnas));
    }

    protected void activateUserUI() {
        if (Objects.equals(btnLogin.getText(), "Login")) {
            PanelLogin ventanaLogin = new PanelLogin(this.frame);
            ventanaLogin.setVisible(true);
            if (ventanaLogin.loginCorrecto()) {
                String username = ventanaLogin.getUsername();
                String nombrePersona = ventanaLogin.getNombrePersona();
                btnLogin.setText("Hola " + username + "!");
                lockUI(false);
                btnLogin.setForeground(Color.BLACK);
                btnLogin.setBackground(Color.GREEN);
                btnLogin.setText("Cerrar Sesión");
                labelUsuarioNombre.setText("Nombre: " + nombrePersona);
                if (ventanaLogin.userCorrecto()) {
                    labelUsuarioRol.setText("Rol: USUARIO");
                    crearControlesUsuario();
                    deleteTabs(1);
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
                    deleteTabs(2);
                }
                labelUsuarioFecha.setText("Login: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            } else {
                deleteTabs(1);
                lockUI(true);
            }
        } else {
            deleteTabs(0);
            btnLogin.setText("Login");
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setBackground(Color.RED);
            lockUI(true);
        }
    }

    private void deleteTabs(int userType) {
        //userType: 0 = sin sesión, 1 = usuario, 2 = supervisor, 3 = administrador
        int tabsNumber = tabs.getTabCount();
        if (tabsNumber > userType + 2) {
            for (int i = userType; i < tabsNumber; i++) {
                tabsNumber = tabs.getTabCount();
                if (tabsNumber != 2) {
                    tabs.remove(tabsNumber - 1);
                }
            }
        }
    }

    @Override
    public void readConfig() {
        //Para extender la configuración de la aplicación en un futuro
    }

    protected void lockUI(boolean lock) {
        btnProductSearch.setEnabled(!lock);
        btnRecordIncomingProduct.setEnabled(!lock);
        btnRecordOutgoingProduct.setEnabled(!lock);
        btnProductAdd.setEnabled(!lock);
        btnProductDelete.setEnabled(!lock);
        btnProductStock.setEnabled(!lock);
        btnCategoryStock.setEnabled(!lock);
        btnViewRecords.setEnabled(!lock);
        btnProductViewBadState.setEnabled(!lock);
        txtProductCategory.setEnabled(!lock);
        txtProductName.setEnabled(!lock);
        txtProductState.setEnabled(!lock);
        txtProductSupplier.setEnabled(!lock);
    }
}

class NewProductDialog extends JDialog {
    private final JTextField txtProductName = new JTextField(24);
    private final JTextField txtProductCategory = new JTextField(24);
    private final JTextField txtProductSupplier = new JTextField(36);

    public NewProductDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Nuevo Producto");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        txtProductName.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtProductCategory.setBorder(BorderFactory.createTitledBorder("Categoría"));
        txtProductSupplier.setBorder(BorderFactory.createTitledBorder("Proveedor"));

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(15);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(txtProductName);
        panel.add(txtProductCategory);
        panel.add(txtProductSupplier);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(Color.GREEN);
        btnAgregar.setForeground(Color.BLACK);
        panel.add(btnAgregar);
        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);
        this.add(panel);
        this.pack();

        btnAgregar.addActionListener(e -> {
            if (txtProductName.getText().isEmpty() || txtProductCategory.getText().isEmpty() || txtProductSupplier.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            } else {
                UserController controller = new UserController();
                controller.createProduct(this, txtProductName.getText(), txtProductCategory.getText(), txtProductSupplier.getText());
            }
        });
        btnCancelar.addActionListener(e -> dispose());
    }


}

class DeleteProductDialog extends JDialog {
    public DeleteProductDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Eliminar Producto");
        JLabel label = new JLabel("<html>Primero busque el ID del producto en la sección  'buscar',<br> luego ingrese el id del producto a eliminar en el cuadro de abajo</html>");
        JTextField txtId = new JTextField(16);
        JButton btnDelete = new JButton("Eliminar");
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Cancelar");
        txtId.setBorder(BorderFactory.createTitledBorder("ID del producto"));
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(15);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(label);
        panel.add(txtId);
        panel.add(btnDelete);
        panel.add(btnCancel);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        btnDelete.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo ID es obligatorio");
            } else {
                UserController controller = new UserController();
                controller.deleteProduct(this, txtId.getText());
            }
        });
        btnCancel.addActionListener(e -> dispose());
    }


}
