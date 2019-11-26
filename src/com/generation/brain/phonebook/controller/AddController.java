package com.generation.brain.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtNote;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;

    public void addButtonAction(ActionEvent actionEvent) {
        System.out.println("Add");
    }

    public void cancelButtonAction(ActionEvent actionEvent) {

        // Closing the window.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}