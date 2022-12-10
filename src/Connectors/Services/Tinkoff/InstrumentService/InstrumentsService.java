package Connectors.Services.Tinkoff.InstrumentService;

import Connectors.Services.Tinkoff.Tinkoff;

import java.io.IOException;

public class InstrumentsService extends Tinkoff {
    private static final String name = "InstrumentsService/";

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += name;
    }

}
