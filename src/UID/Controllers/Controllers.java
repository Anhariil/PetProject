package UID.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controllers {

    /**
     * Open new scene on active window
     *
     * @param sceneName
     * @param event
     * @throws IOException
     */
    public void openNewScene(String sceneName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene old = ((Node) event.getSource()).getScene();
        stage.setScene(new Scene(root, old.getWidth(), old.getHeight()));
        stage.show();
    }
}
