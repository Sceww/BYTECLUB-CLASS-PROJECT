package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Person.User;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ProfileScreenController {

    private static final String[] SUPPORTED_PICTURE_EXTENSIONS = {"jpg", "jpeg", "png"};

    @FXML
    private ImageView user_profile_picture;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea bioTextArea;

    @FXML
    private TextField interestsField;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
        User currentUser = ByteClubApplication.getCurrentUser();
        nameLabel.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        bioTextArea.setText(currentUser.getBiography());
        interestsField.setText(String.join(", ", currentUser.getInterestList()));
        loadProfilePicture();
    }

    @FXML
    private void onBackClick() {
        SceneManager.switchScene("HomeScreen.fxml");
    }

    @FXML
    private void onSaveClick() {
        User currentUser = ByteClubApplication.getCurrentUser();
        currentUser.setBiography(bioTextArea.getText());
        currentUser.setInterests(interestsField.getText());
        currentUser.saveDatatoDisk();
        saveButton.setText("Saved!");
    }

    @FXML
    private void onChoosePictureClick() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Profile Picture");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

        File selected = chooser.showOpenDialog(user_profile_picture.getScene().getWindow());
        if (selected == null) {
            return;
        }

        String name = selected.getName();
        String extension = name.substring(name.lastIndexOf('.') + 1).toLowerCase();

        User currentUser = ByteClubApplication.getCurrentUser();
        File userDataDir = new File(String.format("data/userData/%d/", currentUser.getId()));
        if (!userDataDir.exists()) {
            userDataDir.mkdirs();
        }

        // clear out any previous profile picture so stale files with a different
        // extension don't get picked up ahead of the new one
        for (String ext : SUPPORTED_PICTURE_EXTENSIONS) {
            File old = new File(userDataDir, "pfp." + ext);
            if (old.exists()) {
                old.delete();
            }
        }

        File dest = new File(userDataDir, "pfp." + extension);
        try {
            Files.copy(selected.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            loadProfilePicture();
        } catch (IOException e) {
            System.out.println("Failed to save profile picture: " + e.getMessage());
        }
    }

    private void loadProfilePicture() {
        User currentUser = ByteClubApplication.getCurrentUser();
        File picture = null;
        for (String ext : SUPPORTED_PICTURE_EXTENSIONS) {
            File candidate = new File(String.format("data/userData/%d/pfp.%s", currentUser.getId(), ext));
            if (candidate.exists()) {
                picture = candidate;
                break;
            }
        }
        if (picture == null) {
            picture = new File("data/userData/default/pfp.png");
        }
        user_profile_picture.setImage(new Image(picture.toURI().toString()));
    }
}