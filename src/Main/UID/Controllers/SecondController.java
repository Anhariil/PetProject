package Main.UID.Controllers;

import Main.Configs.DatabaseHandler;
import Main.Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import Main.DateTime;
import Main.UID.AssetTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

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
    private TabPane TabPane1;

    @FXML
    private ChoiceBox<String> choiceBox1;

    /**
     * Chart wth info about share
     */
    @FXML
    private LineChart<Number, Number> LineChart1; //TODO fix type ?

    @FXML
    void initialize() throws IOException, InterruptedException {

//        XYChart.Series<Number, Number> series = new XYChart.Series<>(); // create series to save info about now share
//        series.setName("Work bitch");

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

        choiceBox1.getItems().addAll(dbHandler.getNamesByCountryOfRisk("RU")); //test value

        choiceBox1.setValue("Выберите акцию для просмотра");

        Button1.setOnAction(actionEvent -> {
            try {
                changeScene("/Main/UID/UI/Welcome.fxml", actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        choiceBox1.setOnAction(actionEvent -> { // TODO move to stand-alone func

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            choiceBox1.setValue(choiceBox1.getValue());
            String name = choiceBox1.getValue();
            String figi = dbHandler.getFigiByName(name);
            LocalDateTime dateTime = LocalDateTime.now();
            DateTime to = new DateTime(dateTime);
            DateTime from = new DateTime(dateTime.minusMonths(1));

            GetCandles candles = new GetCandles("test", "POST", figi, from.toString(), to.toString(), 24); //test value
            try {
                candles.getConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // if result returns set it into series
            SetCandleIntoSeries(candles.openResponse().getCandles(), series);

            // create lineChart wth our data
            LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
            lineChart.getData().add(series);

            //create and fill tab
            CreateNewTab(name, lineChart);

        });

    }

    private void CreateNewTab(String name, LineChart lineChart) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/UID/UI/Asset.fxml")); // create new Loader
        Parent node = null;  // node wth info for new tab
        try {
            node = loader.load();
            // if we can create node fill it
            AssetController controller = loader.getController(); // take pointer to controller
            controller.initializeByParams(name, lineChart); // call func and set our params
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean tabExist = false;
        for (Tab nowTab : TabPane1.getTabs()) { // loop by results
            if (nowTab.getText().equalsIgnoreCase(name)) {
                TabPane1.getTabs().get(TabPane1.getTabs().indexOf(nowTab)).setContent(node); //replace old content to new
                tabExist = true;
                TabPane1.getSelectionModel().select(nowTab);
            }
        }

        if (!tabExist) {
            Tab tab = new AssetTab(name, node); //create new tab
            TabPane1.getTabs().add(tab);
            TabPane1.getSelectionModel().select(tab); //open new tab
        }
    }

}
