package util;

import controller.AdminController;

public class Login {

    public static int[] autenticar(String username, String password) {
        AdminController adminController = new AdminController();
        return adminController.credentialsExist(username, password);
    }
}
