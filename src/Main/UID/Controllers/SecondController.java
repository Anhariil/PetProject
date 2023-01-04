package Main.UID.Controllers;

import Main.Configs.DatabaseHandler;
import Main.Connectors.ConnectorsThread;
import Main.Connectors.Data.Tinkoff.InsrumentService.Instrument;
import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.Candle;
import Main.Connectors.Services.Tinkoff.InstrumentService.Shares;
import Main.Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import Main.DateTime;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class SecondController extends Controllers {

    //private Shares share;

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
    void initialize() throws IOException, InterruptedException {

        Shares share = new Shares(); //TODO do it like a global variable (in main class?) or take it from DB
        ConnectorsThread thread = new ConnectorsThread(share);
        thread.start();
        thread.join(); // wait until thread stop working

//
//        DatabaseHandler dbHandler = new DatabaseHandler(); // create to connection
//        try {
//            Instrument[] instruments = share.openResponse().getInstruments();
//            for (Instrument i : instruments)
//                dbHandler.saveShares(i); // try to save all instruments
//        } catch (ClassNotFoundException e) {
//            System.out.println("Smth go wrong");
//        }

//        while (thread.isAlive()) {
//            try {
//                Thread.sleep(2 * 1000); // wait 2 seconds in Main
//            } catch (InterruptedException e) {
//            }
//            System.out.println("share connection still going");
//        }

        DatabaseHandler dbHandler = new DatabaseHandler(); // create to connection

        try {
            choiceBox1.getItems().addAll(dbHandler.getNamesByCountryOfRisk("RU"));
        } finally {

        }

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
            String figi = ""; // TODO fix it
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

                XYChart.Series<Number, Number> series = new XYChart.Series<>(); // create series to save info about now share
                series.setName("Work bitch");
                int j = 0;
                int max = 100;
                int min = 0;
//                for (Candle i : candlesDate) { // loop by results
//                    //series.getData().add(new XYChart.Data(i.getTime().getDay(), i.getClose().getUnits()));
//                    series.getData().add(new XYChart.Data(j++, (int) ((Math.random() * (max - min)) + min)));
//                }
                series.getData().add(new XYChart.Data<>(1, 20));
                series.getData().add(new XYChart.Data<>(2, 10));
                series.getData().add(new XYChart.Data<>(3, 30));
                series.getData().add(new XYChart.Data<>(4, 25));
                //LineChart1.getData().removeAll(LineChart1.getData()); // clear chart ?
                LineChart1.getData().add(series);
            }
        });
        // to chart works ?
        final NumberAxis xAxis = new NumberAxis(0, 5, 1);
        xAxis.setLabel("Month");
        final NumberAxis yAxis = new NumberAxis(0, 40, 5);
        yAxis.setLabel("Number of Month");
        //creating the chart
        LineChart1 = new LineChart(xAxis, yAxis);
    }
}
