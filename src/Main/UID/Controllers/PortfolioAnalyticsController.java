package Main.UID.Controllers;

import Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap.Portfolio;
import Main.Connectors.Services.Tinkoff.SandboxService.GetSandboxPortfolio;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PortfolioAnalyticsController extends Controllers {

    Portfolio portfolio = null;
    Map<String, PieChart.Data[]> dataMap = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label BalanceLabel;

    @FXML
    private RadioButton CurrencyRadioButton;

    @FXML
    private ToggleGroup Group1;

    @FXML
    private RadioButton InstrumentTypeRadioButton;

    @FXML
    private PieChart PortfolioPieChart;

    @FXML
    private RadioButton SectorTypeRadioButton;

    @FXML
    void initialize() {

        dataMap = new HashMap<>(); // to collect data about portfolio to Pie Chart
        try {
            GetSandboxPortfolio sp = new GetSandboxPortfolio("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
            sp.getConnection();
            portfolio = sp.openResponse().getPortfolio();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (portfolio != null) {

            // set current balance
            double balance = 0;
            for (Double v : portfolio.getInstrumentsTypesAndValue().values())
                balance += v;
            BalanceLabel.setText("Current balance - " + balance + " " + portfolio.getTotalAmountCurrencies().getCurrency());

            // for sort by type
            PieChart.Data[] data = new PieChart.Data[portfolio.getInstrumentsTypesCount()];
            int i = 0;
            for (String s : portfolio.getInstrumentsTypesAndValue().keySet()) {
                double value = portfolio.getInstrumentsTypesAndValue().get(s);
                data[i] = new PieChart.Data(s + ", " + (int) (value / balance * 100) + "%", value);
                i++;
            }
            dataMap.put("instrument type", data);

            // for sort by sector
            // TODO
            data = new PieChart.Data[portfolio.getSumValueBySector().size()];
            i = 0;
            for (String s : portfolio.getSumValueBySector().keySet()) {
                double value = portfolio.getSumValueBySector().get(s);
                data[i] = new PieChart.Data(s + ", " + (int) (value / balance * 100) + "%", value);
                i++;
            }
            dataMap.put("sector", data);

            // for sort by currency
            // TODO

        }

        PortfolioPieChart.getData().addAll(dataMap.get("instrument type")); // set default value
//        PortfolioPieChart.getData().add(new PieChart.Data());

        InstrumentTypeRadioButton.setOnAction(actionEvent -> {
            setChart("instrument type");
        });
//        SectorTypeRadioButton.setOnAction(setChart("sector"));
        SectorTypeRadioButton.setOnAction(actionEvent -> {
            setChart("sector");
        });
    }


    private void setChart(String chartType) {
        PortfolioPieChart.getData().clear();
        PortfolioPieChart.getData().addAll(dataMap.get(chartType));
    }
}
