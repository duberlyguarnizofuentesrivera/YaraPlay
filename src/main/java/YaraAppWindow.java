import dao.JdbcConnection;
import view.PanelAdmin;
import view.PanelSupervisor;
import view.PanelUsuario;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YaraAppWindow {
    PanelAdmin panelAdmin;
    PanelSupervisor panelSupervisor;
    PanelUsuario panelUsuario;
    JFrame frame;

    public static void main(String[] args) throws SQLException {
        JdbcConnection jdbcConnection = new JdbcConnection();
        ResultSet resultados=null;
        try {
            resultados = jdbcConnection.execute("SELECT * FROM persona");
            System.out.println(resultados);
            while (resultados.next()) {
                System.out.println(resultados.getString("nombre") + " " + resultados.getString("apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcConnection.close();
            assert resultados != null;
            resultados.close();
        }

    }
}
