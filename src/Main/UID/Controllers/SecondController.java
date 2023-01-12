package Main.UID.Controllers;

import Main.Configs.DatabaseHandler;
import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.Candle;
import Main.Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import Main.DateTime;
import Main.ModalWindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
    private AreaChart<Number, Number> AreaChart1;

    @FXML
    void initialize() throws IOException, InterruptedException {

        XYChart.Series<Number, Number> series = new XYChart.Series<>(); // create series to save info about now share
        series.setName("Work bitch");

//        Shares share = new Shares(); //TODO do it like a global variable (in main class?) or take it from DB
//        ConnectorsThread thread = new ConnectorsThread(share);
//        thread.start();
//        thread.join(); // wait until thread stop working


        DatabaseHandler dbHandler = new DatabaseHandler(); // create to connection

//        Instrument[] instruments = share.openResponse().getInstruments();
//        dbHandler.updateInstruments(instruments); // try to save all instruments

//        while (thread.isAlive()) {
//            try {
//                Thread.sleep(2 * 1000); // wait 2 seconds in Main
//            } catch (InterruptedException e) {
//            }
//            System.out.println("share connection still going");
//        }

        try {
            choiceBox1.getItems().addAll(dbHandler.getNamesByCountryOfRisk("RU"));
        } finally {

        }

        choiceBox1.setValue("Выберите акцию для просмотра");

        Button1.setOnAction(actionEvent -> {
            try {
                changeScene("/Main/UID/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> { // TODO move to stand-alone func

            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(); //??

            choiceBox1.setValue(choiceBox1.getValue());
            String name = choiceBox1.getValue();
            String figi = dbHandler.getFigiByName(name);
            LocalDateTime dateTime = LocalDateTime.now();
            DateTime to = new DateTime(dateTime);
            DateTime from = new DateTime(dateTime.minusMonths(1));

            GetCandles candles = new GetCandles("test", "POST", figi, from.toString(), to.toString(), 24);
            try {
                candles.getConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // if result returns
            //SetCandleIntoSeries(candles.openResponse().getCandles(), series);
            SetCandleIntoSeries(series);

        });

        // to chart works ?
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Month");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Month");
        //creating the chart
        LineChart1 = new LineChart(xAxis, yAxis);
        LineChart1.getData().add(series);

    }

    private void SetCandleIntoSeries(Candle[] candles, XYChart.Series<Number, Number> series) {
        if (candles != null) {
            Candle[] candlesDate = candles; // copy results
            series.getData().removeAll(); // clear chart

            for (Candle i : candlesDate) { // loop by results
                series.getData().add(new XYChart.Data(i.getTime().getDay(), Integer.valueOf(i.getClose().getUnits())));
            }

            Scene scene = new Scene(LineChart1, 800, 600);
            ModalWindow.openModalWindow(scene);

            //LineChart1.getData().removeAll(LineChart1.getData()); // clear chart ?
            //LineChart1.getData().add(series);
            //LineChart1.setAnimated(true);

        }
    }

    public void SetCandleIntoSeries(XYChart.Series<Number, Number> series) {
        Platform.setImplicitExit(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                int j = 0;
                int max = 100;
                int min = 0;
//                while (j < 10) {
//                    series.getData().add(new XYChart.Data(j++, (int) ((Math.random() * (max - min)) + min)));
//                }

                series.getData().add(new XYChart.Data(j++, (int) ((Math.random() * (max - min)) + min)));

                Scene scene = new Scene(LineChart1, 800, 600);
                ModalWindow.openModalWindow(scene);
            }
        });
    }

}
