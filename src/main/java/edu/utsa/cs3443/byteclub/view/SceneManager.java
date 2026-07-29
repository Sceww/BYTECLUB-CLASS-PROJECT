package edu.utsa.cs3443.byteclub.view;

import edu.utsa.cs3443.byteclub.ByteClubApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Scene mainScene;

    public static void setMainScene(Scene scene, Stage stage) {
        mainScene = scene;
        stage.setScene(mainScene);
        stage.show();
    }

    public static void switchScene(String fxmlPath) {
        try {
            java.net.URL resource = ByteClubApplication.class.getResource(fxmlPath);
            if (resource == null) {
                throw new IllegalArgumentException("Cannot find FXML file at path: " + fxmlPath + ". Please check your resources folder and path!");
            }
            
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}