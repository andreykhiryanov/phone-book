package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.objects.Person;
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

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void addButtonAction(ActionEvent actionEvent) {
        // Creating a new person.
        person = new Person(txtName.getText(), txtSurname.getText(), txtPhoneNumber.getText(), txtAddress.getText(), txtAge.getText(), txtNote.getText());
        System.out.println("Inner Add button");
        cancelButtonAction(actionEvent);
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        // Closing the window.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}