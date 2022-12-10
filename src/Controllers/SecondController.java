package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Connectors.Connectors;
import Connectors.Services.Tinkoff.InstrumentService.GetCountries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SecondController extends Controllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button1;

    @FXML
    private Label Label1;

    @FXML
    private ChoiceBox<String> choiceBox1;

    @FXML
    void initialize() throws IOException {
        GetCountries getCountries = new GetCountries("sb","POST");
        getCountries.getConnection();
        //System.out.println(getCountries.getResponse());
        choiceBox1.getItems().addAll(getCountries.openResponse().getAllName());

        Button1.setOnAction(actionEvent -> {
            try {
                openNewScene("/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> {
            Label1.setText(getCountries.openResponse().getAlfaThree(choiceBox1.getValue()));
        });
    }
}
