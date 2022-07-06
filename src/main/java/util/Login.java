package util;

public class Login {

    public static boolean autenticar(String username, String password) {
        // hardcoded username and password
        if (username.equals("user") && password.equals("user")) {
            return true;
        }
        if (username.equals("super") && password.equals("super")) {
            return true;
        }
        return username.equals("admin") && password.equals("admin");
    }
}
