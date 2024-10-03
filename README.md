import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherApp {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Weather Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        
        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column
        
        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Weather App", SwingConstants.CENTER);
        panel.add(welcomeLabel);
        
        // Label for location input
        JLabel locationLabel = new JLabel("Please enter your current location:", SwingConstants.CENTER);
        panel.add(locationLabel);
        
        // Text field for location input
        JTextField locationField = new JTextField();
        panel.add(locationField);
        
        // Button to submit location
        JButton submitButton = new JButton("Get Weather");
        panel.add(submitButton);
        
        // Add panel to frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        // Button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = locationField.getText();
                if (!location.isEmpty()) {
                    // Here you'll call the API with the location
                    JOptionPane.showMessageDialog(frame, "Fetching weather for: " + location);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a location.");
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}
