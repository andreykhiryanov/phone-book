package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.objects.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InfoController {

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

    public void setPerson(Person person) {

        txtName.setText(person.getName());
        txtSurname.setText(person.getSurname());
        txtPhoneNumber.setText(person.getPhoneNumber());
        txtAddress.setText(person.getAddress());
        txtAge.setText(person.getAge());
        txtNote.setText(person.getNote());

        txtName.setEditable(false);
        txtSurname.setEditable(false);
        txtPhoneNumber.setEditable(false);
        txtAddress.setEditable(false);
        txtAge.setEditable(false);
        txtNote.setEditable(false);
    }

}