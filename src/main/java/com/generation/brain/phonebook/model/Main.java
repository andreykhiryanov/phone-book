package com.generation.brain.phonebook.model;

import com.generation.brain.phonebook.controller.MainController;
import com.generation.brain.phonebook.objects.CollectionPhoneBook;
import com.generation.brain.phonebook.objects.PersonSerializer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private PersonSerializer personSerializer = PersonSerializer.getSerializer();

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

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                // This method is called in case the user
                // closes the program without clearing the search results.
                mainController.clearButtonAction(new ActionEvent());

                // Saving the persons back to the file.
                personSerializer.writeList(CollectionPhoneBook.getPhoneBook().getPersonList());

                personSerializer.closeInputStream();
                personSerializer.closeOutputStream();
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
