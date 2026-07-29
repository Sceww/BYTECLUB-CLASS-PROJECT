package edu.utsa.cs3443.byteclub.controller.store;

import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CheckoutScreenController {
    @FXML
    private Button back_button;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onBackButtonClick() {
        // Handle back button click
        SceneManager.switchScene("store/Storefront.fxml");
    }
}
