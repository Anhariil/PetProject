package Connectors.Tinkoff.InstrumentService;

import Connectors.Tinkoff.Tinkoff;

public class InstrumentsService extends Tinkoff {
    private static final String name = "InstrumentsService/";

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += name;
    }

}
