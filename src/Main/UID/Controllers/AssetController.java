package Main.UID.Controllers;

import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.Candle;
import Main.Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import Main.DateTime;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssetController extends Controllers {

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private AnchorPane anchorPaneLineChart;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button test;

    @FXML
    private DatePicker dataPickerTo;

    @FXML
    private DatePicker dataPickerFrom;

    @FXML
    private Label labelDateFrom;

    @FXML
    private Label labelDateTo;

    @FXML
    private Label labelInterval;

    @FXML
    private Label labelName;

    @FXML
    /**
     * series 0 have value about candle
     * series 1 and 2 have value ...
     */
    private LineChart<String, Number> lineChartAsset;

    @FXML
    private VBox VBox11;

    @FXML
    private ChoiceBox<String> choiceBoxInterval;

    private String figi;

    @FXML
    void initialize() { //default

        // TODO fix this trash and understand, why its didn't work correct without recreation
        anchorPane1.getChildren().remove(lineChartAsset); // remove to correct work
        lineChartAsset = new LineChart<>(new CategoryAxis(), new NumberAxis()); // to recreation
        AnchorPane.setTopAnchor(lineChartAsset, 10.0);
        AnchorPane.setBottomAnchor(lineChartAsset, 148.0);
        AnchorPane.setLeftAnchor(lineChartAsset, 324.0);
        AnchorPane.setRightAnchor(lineChartAsset, 10.0);

        choiceBoxInterval.getItems().addAll("1 minute", "5 minutes", "15 minutes", "1 hour", "1 day");
        choiceBoxInterval.setValue("1 day");

        dataPickerTo.setValue(LocalDate.now());
        dataPickerFrom.setValue(LocalDate.now().minusMonths(1));

        dataPickerFrom.setOnAction(actionEvent -> {
        }); // check date is correct
        dataPickerTo.setOnAction(actionEvent -> {
        }); // check date is correct

        test.setOnAction(actionEvent -> {
            if (lineChartAsset.getData().isEmpty())
                lineChartAsset.getData().add(new XYChart.Series<String, Number>());

            DateTime from = new DateTime(this.dataPickerFrom.getValue());
            DateTime to = new DateTime(this.dataPickerTo.getValue());

            // TODO create checks on setOnAction to dataPickers
            if (from.isAfter(to)) {
                DateTime t = new DateTime(from);
                from = to;
                to = t;
            }

            int interval = 0;
            switch (choiceBoxInterval.getValue()) {
                case "1 minute":
                    interval = 1;
                    break;
                case "5 minutes":
                    interval = 5;
                    break;
                case "15 minutes":
                    interval = 15;
                    break;
                case "1 hour":
                    interval = 60;
                    break;
                case "1 day":
                    interval = 24;
                    break;
            }

            int repeat = 1; // how many times we should call GetCandles
            int delta = 1; // days different between call
            if ((interval == 1 || interval == 5 || interval == 15) & from.differenceInDays(to) > 1) {
                repeat = from.differenceInDays(to); // do repeat for each day
            } else if (interval == 60 & from.differenceInDays(to) > 7) {
                repeat = from.differenceInDays(to) / 7 + 1; // do repeat for each week
                delta = 7;
            } else {
                if (from.differenceInDays(to) > 365) {
                    repeat = from.differenceInDays(to) / 365 + 1; // do repeat for each year
                    delta = 365;
                }
            }

            List<Candle> candlesList = new ArrayList<>(); // create list to collect and join results

            if (repeat > 1) {
                to = new DateTime(from); // set to take intervals into loop
                to.plusDays(delta);
            }

            for (int i = 0; i < repeat; i++) {

                GetCandles candles = new GetCandles("test", "POST", figi, from.toString(), to.toString(), interval); // value from UI
                try {
                    candles.getConnection();
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                    System.out.println("ERR in get Connection" + e);
                }

                for (Candle c : candles.openResponse().getCandles()) // add all in list TODO how set wth addAll ?
                    candlesList.add(c);

                // set param to new interval
                from.plusDays(delta);
                to.plusDays(delta);
            }

            Candle[] candles = new Candle[candlesList.size()];
            candlesList.toArray(candles);

            // if result returns set it into series
            SetCandleIntoSeries(candles, lineChartAsset.getData().get(0)); // get correct series and fill

//            SetCandleIntoSeries(lineChartAsset.getData().get(0));
        });

    }

    void initializeByParams() { //set value from parent
    }

    void initializeByParams(String name, String figi, LineChart<String, Number> lineChart) { //set value from parent
        this.figi = figi;
        labelName.setText(name);
        lineChartAsset.setTitle(name);
        lineChartAsset.getData().add(lineChart.getData().get(0));
        anchorPane1.getChildren().add(lineChartAsset);
    }

}
