package util;

import controller.AdminController;

public class Login {
    private Login() {
        //en el futuro: generar API para login remoto
    }

    public static int[] autenticar(String username, String password) {
        AdminController adminController = new AdminController();
        return adminController.credentialsExist(username, password);
    }
}
