package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.view.EventCard;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class HomeScreenController {

    @FXML
    private Button storeButton;
    @FXML
    private Button viewClassesButton;
    @FXML
    private VBox eventVBox;

    @FXML
    private void initialize() {
        // Initialize the controller
        // populate scrollplane with events
        // open up events.csv
        try (Scanner scnr = new Scanner(new File("data/appData/events.csv"))) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                eventVBox.getChildren().add(new EventCard(line.nextInt()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onStoreButtonClick() {
        SceneManager.switchScene("store/Storefront.fxml");
    }

    @FXML
    private void onViewClassesClick() {
        SceneManager.switchScene("Events.fxml");
    }

    @FXML
    private void onLogoutClick() {
        ByteClubApplication.setCurrentUser(null);
        SceneManager.switchScene("Login.fxml");
    }

    @FXML
    private void onProfileClick() {
        SceneManager.switchScene("Profile.fxml");
    }
}