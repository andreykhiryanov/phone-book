package com.generation.brain.phonebook.controller;

import com.generation.brain.phonebook.objects.CollectionPhoneBook;
import com.generation.brain.phonebook.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    private Button btnClear;
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

    // Creating the phone book.
    private CollectionPhoneBook phoneBook = CollectionPhoneBook.getPhoneBook();

    // Creating the backup list of our phoneBook.
    // It is necessary for temporary storage of the list of people at the time of the search.
    // After the search, we can get a complete list of people from it.
    private ObservableList<Person> backupList = FXCollections.observableArrayList();

    // Linking to the main stage.
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Getting access to the editor from more than one method.
    private Stage editorStage;
    private EditController editorController;

    // This method is executed once after loading the GUI.
    @FXML
    private void initialize() throws IOException {

        // This will allow to select more than one person in the tablePhoneBook:
        // tablePhoneBook.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Initializing the listeners.
        initListeners();

        // Fill in the phone book.
        phoneBook.fillData();

        // Fill in the test data.
//        phoneBook.fillTestData();

        // Automatically reading from the Person class of the getter, which corresponds to the variable name we specified,
        // writing the obtained value to the corresponding column of the table.
        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));

        // Filling up the table in the GUI with data from the collection.
        tablePhoneBook.setItems(phoneBook.getPersonList());

        // Initializing the editor window and its controller.
        initEditor();
    }

    private void initListeners() {
        // Adding a listener, that automatically updates the counter, if the list is updated.
        phoneBook.getPersonList().addListener((ListChangeListener<Person>) change -> updateCountLabel());

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
    }

    private void initEditor() throws IOException {

        editorStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/edit.fxml"));
        Parent fxmlEdit = fxmlLoader.load();
        editorController = fxmlLoader.getController();

        editorStage.setTitle("Contact Editor");
        editorStage.setResizable(false);
        editorStage.setScene(new Scene(fxmlEdit));
        editorStage.initModality(Modality.APPLICATION_MODAL);
        editorStage.initOwner(primaryStage);
    }

    // Method for updating the number of contacts.
    private void updateCountLabel() {
        labelCount.setText("Total contacts: " + phoneBook.getPersonList().size());
    }

    //----------------------------

    // The "Add" button.
    public void addButtonAction (ActionEvent actionEvent) throws IOException {

        clearButtonAction(actionEvent);

        // Waits until the user enters data.
        editorStage.showAndWait();

        // If user clicked cancel, the method getPerson() will return null.
        if (editorController.getPerson() != null) {
            phoneBook.add(editorController.getPerson());
            editorController.resetFields();
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
                editorController.setPerson(selectedPerson);
                editorStage.showAndWait();
                editorController.resetFields();
                tablePhoneBook.refresh();
                break;
            case "btnRemove":
                if (confirm()) {
                    phoneBook.delete(selectedPerson);
                    backupList.remove(selectedPerson);
                    tablePhoneBook.refresh();
                }
                break;
        }
    }

    // The "Search" button.
    public void searchButtonAction (ActionEvent actionEvent) {

        String temp = txtSearch.getText();
        clearButtonAction(actionEvent);
        txtSearch.setText(temp);

        if (!txtSearch.getText().equals("") & backupList.isEmpty()) {

            // Moving all people from the main list to the backup.
            backupList.addAll(phoneBook.getPersonList());
            phoneBook.getPersonList().clear();

            for (Person person : backupList) {
                if ((person.getName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        person.getPhoneNumber().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        person.getSurname().toLowerCase().contains(txtSearch.getText().toLowerCase())) & !phoneBook.getPersonList().contains(person)) {
                    phoneBook.getPersonList().add(person);
                }
            }
        }
    }

    // The "Clear" button.
    public void clearButtonAction(ActionEvent actionEvent) {

        if (!txtSearch.getText().equals("") & !backupList.isEmpty()) {
            phoneBook.getPersonList().clear();
            phoneBook.getPersonList().addAll(backupList);
            backupList.clear();
        }

        txtSearch.clear();

    }

    // Displays information about person.
    private void showInfo(Person selectedPerson) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/info.fxml"));
        Parent fxmlInfo = fxmlLoader.load();
        InfoController infoController = fxmlLoader.getController();
        infoController.setPerson(selectedPerson);

        Stage infoStage = new Stage();
        infoStage.setTitle("Information about " + selectedPerson.getName());
        infoStage.setResizable(false);
        infoStage.setScene(new Scene(fxmlInfo));
        infoStage.show();
    }

    // Requests confirmation.
    public boolean confirm() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/confirm.fxml"));
        Parent fxmlConfirm = fxmlLoader.load();
        ConfirmController confirmController = fxmlLoader.getController();

        Stage confirmStage = new Stage();
        confirmStage.setTitle("Delete Contact");
        confirmStage.setResizable(false);
        confirmStage.setScene(new Scene(fxmlConfirm));

        confirmStage.initModality(Modality.APPLICATION_MODAL);
        confirmStage.initOwner(primaryStage);

        confirmStage.showAndWait();

        return confirmController.getConfirm();
    }
}