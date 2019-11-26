package com.generation.brain.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tableAddressBook;
    @FXML
    private Label labelCount;


    public void addButtonAction (ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/add.fxml"));
        stage.setTitle("New Contact");
        stage.setMinWidth(600);
        stage.setMinHeight(270);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();

    }

    public void editButtonAction (ActionEvent actionEvent) {

    }

    public void removeButtonAction (ActionEvent actionEvent) {

    }

    public void searchButtonAction (ActionEvent actionEvent) {

    }

}
