package Main.UID.Controllers;

import Main.Connectors.ConnectorsThread;
import Main.Connectors.Services.Tinkoff.InstrumentService.Shares;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondController extends Controllers {

    private Shares share;

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

        share = new Shares(); //TODO do it like a global variable (in main class?) or take it from DB
        ConnectorsThread thread = new ConnectorsThread(share);
        thread.start();
        while (thread.isAlive()) {
            try {
                Thread.sleep(1000); // wait 1 second in Main
            } catch (InterruptedException e) {
            }
            System.out.println("share connection still going");
        }
        choiceBox1.getItems().addAll(share.openResponse().getAllNamesByCountryOfRisk("RU"));
        choiceBox1.setValue("Open me!"); //TODO how set first value in list?

        Button1.setOnAction(actionEvent -> {
            try {
                openNewScene("/Main/UID/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> {
            //Label1.setText(getCountries.openResponse().getAlfaThree(choiceBox1.getValue()));
        });
    }
}
