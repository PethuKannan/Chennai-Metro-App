import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MetroAppGUI extends JFrame implements ActionListener {
    private Graph_M graph;

    private JButton showStationsButton;
    private JButton showMapButton;
    private JButton shortestDistanceButton;
    private JButton shortestTimeButton;
    private JButton shortestPathDistanceButton;
    private JButton shortestPathTimeButton;

    public MetroAppGUI(Graph_M graph) {
        this.graph = graph;

        setTitle("Metro App");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        showStationsButton = createButton("Show Stations");
        panel.add(showStationsButton, gbc);

        showMapButton = createButton("Show Map");
        gbc.gridy++;
        panel.add(showMapButton, gbc);

        shortestDistanceButton = createButton("Shortest Distance");
        gbc.gridy++;
        panel.add(shortestDistanceButton, gbc);

        shortestTimeButton = createButton("Shortest Time");
        gbc.gridy++;
        panel.add(shortestTimeButton, gbc);

        shortestPathDistanceButton = createButton("Shortest Path by Distance");
        gbc.gridy++;
        panel.add(shortestPathDistanceButton, gbc);

        shortestPathTimeButton = createButton("Shortest Path by Time");
        gbc.gridy++;
        panel.add(shortestPathTimeButton, gbc);

        add(panel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(200, 40)); // Adjust button size as needed
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showStationsButton) {
            JOptionPane.showMessageDialog(this, "List of stations:\n" + graph.numVetex() + " stations in total.");
        } else if (e.getSource() == showMapButton) {
            graph.display_Map();
        } else if (e.getSource() == shortestDistanceButton) {
            // Action for shortest distance
            String source = JOptionPane.showInputDialog(this, "Enter source station:");
            String destination = JOptionPane.showInputDialog(this, "Enter destination station:");
            int distance = graph.dijkstra(source, destination, false);
            if (distance != 0) {
                JOptionPane.showMessageDialog(this, "Shortest distance from " + source + " to " + destination + " is " + distance + "KM");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid stations or no path exists between them.");
            }
        } else if (e.getSource() == shortestTimeButton) {
            // Action for shortest time
            String source = JOptionPane.showInputDialog(this, "Enter source station:");
            String destination = JOptionPane.showInputDialog(this, "Enter destination station:");
            int time = graph.dijkstra(source, destination, true) / 60;
            if (time != 0) {
                JOptionPane.showMessageDialog(this, "Shortest time from " + source + " to " + destination + " is " + time + " minutes");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid stations or no path exists between them.");
            }
        } else if (e.getSource() == shortestPathDistanceButton) {
            // Action for shortest path by distance
            String source = JOptionPane.showInputDialog(this, "Enter source station:");
            String destination = JOptionPane.showInputDialog(this, "Enter destination station:");
            String path = graph.Get_Minimum_Distance(source, destination);
            JOptionPane.showMessageDialog(this, "Shortest path (distance wise) from " + source + " to " + destination + " is:\n" + path);
        } else if (e.getSource() == shortestPathTimeButton) {
            // Action for shortest path by time
            String source = JOptionPane.showInputDialog(this, "Enter source station:");
            String destination = JOptionPane.showInputDialog(this, "Enter destination station:");
            String path = graph.Get_Minimum_Time(source, destination);
            JOptionPane.showMessageDialog(this, "Shortest path (time wise) from " + source + " to " + destination + " is:\n" + path);
        }
    }

    public static void main(String[] args) {
        Graph_M graph = new Graph_M();
        Graph_M.Create_Metro_Map(graph);
        SwingUtilities.invokeLater(() -> new MetroAppGUI(graph));
    }
}
