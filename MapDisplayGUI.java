import javax.swing.*;
import java.awt.*;

public class MapDisplayGUI extends JFrame {
    public MapDisplayGUI(String[] locations) {
        setTitle("Map Display");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (String location : locations) {
            JLabel label = new JLabel(location);
            panel.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        String[] locations = {"Location 1", "Location 2", "Location 3"}; // Example locations
        SwingUtilities.invokeLater(() -> new MapDisplayGUI(locations));
    }
}
