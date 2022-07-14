package view;

import dao.impl.AnaquelDaoImpl;
import dao.impl.CategoriaDaoImpl;
import model.Anaquel;
import model.Categoria;

import javax.swing.*;
import java.awt.*;
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


    }

    private void crearCategoria() {
        NuevaCategoriaDialog nuevaCategoriaDialog = new NuevaCategoriaDialog();
        nuevaCategoriaDialog.setVisible(true);
    }

    private void crearAnaquel() {
        NuevoAnaquelDialog dialog = new NuevoAnaquelDialog();
        dialog.setVisible(true);
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

class NuevaCategoriaDialog extends JDialog {
    public NuevaCategoriaDialog() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva categoría: ");
        if (!Objects.equals(nombre, "")) {
            CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            categoriaDao.save(categoria);
            JOptionPane.showMessageDialog(this, "Categoría creada correctamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre");
        }
    }
}