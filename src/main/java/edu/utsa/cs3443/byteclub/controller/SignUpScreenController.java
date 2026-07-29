package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.model.Database;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpScreenController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button createAccountButton;

    @FXML
    private void onSignupClick() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();
        String first = firstNameField.getText();
        String last = lastNameField.getText();

        if (email.isBlank() || password.isBlank() || confirm.isBlank() || first.isBlank() || last.isBlank()) {
            createAccountButton.setText("A field is empty!");
            return;
        }

        if (!password.trim().equals(confirm.trim())) {
            // Ask user to retry
            createAccountButton.setText("Passwords don't match!");
            return;
        }
        // ask database to register with these details
        Database.registerUser(email, password, first, last);

        //return to login screen.
        SceneManager.switchScene("Login.fxml");
    }
}