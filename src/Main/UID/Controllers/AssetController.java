package Main.UID.Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
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
    private LineChart<Number, Number> lineChartAsset;

    @FXML
    private VBox VBox11;

    @FXML
    private ChoiceBox<String> choiceBoxInterval;

    @FXML
    void initialize() { //default

        lineChartAsset = new LineChart<>(new NumberAxis(), new NumberAxis()); // to correct copy into this chart

        choiceBoxInterval.getItems().addAll("1 minute", "5 minutes", "15 minutes", "1 hour", "1 day");
        choiceBoxInterval.setValue("1 day");

        dataPickerTo.setValue(LocalDate.now());
        dataPickerFrom.setValue(LocalDate.now().minusMonths(1));

        test.setOnAction(actionEvent -> {
            if (lineChartAsset.getData().isEmpty()) lineChartAsset.getData().add(new XYChart.Series<Number, Number>());
            SetCandleIntoSeries(lineChartAsset.getData().get(0));
        });

    }

    void initializeByParams() { //set value from parent
    }

    void initializeByParams(String name, LineChart<Number, Number> lineChart) { //set value from parent
        labelName.setText(name);
//        LineChartAsset = new LineChart<>();
//        lineChartAsset.getData().removeAll();
        lineChartAsset.getData().add(lineChart.getData().get(0));
        anchorPaneLineChart.getChildren().remove(anchorPaneLineChart.getChildren().get(0));
        anchorPaneLineChart.getChildren().add(lineChartAsset);
//        anchorPane1.getChildren().clear();
//        anchorPane1.getChildren().add(this.lineChartAsset);
    }

}
