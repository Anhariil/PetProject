package Controllers;
//import UI.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WelcomeController {

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
            if(Label1.getText().equals("Я родился")) {
                Label1.setText("Я умер(");
            }
            else {
                Label1.setText("Я родился)");
            }
            Label1.setVisible(true);
        });
    }

}
