package edu.utsa.cs3443.byteclub.controller.store;

import edu.utsa.cs3443.byteclub.model.Item;
import edu.utsa.cs3443.byteclub.view.EventCard;
import edu.utsa.cs3443.byteclub.view.ProductCard;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StorefrontScreenController {
    @FXML
    private HBox productHBox1;
    @FXML
    private Button checkoutButton;

    @FXML
    private void onCheckoutClick() {
        SceneManager.switchScene("store/Checkout.fxml");
    }

    @FXML
    private void initialize() {
        // Initialize the controller
        // read items.csv
        try (Scanner scnr = new Scanner(new File("data/appData/items.csv"))) {
            scnr.nextLine();
            while (scnr.hasNextLine()) {
                // populate hbox
                Scanner line = new Scanner(scnr.nextLine());
                line.useDelimiter(",");
                productHBox1.getChildren().add(new ProductCard(line.nextInt()));
            }
        } catch (IOException e) {

        }
    }


    @FXML
    private void onBackButtonClick() {
        // Handle back button click
        SceneManager.switchScene("HomeScreen.fxml");
    }
}
