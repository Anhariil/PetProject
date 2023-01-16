package Main.UID.Controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
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
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        lineChartAsset.getData().add(series);

        choiceBoxInterval.getItems().addAll("1 minute", "5 minutes", "15 minutes", "1 hour", "1 day");
        choiceBoxInterval.setValue("1 day");

        dataPickerTo.setValue(LocalDate.now());
        dataPickerFrom.setValue(LocalDate.now().minusMonths(1));

        test.setOnAction(actionEvent -> {
            SetCandleIntoSeries(series);
        });

    }

    void initializeByParams() { //set value from parent
    }

    void initializeByParams(String name, LineChart lineChart) { //set value from parent
        labelName.setText(name);
//        LineChartAsset = new LineChart<>();
        lineChartAsset = lineChart;
        //anchorPaneLineChart.getChildren().clear();
//        anchorPaneLineChart.getChildren().add(lineChart);
//        anchorPane1.getChildren().clear();
//        anchorPane1.getChildren().add(this.lineChartAsset);
    }

}
