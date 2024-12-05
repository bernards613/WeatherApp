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

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Third {

    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    Label currently;
@FXML
Label type = new Label("");
@FXML
Label speed = new Label("");
@FXML
Label temp = new Label("");
@FXML
Label label1am = new Label("");
@FXML
Label label2am = new Label("");
@FXML
Label label3am = new Label("");
@FXML
Label label4am = new Label("");
@FXML
Label label5am = new Label("");
@FXML
Label label6am = new Label("");
@FXML
Label label7am = new Label("");
@FXML
Label label8am = new Label("");
@FXML
Label label9am = new Label("");
@FXML
Label label10am = new Label("");
@FXML
Label label11am = new Label("");
@FXML
Label label12am = new Label("");
@FXML
Label label3pm = new Label("");
@FXML
Label label4pm = new Label("");
@FXML
Label label5pm = new Label("");
@FXML
Label label6pm = new Label("");
@FXML
Label label7pm = new Label("");
@FXML
Label label8pm = new Label("");
@FXML
Label label9pm = new Label("");
@FXML
Label label2pm = new Label("");
@FXML
Label label1pm = new Label("");
@FXML
Label label10pm = new Label("");
@FXML
Label label12pm = new Label("");
@FXML
Label label11pm = new Label("");
@FXML
Label chance = new Label("");
@FXML
Label Precipitation = new Label("");
@FXML
Label Date = new Label("");
@FXML
Label loc = new Label("");
@FXML
Label temp1 = new Label("");
@FXML
Label temp2 = new Label("");
@FXML
Label temp3 = new Label("");
@FXML
Label temp4 = new Label("");
@FXML
Label temp5 = new Label("");
@FXML
Label temp6 = new Label("");
@FXML
Label temp7 = new Label("");
String store;
@FXML

public void getlistofthings(String location) {
    store = location;

    // Coordinates for city
    double latitude = 0, longitude = 0;
	String coordinateCall = coordCall(store);
	 if (!coordinateCall.startsWith("Error")) {
         try {
        	 JSONObject jsonResponse = new JSONObject(coordinateCall);
        	 JSONArray coords = jsonResponse.getJSONArray("results");
        	 if(coords.length() > 0 ) {
        	 
        	 JSONObject coordsObj = coords.getJSONObject(0);
        	 
        	 latitude = coordsObj.getDouble("latitude");
        	 longitude = coordsObj.getDouble("longitude");
        	 System.out.println(latitude + " " + longitude);
        	 }
        	 else
        		 System.out.println("No results found");
         }  catch (Exception e) {
             e.printStackTrace();
             temp.setText("Error fetching temperature.");
             speed.setText("Error fetching wind speed.");
             Precipitation.setText("Error fetching precipitation chance.");
         }
	 }
    // Fetch the weather data
    String weatherData = fetchWeatherData(latitude, longitude);

    // Parse JSON and update the GUI
    if (!weatherData.startsWith("Error")) {
        try {
            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(weatherData);

            // Extract current weather data
            JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");
            double currentTemp = currentWeather.getDouble("temperature");
            double windSpeed = currentWeather.getDouble("windspeed");

            temp.setText("Temperature: " + currentTemp + "°C");
            speed.setText(/*"Wind Speed: " +*/ windSpeed + " km/h");

            // Extract hourly temperature data
            JSONObject hourly = jsonResponse.getJSONObject("hourly");
            var temperatures = hourly.getJSONArray("temperature_2m");

            // Array of hourly labels
            Label[] hourlyLabels = {
                label1am, label2am, label3am, label4am, label5am, label6am, label7am,
                label8am, label9am, label10am, label11am, label12am,
                label1pm, label2pm, label3pm, label4pm, label5pm, label6pm,
                label7pm, label8pm, label9pm, label10pm, label11pm, label12pm
            };

            // Fill in labels with temperatures
            for (int i = 0; i < hourlyLabels.length; i++) {
                String temperature = temperatures.getDouble(i) + "°C";
                hourlyLabels[i].setText(temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
            temp.setText("Error fetching temperature.");
            speed.setText("Error fetching wind speed.");
            Precipitation.setText("Error fetching precipitation chance.");
        }
    } else {
        temp.setText("Unable to fetch temperature.");
        speed.setText("Unable to fetch wind speed.");
        Precipitation.setText("Unable to fetch precipitation chance.");
    }

    // Set static labels
    currently.setText("Currently");
    type.setText("Weather in " + location + " is:");
    chance.setText("Chance of Rain:");  // Update with actual data if available
    Date.setText("Date: " + java.time.LocalDate.now().toString());  // Display current date
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
    
    public static String fetchWeatherData(double latitude, double longitude) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                + "&longitude=" + longitude + "&current_weather=true&hourly=temperature_2m,precipitation_probability";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // Return the raw JSON response
            } else {
                return "Error: Unable to fetch weather data (HTTP " + response.statusCode() + ").";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Exception occurred while fetching weather data.";
        }
    }
    
    
 public String coordCall(String location) {
    	
    	String apiURL = "https://geocoding-api.open-meteo.com/v1/search?name="+location+"&count=10&language=en&format=json";
    	  try {
              HttpClient client = HttpClient.newHttpClient();

              HttpRequest request = HttpRequest.newBuilder()
                      .uri(URI.create(apiURL))
                      .GET()
                      .build();

              HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

              if (response.statusCode() == 200) {
                  return response.body(); // Return the raw JSON response
              } else {
                  return "Error: Unable to fetch coordinates (HTTP " + response.statusCode() + ").";
              }
          } catch (Exception e) {
              e.printStackTrace();
              return "Error: Exception occurred while fetching coordinates.";
          }
      }
    	
   
    
    
}
    
    
    
    

