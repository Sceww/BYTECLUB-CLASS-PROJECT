package edu.utsa.cs3443.byteclub.view;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProductCard extends VBox {

    private boolean userView;
    private Item item;
    private ImageView imageV;
    private Label name;
    private Label price;
    private Button purchase;

    private void onPurchaseClick() {
        ByteClubApplication.getCurrentUser().addToCart(item);
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
    }

    private void onRemoveClick() {
        ByteClubApplication.getCurrentUser().getShoppingCart().remove(item);
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
        ((VBox) this.getParent()).getChildren().remove(this);
    }

    public ProductCard(Item i, boolean u) {
        // config outer vbox container
        this.userView = u;
        this.item = i;

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(25);
        this.setPadding(new Insets(15,15,35,15));
        this.setPrefWidth(200);
        this.setPrefHeight(200);

        imageV = new ImageView();
        try {
            imageV.setImage(new Image(item.getImageURL(), true));
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        imageV.setFitWidth(100);
        imageV.setFitHeight(100);
        imageV.setPreserveRatio(false);

        name = new Label(item.getName());
        price = new Label(String.format("$%.2f",item.getPrice()));
        purchase = new Button(userView ? "Remove" : "Purchase");
        purchase.setOnAction(userView ? event -> onRemoveClick() : event -> onPurchaseClick() );

        this.getChildren().addAll(imageV, name, price, purchase);
    }

    public ProductCard(int id) {
        this(Item.queryItem(id), false);
    }
}
