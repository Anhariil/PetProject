package Connectors.Services.Tinkoff.MarketDataService;

import Connectors.Services.Tinkoff.Tinkoff;

public class MarketDataService extends Tinkoff {
    private static final String name = "MarketDataService/";

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += name;
    }
}
