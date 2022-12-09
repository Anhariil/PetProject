package Controllers;
//import UI.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WelcomeController extends Controllers{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button1;

    @FXML
    private Label Label1;

    @FXML
    /**
     * Here we write realisation all actions on this window
     */
    void initialize() {
        /**
         * Should be renamed to smth like AnalitickButton
         */

        Button1.setOnAction(actionEvent -> {
//            Stage stageNow = (Stage) this.Button1.getScene().getWindow();
//            try {
//                openNewScene("/UI/Second.fxml",stageNow);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            if(Label1.getText().equals("Я родился)")) {
                Label1.setText("Я умер(");
            }
            else {
                Label1.setText("Я родился)");
            }

        });
    }
}
