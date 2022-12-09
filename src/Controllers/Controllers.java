package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controllers {

    public void openNewScene(String sceneName, Stage stage1) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));

        //Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Stage stage = stage1;
        stage.setScene(new Scene(root));
        stage.show();
    }
}
