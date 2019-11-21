package com.generation.brain.phonebook.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/com/generation/brain/phonebook/view/main.fxml"));
        primaryStage.setTitle("Phone Book");
        primaryStage.setMinHeight(420);
        primaryStage.setMinWidth(320);
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
