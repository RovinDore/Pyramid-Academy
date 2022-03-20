package com.example.humanvsgoblingui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    Text pointsAmount;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HVG.fxml"));
        scene = new Scene(fxmlLoader.load());
        this.stage = stage;
        HVGGame GameControl = fxmlLoader.getController();
        stage.setTitle("Human Vs Goblin");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                GameControl.moveEntity(keyEvent);
            }
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public Scene getScene(){
        return this.scene;
    }

    public Stage getStage(){
        return this.stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public void start(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        start(stage);
    }

    public void setPointsAmount(int points) {
        pointsAmount.setText(String.valueOf(points));
    }
}