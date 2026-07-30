package edu.utsa.cs3443.byteclub.controller.store;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Item;
import edu.utsa.cs3443.byteclub.view.ProductCard;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CheckoutScreenController {

    @FXML
    public Label thankYouLabel;

    @FXML
    public Button buyButton;

    @FXML
    public Label moneyLabel;

    @FXML
    private Button back_button;

    @FXML
    private VBox checkoutVBox;

    @FXML
    private void initialize() {
        double total = 0.00;
        // populate HBox with products
        for (Item e : ByteClubApplication.getCurrentUser().getShoppingCart()) {
            total += e.getPrice();
            checkoutVBox.getChildren().add(new ProductCard(e, true));
        }
        moneyLabel.setText(String.format("$%.2f", total));
    }

    @FXML
    private void onBackButtonClick() {
        // Handle back button click
        SceneManager.switchScene("store/Storefront.fxml");
    }

    public void onPurchaseClick() {
        // remove all items in shopping cart
        ByteClubApplication.getCurrentUser().getShoppingCart().clear();
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
        // clear vbox children
        checkoutVBox.getChildren().clear();
        moneyLabel.setText("$0.00");
    }

    public void onUpdateClick() {
        double total = 0.00;
        for (Item e : ByteClubApplication.getCurrentUser().getShoppingCart()) {
            total += e.getPrice();
        }
        moneyLabel.setText(String.format("$%.2f", total));
    }
}
