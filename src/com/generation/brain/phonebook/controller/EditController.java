package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

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
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

        txtName.setText(person.getName());
        txtSurname.setText(person.getSurname());
        txtPhoneNumber.setText(person.getPhoneNumber());
        txtAddress.setText(person.getAddress());
        txtAge.setText(person.getAge());
        txtNote.setText(person.getNote());
    }

    // This method must be executed every time the editor has been used.
    public void resetFields() {
        txtName.setText("");
        txtSurname.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        txtAge.setText("");
        txtNote.setText("");

        person = null;
    }

    public void okButtonAction(ActionEvent actionEvent) {
        // Creating a new person.
        if (person == null) person = new Person(txtName.getText(), txtSurname.getText(), txtPhoneNumber.getText(), txtAddress.getText(), txtAge.getText(), txtNote.getText());
        else {
            person.setName(txtName.getText());
            person.setSurname(txtSurname.getText());
            person.setPhoneNumber(txtPhoneNumber.getText());
            person.setAddress(txtAddress.getText());
            person.setAge(txtAge.getText());
            person.setNote(txtNote.getText());
        }
        cancelButtonAction(actionEvent);
    }

    public void cancelButtonAction(ActionEvent actionEvent) {

        // Closing the window.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

}