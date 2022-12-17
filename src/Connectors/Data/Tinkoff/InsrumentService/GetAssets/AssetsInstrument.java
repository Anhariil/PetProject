package Connectors.Data.Tinkoff.InsrumentService.GetAssets;

import Connectors.Data.Tinkoff.InsrumentService.Instrument;

public class AssetsInstrument extends Instrument {

    private String instrumentType;
    private Link[] links;

    public AssetsInstrument(String figi, String ticker, String classCode, String uid, String instrumentType, Link[] links) {
        super(figi, ticker, classCode, uid);
        this.instrumentType = instrumentType;
        this.links = links;
    }
}
