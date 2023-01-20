package Main.Connectors.Data.Tinkoff;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

public class PortfolioPositions {

    protected String figi;
    protected String instrumentType;
    protected UnitsNano quantity;
    protected CurrencyUnitsNano averagePositionPrice;
    protected UnitsNano averagePositionPricePt;
    protected CurrencyUnitsNano currentPrice;
    protected CurrencyUnitsNano averagePositionPriceFifo;
    protected UnitsNano quantityLots;
    protected boolean blocked;
    protected String positionUid;
    protected String instrumentUid;

//    protected CurrencyUnitsNano varMargin;
//    protected UnitsNano expectedYieldFifo;
//    protected DateTime expireDate;
//    protected UnitsNano expectedYield;
//    protected CurrencyUnitsNano currentNkd;


    public PortfolioPositions(String figi, String instrumentType, UnitsNano quantity, CurrencyUnitsNano averagePositionPrice, UnitsNano averagePositionPricePt, CurrencyUnitsNano currentPrice, CurrencyUnitsNano averagePositionPriceFifo, UnitsNano quantityLots, boolean blocked, String positionUid, String instrumentUid) {
        this.figi = figi;
        this.instrumentType = instrumentType;
        this.quantity = quantity;
        this.averagePositionPrice = averagePositionPrice;
        this.averagePositionPricePt = averagePositionPricePt;
        this.currentPrice = currentPrice;
        this.averagePositionPriceFifo = averagePositionPriceFifo;
        this.quantityLots = quantityLots;
        this.blocked = blocked;
        this.positionUid = positionUid;
        this.instrumentUid = instrumentUid;
    }
}
