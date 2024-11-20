package com.example.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Third {

    private Stage stage;
    private Scene scene;
    private Parent parent;
@FXML
    Label currently;
@FXML
Label type;
@FXML
Label speed;
@FXML
Label temp;
@FXML
Label label1;
@FXML
Label label2;
@FXML
Label label3;
@FXML
Label label4;
@FXML
Label label5;
@FXML
Label label6;
@FXML
Label label7;
@FXML
Label label8;
@FXML
Label label9;
@FXML
Label label10;
@FXML
Label label11;
@FXML
Label label12;
@FXML
Label label13;
@FXML
Label label14;
@FXML
Label label15;
@FXML
Label label16;
@FXML
Label label17;
@FXML
Label label18;
@FXML
Label label19;
@FXML
Label label20;
@FXML
Label label21;
@FXML
Label label22;
@FXML
Label label23;
@FXML
Label label24;
@FXML
Label chance;
@FXML
Label Precaction;
@FXML
Label Time;
@FXML
Label loc;

String store;
@FXML
public void getlistofthings(String location) {
    store = location;
    fetchWeatherData(location);  // Fetch weather data for the location

    // Set static text labels
    currently.setText("Currently");
    type.setText("Weather in " + location + " is:");
    chance.setText("Chance of Rain:");  // Update with actual data if available
    Time.setText("Time: " + java.time.LocalDate.now().toString());  // Display current date
    loc.setText(location);
}

    public void switchtosecence3(ActionEvent event) throws IOException {
        FXMLLoader root= new FXMLLoader(getClass().getResource("third.fxml"));
        parent=root.load();
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(parent);
        Second second=root.getController();
          second.getlistofthings(store);
       String be=getClass().getResource("Background2.css").toExternalForm();
       scene.getStylesheets().add(be);
        stage.setScene(scene);
        stage.show();

    }

    public void switchtosecence1(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        String be=getClass().getResource("Background2.css").toExternalForm();
        scene.getStylesheets().add(be);
        stage.setScene(scene);
        stage.show();

    }


    public void getlistofthings(MouseEvent mouseEvent) {

   }
    
    private void fetchWeatherData(String location) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=YOUR_LATITUDE&longitude=YOUR_LONGITUDE&current_weather=true";
        
        try {
            // Make API call
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");

            // Extract data
            String temperature = currentWeather.getString("temperature");
            String windSpeed = currentWeather.getString("windspeed");
            String weatherCode = currentWeather.getString("weathercode");

            // Update labels with fetched data
            temp.setText("Temperature: " + temperature + "°C");
            speed.setText("Wind Speed: " + windSpeed + " km/h");
            type.setText("Weather Code: " + weatherCode);
        } catch (Exception e) {
            e.printStackTrace();
            temp.setText("Error fetching data.");
        }
    }
}
