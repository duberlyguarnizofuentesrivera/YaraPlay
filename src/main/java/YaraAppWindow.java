import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.SolarizedLightTheme;
import view.PanelAdmin;
import view.PanelSupervisor;
import view.PanelUsuario;

import javax.swing.*;

public class YaraAppWindow {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LafManager.install(new SolarizedLightTheme());
            PanelAdmin panelAdmin;
            PanelSupervisor panelSupervisor;
            PanelUsuario panelUsuario;
            JFrame frame;
            frame = new JFrame("Yara Play");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            panelUsuario = new PanelUsuario(frame);
            frame.setContentPane(panelUsuario);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
