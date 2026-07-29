package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Event;
import edu.utsa.cs3443.byteclub.view.EventCard;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EventsScreenController {

    @FXML
    private Button back_button;

    @FXML
    private VBox eventVBox;

    @FXML
    private void initialize() {
        // open up current user's events
        for (Event e : ByteClubApplication.getCurrentUser().getClassList()) {
            // create EventCard
            eventVBox.getChildren().add(new EventCard(e, true));
        }
    }

    @FXML
    private void onBackButtonClick() {
        // Handle back button click
        SceneManager.switchScene("HomeScreen.fxml");
    }
}