package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ProfileScreenController {

    @FXML
    private ImageView user_profile_picture;

    @FXML
    private TextArea bioTextArea;

    @FXML
    private void initialize() {
        bioTextArea.setText(ByteClubApplication.getCurrentUser().getBiography());
    }

    @FXML
    private Button backButton;

    @FXML
    private void onBackClick() {
        SceneManager.switchScene("HomeScreen.fxml");
    }

    @FXML
    private void onSaveClick() {
        ByteClubApplication.getCurrentUser().setBiography(bioTextArea.getText());
        ByteClubApplication.getCurrentUser().saveDatatoDisk();
    }
}