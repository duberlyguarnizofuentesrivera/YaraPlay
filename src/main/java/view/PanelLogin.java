package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import util.Login;

public class PanelLogin extends JDialog {
    private final JTextField txtLoginUserName;
    private final JPasswordField txtLoginPassword;
    private final JLabel lblLoginUserName;
    private final JLabel lblLoginPassword;
    private final JButton btnLogin;
    private final JButton btnCancel;

    private boolean correcto = false;
    private boolean userCorrecto = false;
    private boolean supervisorCorrecto = false;
    private boolean adminCorrecto = false;

    public PanelLogin(Frame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lblLoginUserName = new JLabel("Usuario: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lblLoginUserName, cs);

        txtLoginUserName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(txtLoginUserName, cs);

        lblLoginPassword = new JLabel("Contraseña: ");
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

        btnLogin = new JButton("Iniciar Sesión");

        btnLogin.addActionListener(e -> {
            int[] resultado = Login.autenticar(getUsername(), getPassword());
            if (resultado[0] == 1) {
                JOptionPane.showMessageDialog(PanelLogin.this,
                        "Hola " + getUsername() + "! Has iniciado sesión correctamente.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                correcto = true;
                if (resultado[1] == 1) {
                    userCorrecto = true;
                } else if (resultado[1] == 2) {
                    supervisorCorrecto = true;
                } else if (resultado[1] == 3) {
                    adminCorrecto = true;
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
                correcto = false;
                userCorrecto = false;
                supervisorCorrecto = false;
                adminCorrecto = false;
            }
        });
        btnCancel = new JButton("Cancelar");
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

    public boolean loginCorrecto() {
        return correcto;
    }

    public boolean userCorrecto() {
        return userCorrecto;
    }

    public boolean supervisorCorrecto() {
        return supervisorCorrecto;
    }

    public boolean adminCorrecto() {
        return adminCorrecto;
    }
}