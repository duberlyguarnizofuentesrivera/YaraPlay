package view;

import controller.AdminController;
import util.Login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelLogin extends JDialog {
    private final JTextField txtLoginUserName;
    private final JPasswordField txtLoginPassword;

    private boolean success = false;
    private boolean userLoggedIn = false;
    private boolean supervisorLoggedIn = false;
    private boolean adminLoggedIn = false;

    public PanelLogin(Frame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblLoginUserName = new JLabel("Usuario: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lblLoginUserName, cs);

        txtLoginUserName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(txtLoginUserName, cs);

        JLabel lblLoginPassword = new JLabel("Contraseña: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lblLoginPassword, cs);

        txtLoginPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(txtLoginPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        JButton btnLogin = new JButton("Iniciar Sesión");

        btnLogin.addActionListener(e -> {
            int[] resultado = Login.autenticar(getUsername(), getPassword());
            if (resultado[0] == 1) {
                JOptionPane.showMessageDialog(PanelLogin.this,
                        "Hola " + getUsername() + "! Has iniciado sesión correctamente.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                success = true;
                //resultado[1] = id de persona, no usado aquí
                if (resultado[2] == 1) {
                    adminLoggedIn = true;
                } else if (resultado[2] == 2) {
                    userLoggedIn = true;
                } else if (resultado[2] == 3) {
                    supervisorLoggedIn = true;
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(PanelLogin.this,
                        "Usuario o contraseña inválidos!",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                // reset username and password
                txtLoginUserName.setText("");
                txtLoginPassword.setText("");
                success = false;
                userLoggedIn = false;
                supervisorLoggedIn = false;
                adminLoggedIn = false;
            }
        });
        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
        this.getRootPane().setDefaultButton(btnLogin);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return txtLoginUserName.getText().trim();
    }

    public String getPassword() {
        return new String(txtLoginPassword.getPassword());
    }

    public boolean successfulLogin() {
        return success;
    }

    public boolean userLoggedIn() {
        return userLoggedIn;
    }

    public boolean supervisorLoggedIn() {
        return supervisorLoggedIn;
    }

    public boolean adminLoggedIn() {
        return adminLoggedIn;
    }

    public String getPersonName() {
        AdminController adminController = new AdminController();
        return adminController.getCompleteNameFromUsername(getUsername());
    }
}