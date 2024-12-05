package com.example.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class Second {
private Stage stage;
private Scene scene;
private Parent parent;

@FXML
Label temp1;
@FXML
Label temp2;
@FXML
Label temp3;
@FXML
Label temp4;
@FXML
Label temp5;
@FXML
Label temp6;
@FXML
Label temp7;
@FXML
Label prec1;
@FXML
Label prec2;
@FXML
Label prec3;
@FXML
Label prec4;
@FXML
Label prec5;
@FXML
Label prec6;
@FXML
Label prec7;

String string;





    public void switchtoscene2withoutbutton(ActionEvent event) throws IOException {
    FXMLLoader root= new FXMLLoader(getClass().getResource("second.fxml"));
    stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        parent = root.load();
    scene=new Scene(parent);
    String be=getClass().getResource("Background.css").toExternalForm();
        Third third=root.getController();
        third.getlistofthings(string);
    scene.getStylesheets().add(be);
    stage.setScene(scene);
    stage.show();
}



    public void getlistofthings(String store) {
    	this.string=store;
    	store = store;
    	
    	   // Coordinates for Detroit (replace as needed)
        double latitude = 42.3314;
        double longitude = -83.0458;
        

        String WeeklyWeatherData = fetchWeeklyWeatherData(latitude, longitude);
        if (!WeeklyWeatherData.startsWith("Error")) {
            try {
        JSONObject jsonResponse = new JSONObject(WeeklyWeatherData);
        JSONObject daily = jsonResponse.getJSONObject("daily");
        var temperatures = daily.getJSONArray("temperature_2m_max");
        Label[] weeklyTemps = {temp1, temp2, temp3, temp4, temp5, temp6, temp7};
        
        for(int i = 0; i < weeklyTemps.length; i++) {
        	 String temperature = temperatures.getDouble(i) + "°C";
        	weeklyTemps[i].setText(temperature);
        }
        
        var precip = daily.getJSONArray("precipitation_probability_max");
        Label[] weeklyPrecip = { prec1,prec2,prec3,prec4,prec5,prec6,prec7};
        
        for(int j = 0; j < weeklyPrecip.length; j++) {
        	String precipChance = precip.getDouble(j) + "%";
        	weeklyPrecip[j].setText(precipChance);
        }
        
            }catch (Exception e) {
                e.printStackTrace();
                temp1.setText("Error");
                temp2.setText("Error");
                temp3.setText("Error");
                temp4.setText("Error");
                temp5.setText("Error");
                temp6.setText("Error");
                temp7.setText("Error");
                 prec1.setText("Error");
                prec2.setText("Error");
                prec3.setText("Error");
                prec4.setText("Error");
                prec5.setText("Error");
                prec6.setText("Error");
                prec7.setText("Error");
            }
        }
    }
    
    
    //weather data fetch for weekly forecast
    public static String fetchWeeklyWeatherData(double latitude, double longitude) {
    	String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&daily=temperature_2m_max,precipitation_probability_max";
    	
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
}


