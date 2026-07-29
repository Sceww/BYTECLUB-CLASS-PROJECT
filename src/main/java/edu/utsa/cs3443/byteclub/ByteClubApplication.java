package edu.utsa.cs3443.byteclub;

import edu.utsa.cs3443.byteclub.model.Person.User;
import edu.utsa.cs3443.byteclub.view.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ByteClubApplication extends Application {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ByteClubApplication.currentUser = currentUser;
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ByteClubApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        SceneManager.setMainScene(scene, stage);

    }
}
