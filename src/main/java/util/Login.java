package util;

public class Login {

    public static int[] autenticar(String username, String password) {
        // hardcoded username and password
        if (username.equals("user") && password.equals("user")) {
            return new int[]{1, 1};
        }
        if (username.equals("super") && password.equals("super")) {
            return new int[]{1, 2};
        }
        if (username.equals("admin") && password.equals("admin")) {
            return new int[]{1, 3};
        }
        return new int[]{0, 0};
    }
}
