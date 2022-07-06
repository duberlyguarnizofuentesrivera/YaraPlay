package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import util.Login;

public class PanelLogin extends JDialog {
    private final JTextField tfUsername;
    private final JPasswordField pfPassword;
    private final JLabel lbUsername;
    private final JLabel lbPassword;
    private final JButton btnLogin;
    private final JButton btnCancel;
    private boolean correcto;

    public PanelLogin(Frame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Usuario: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Contraseña: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Iniciar Sesión");

        btnLogin.addActionListener(e -> {
            if (Login.autenticar(getUsername(), getPassword())) {
                JOptionPane.showMessageDialog(PanelLogin.this,
                        "Hola " + getUsername() + "! Has iniciado sesión correctamente.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                correcto = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(PanelLogin.this,
                        "Usuario o contraseña inválidos!",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                // reset username and password
                tfUsername.setText("");
                pfPassword.setText("");
                correcto = false;

            }
        });
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public String getPassword() {
        return new String(pfPassword.getPassword());
    }

    public boolean loginCorrecto() {
        return correcto;
    }
}