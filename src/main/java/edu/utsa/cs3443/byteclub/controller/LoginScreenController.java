package edu.utsa.cs3443.byteclub.controller;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import edu.utsa.cs3443.byteclub.model.Database;
import edu.utsa.cs3443.byteclub.model.Person.User;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginScreenController {
    @FXML
    protected TextField emailAs;
    @FXML
    protected TextField passwordAs;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink signUpLink;

    @FXML
    private void onSignupClick() {
        SceneManager.switchScene("SignUp.fxml");
    }

    @FXML
    private void onLoginClick() {
        if (emailAs.getText().isBlank() || passwordAs.getText().isBlank()) {
            loginButton.setText("Email or Password field is empty!");
            return;
        }
        int id = Database.checkLogin(emailAs.getText(), passwordAs.getText());
        if (id != -1) {
            User u = User.queryUser(id);
            if (u != null) {
                ByteClubApplication.setCurrentUser(u);
                SceneManager.switchScene("HomeScreen.fxml");
            }
        }
    }

}