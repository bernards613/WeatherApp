package com.example.weather;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    TextField field;
    static String location;

    public void switchtosecence2(ActionEvent event) throws IOException {
        FXMLLoader load= new FXMLLoader(getClass().getResource("second.fxml"));
        location=field.getText();
        parent = load.load();
        Third third=load.getController();
      
       String fixedLocation = location.replaceAll(" ","+");
        third.getlistofthings(fixedLocation);

        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(parent);
        String be=getClass().getResource("Background.css").toExternalForm();
        scene.getStylesheets().add(be);
        stage.setScene(scene);
        stage.show();

    }
    
    public static String getLocation() {
    	return location;
    }
   
}


