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
public void getlistofthings(String string){
store=string;
       //enter api data
       currently.setText("Currently");
       type.setText("Weather in "+string+" is");
        speed.setText("ccc");
temp.setText("cccc");
chance.setText("percent");
Time.setText("Time:"+"Date");
loc.setText(string);
Precaction.setText("presipcation");
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
}
