package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.interfaces.impls.CollectionAddressBook;
import com.generation.brain.phonebook.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    // The instance of our address book.
    private CollectionAddressBook addressBook = new CollectionAddressBook();

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
    private TableColumn<Person, String> columnName;
    @FXML
    private TableColumn<Person, String> columnSurname;
    @FXML
    private TableColumn<Person, String> columnPhoneNumber;
    @FXML
    private Label labelCount;


    // This method is executed once after loading the GUI.
    @FXML
    private void initialize() {

        // Automatically reading from the Person class of the getter, which corresponds to the variable name we specified,
        // writing the obtained value to the corresponding column of the table.
        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        // Adding a listener, that automatically updates the counter, if the list is updated.
        addressBook.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                updateCountLabel();
            }
        });

        // Remove after tests.
        addressBook.fillTestData();

        // Filling the table in the GUI with data from the collection.
        tableAddressBook.setItems(addressBook.getPersonList());
    }

    // Method for updating the number of contacts.
    private void updateCountLabel() {
        labelCount.setText("Total contacts: " + addressBook.getPersonList().size());
    }

    // Buttons "Edit" and "Remove".
    public void editAndRemoveButtonAction(ActionEvent actionEvent) {

        // Getting object from the actionEvent.
        Object source = actionEvent.getSource();

        // If the button was not pressed, exit the method.
        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "btnEdit":
                System.out.println("Editing " + selectedPerson.getName());
                break;
            case "btnRemove":
                System.out.println("Removing " + selectedPerson.getName());
        }
    }

    // The add button was pressed.
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

    public void searchButtonAction (ActionEvent actionEvent) {

    }

    public void infoActionButton(ActionEvent actionEvent) throws IOException {

        // Getting object from the actionEvent.
        Object source = actionEvent.getSource();

        Person selectedPerson = (Person) tableAddressBook.getSelectionModel().getSelectedItem();

        // If the button was not pressed, or if the person was not chosen, exit the method.
        if (!(source instanceof Button) | selectedPerson == null) {
            return;
        }

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/info.fxml"));
        stage.setTitle("Information about " + selectedPerson.getName());
        stage.setMinWidth(600);
        stage.setMinHeight(270);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}