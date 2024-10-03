import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

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
        JLabel locationLabel = new JLabel("Please enter your location (e.g., 'Berlin'):", SwingConstants.CENTER);
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
                    try {
                        // Dummy coordinates, need to implement the Google API to actually get city coords
                        double latitude = 42.3314;  // Detroit example latitude
                        double longitude = -83.0457;  // Detroit example longitude
                        
                        // Fetch weather data using the API
                        String weatherData = getWeatherData(latitude, longitude);
                        if (weatherData != null) {
                            JOptionPane.showMessageDialog(frame, "Weather Data: " + weatherData);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Unable to fetch weather data.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a location.");
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to fetch weather data from Open-Meteo API
    public static String getWeatherData(double latitude, double longitude) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();
                
                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(content.toString());
                JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");
                return "Temperature: " + currentWeather.getDouble("temperature") + "°C, Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h";
            } else {
                System.out.println("GET request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
