package com.generation.brain.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmController {

    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;

    private boolean confirm = false;

    public boolean getConfirm() {
        return confirm;
    }

    public void yesButtonAction(ActionEvent actionEvent) {
        confirm = true;
        noButtonAction(actionEvent);
    }

    public void noButtonAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}