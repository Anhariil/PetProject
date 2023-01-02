package Main.UID.Controllers;

import Main.Connectors.ConnectorsThread;
import Main.Connectors.Data.Tinkoff.InsrumentService.Instrument;
import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.Candle;
import Main.Connectors.Services.Tinkoff.InstrumentService.Shares;
import Main.Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import Main.DateTime;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
    private LineChart<Number, Number> LineChart1; //TODO fix type ?

    @FXML
    void initialize() throws IOException {

        share = new Shares(); //TODO do it like a global variable (in main class?) or take it from DB
        ConnectorsThread thread = new ConnectorsThread(share);
        thread.start();
        while (thread.isAlive()) {
            try {
                Thread.sleep(2 * 1000); // wait 2 seconds in Main
            } catch (InterruptedException e) {
            }
            System.out.println("share connection still going");
        }
        choiceBox1.getItems().addAll(share.openResponse().getAllNamesByCountryOfRisk("RU"));
        choiceBox1.setValue("Выберите акцию для просмотра");

        Button1.setOnAction(actionEvent -> {
            try {
                openNewScene("/Main/UID/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> {
            choiceBox1.setValue(choiceBox1.getValue());
            Instrument instrument = share.openResponse().getInstrumentByName(choiceBox1.getValue()); // take instrument by name // TODO SQL request ?
            LocalDateTime dateTime = LocalDateTime.now();
            DateTime to = new DateTime(dateTime);
            DateTime from = new DateTime(dateTime.minusMonths(1));
            GetCandles candles = new GetCandles("test", "POST", instrument.getFigi(), from.toString(), to.toString(), 24);
            try {
                candles.getConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // if result returns
            if (candles.openResponse().getCandles() != null) {
                Candle[] candlesDate = candles.openResponse().getCandles(); // copy results

                XYChart.Series series = new XYChart.Series(); // create series to save info about now share
                int j = 0;
                for (Candle i : candlesDate) { // loop by results
                    series.getData().add(new XYChart.Data(j++, i.getClose().getUnits()));
                }
                LineChart1.getData().removeAll(LineChart1.getData()); // clear chart ?
                LineChart1.getData().add(series);
                //Label1.setText(getCountries.openResponse().getAlfaThree(choiceBox1.getValue()));
            }
        });
    }
}
