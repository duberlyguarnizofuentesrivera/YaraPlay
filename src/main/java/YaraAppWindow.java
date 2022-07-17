import com.formdev.flatlaf.FlatIntelliJLaf;
import view.UserPanel;
import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class YaraAppWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            UserPanel userPanel;
            JFrame frame;
            frame = new JFrame("Yara Play");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            userPanel = new UserPanel(frame);
            frame.setContentPane(userPanel);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
