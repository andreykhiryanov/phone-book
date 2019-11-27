package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.interfaces.impls.CollectionPhoneBook;
import com.generation.brain.phonebook.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnInfo;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tablePhoneBook;
    @FXML
    private TableColumn<Person, String> columnName;
    @FXML
    private TableColumn<Person, String> columnSurname;
    @FXML
    private TableColumn<Person, String> columnPhoneNumber;
    @FXML
    private Label labelCount;

    // The instance of our address book.
    private CollectionPhoneBook phoneBook = new CollectionPhoneBook();

    // Linking to the main stage.
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Getting access to the editor from more than one method.
    private Stage editorStage;
    private EditController editController;

    // This method is executed once after loading the GUI.
    @FXML
    private void initialize() throws IOException {

        // This will allow to select more than one person in the tablePhoneBook:
        // tablePhoneBook.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Automatically reading from the Person class of the getter, which corresponds to the variable name we specified,
        // writing the obtained value to the corresponding column of the table.
        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        // Adding a listener, that automatically updates the counter, if the list is updated.
        phoneBook.getPersonList().addListener((ListChangeListener<Person>) change -> updateCountLabel());

        // Remove after tests.
        phoneBook.fillTestData();

        // Filling the table in the GUI with data from the collection.
        tablePhoneBook.setItems(phoneBook.getPersonList());

        // Double click handler.
        tablePhoneBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        // Getting selected person from the table in the GUI.
                        Person selectedPerson;
                        if ((selectedPerson = (Person)tablePhoneBook.getSelectionModel().getSelectedItem()) != null) showInfo(selectedPerson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Initializing the editor window and its controller.
        initEditor();
    }

    private void initEditor() throws IOException {

        editorStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/edit.fxml"));
        Parent fxmlEdit = fxmlLoader.load();
        editController = fxmlLoader.getController();

        editorStage.setTitle("Contact Editor");
        editorStage.setMinWidth(600);
        editorStage.setMinHeight(270);
        editorStage.setResizable(false);
        editorStage.setScene(new Scene(fxmlEdit));
        editorStage.initModality(Modality.WINDOW_MODAL);
        editorStage.initOwner(primaryStage);
    }

    // Method for updating the number of contacts.
    private void updateCountLabel() {
        labelCount.setText("Total contacts: " + phoneBook.getPersonList().size());
    }


    //----------------------------

    // The "Add" button.
    public void addButtonAction (ActionEvent actionEvent) throws IOException {

        // Waits until the user enters data.
        editorStage.showAndWait();

        // If user clicked cancel, the method getPerson() will return null.
        if (editController.getPerson() != null) {
            phoneBook.add(editController.getPerson());
            editController.resetFields();
            tablePhoneBook.refresh();
        }

    }

    // Buttons "Info", "Edit" and "Remove".
    public void threeButtonAction(ActionEvent actionEvent) throws IOException {

        // Getting object from the actionEvent.
        Object source = actionEvent.getSource();
        // Getting chosen person.
        Person selectedPerson = (Person) tablePhoneBook.getSelectionModel().getSelectedItem();
        // Getting the clicked button.
        Button clickedButton = (Button) source;

        // If the button was not pressed, or if the person was not chosen, exit the method.
        if (source == null || selectedPerson == null) {
            return;
        }

        switch (clickedButton.getId()) {
            case "btnInfo":
                showInfo(selectedPerson);
                break;
            case "btnEdit":
                editController.setPerson(selectedPerson);
                editorStage.showAndWait();
                editController.resetFields();
                tablePhoneBook.refresh();
                break;
            case "btnRemove":
                phoneBook.delete(selectedPerson);
                tablePhoneBook.refresh();
                break;
        }
    }

    public void searchButtonAction (ActionEvent actionEvent) {

    }

    public void showInfo(Person selectedPerson) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/info.fxml"));
        Parent fxmlInfo = fxmlLoader.load();
        InfoController infoController = fxmlLoader.getController();
        infoController.setPerson(selectedPerson);

        Stage infoStage = new Stage();
        infoStage.setTitle("Information about " + selectedPerson.getName());
        infoStage.setMinWidth(500);
        infoStage.setMinHeight(270);
        infoStage.setResizable(false);
        infoStage.setScene(new Scene(fxmlInfo));
        infoStage.show();
    }
}