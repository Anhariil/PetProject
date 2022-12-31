package UID.Controllers;

import Connectors.Services.Tinkoff.InstrumentService.Shares;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    /**
     * Chart wth info about share
     */
    @FXML
    private LineChart<String, String> LineChart1; //TODO fix type ?

    @FXML
    void initialize() throws IOException {
        choiceBox1.getItems().addAll(new Shares().openResponse().getAllNames());

        Button1.setOnAction(actionEvent -> {
            try {
                openNewScene("/UID/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> {
            //Label1.setText(getCountries.openResponse().getAlfaThree(choiceBox1.getValue()));
        });
    }
}
