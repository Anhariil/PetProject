package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondController extends Controllers{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button1;

    @FXML
    private Label Label1;

    @FXML
    void initialize() {
        Button1.setOnAction(actionEvent -> {
            try {
                openNewScene("/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
