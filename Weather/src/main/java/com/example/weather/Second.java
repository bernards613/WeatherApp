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
    }
}


