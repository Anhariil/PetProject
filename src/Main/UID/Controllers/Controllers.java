package Main.UID.Controllers;

import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.Candle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class Controllers {

    /**
     * Open new scene on active window
     *
     * @param sceneName
     * @param event
     * @throws IOException
     */
    public void changeScene(String sceneName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene old = ((Node) event.getSource()).getScene();
        stage.setScene(new Scene(root, old.getWidth(), old.getHeight()));
        stage.show();
    }

    public void openNewScene(Scene sceneName) throws IOException {
        Stage stage1 = new Stage();
        stage1.setScene(sceneName);
        stage1.show();
    }

    public void SetCandleIntoSeries(Candle[] candles, XYChart.Series<String, Number> series) {

        if (candles != null) {
            Candle[] candlesDate = candles; // copy results
            series.getData().removeAll(series.getData()); // clear chart

            for (Candle i : candlesDate) { // loop by results
                series.getData().add(new XYChart.Data(i.getTime().toString(), Integer.valueOf(i.getClose().getUnits())));
            }

        }
    }

    public void SetCandleIntoSeries(XYChart.Series<Number, Number> series) {
        series.getData().removeAll(series.getData());
        int j = 0;
        int max = 100;
        int min = 0;
        while (j < 10) {
            series.getData().add(new XYChart.Data(j++, (int) ((Math.random() * (max - min)) + min)));
        }
    }


}
