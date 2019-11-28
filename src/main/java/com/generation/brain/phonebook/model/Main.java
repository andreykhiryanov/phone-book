package com.generation.brain.phonebook.model;

import com.generation.brain.phonebook.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Phone Book");
        primaryStage.setMinHeight(435);
        primaryStage.setMinWidth(335);
        primaryStage.setScene(new Scene(fxmlMain, 320, 420));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
