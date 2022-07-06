package view;

import org.apache.log4j.Layout;

import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JPanel implements PanelYara {
    public PanelUsuario() {
        super();
        leerConfiguracion();
        crearControles();
        crearEventos();
        crearLayout();
        inicializar();
    }

    @Override
    public void crearControles() {
        this.setLayout(new GridLayout(0, 2));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Inicio panel Buscar
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar"));

        JLabel labelBienvenido = new JLabel("Bienvenido, utilice los campos para buscar una producto:");
        JTextField txtProductoNombre = new JTextField();
        txtProductoNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        JTextField txtProductoAnaquel = new JTextField();
        txtProductoAnaquel.setBorder(BorderFactory.createTitledBorder("Anaquel"));
        JTextField txtProductoCategoria = new JTextField();
        txtProductoCategoria.setBorder(BorderFactory.createTitledBorder("Categoria"));
        JTextField txtProductoEstado = new JTextField();
        txtProductoEstado.setBorder(BorderFactory.createTitledBorder("Estado"));
        JTextField txtProductoProveedor = new JTextField();
        txtProductoProveedor.setBorder(BorderFactory.createTitledBorder("Proveedor"));
        JButton btnProductoBuscar = new JButton("Buscar");
        btnProductoBuscar.setMargin(new Insets(10, 10, 10, 10));
        JTextArea txtResultados = new JTextArea(10, 25);
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
        panelBuscarBotones.setLayout(new BoxLayout(panelBuscarBotones, BoxLayout.X_AXIS));
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

        // Inicio panel Registrar Entradas/Salidas
        JPanel panelEntradaSalida = new JPanel();
        panelEntradaSalida.setLayout(gridLayout);
        panelEntradaSalida.setBorder(BorderFactory.createTitledBorder("Registrar Entrada/Salida De Productos"));
        JButton btnRegistrarEntrada = new JButton("Registrar Entrada");
        btnRegistrarEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton btnRegistrarSalida = new JButton("Registrar Salida");
        btnRegistrarSalida.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarSalida.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEntradaSalida.add(btnRegistrarEntrada);
        panelEntradaSalida.add(btnRegistrarSalida);
        // Fin panel Registrar Entradas/Salidas

        JPanel panelAgregarEliminarProducto = new JPanel();
        panelAgregarEliminarProducto.setLayout(gridLayout);
        JButton btnAgregarProducto = new JButton("Agregar Producto");
        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        panelAgregarEliminarProducto.add(btnAgregarProducto);
        panelAgregarEliminarProducto.add(btnEliminarProducto);

        panelOperaciones.add(panelEntradaSalida);
        panelOperaciones.add(panelAgregarEliminarProducto);

        this.add(panelBuscar);
        this.add(panelOperaciones);

    }

    @Override
    public void crearEventos() {

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
}
