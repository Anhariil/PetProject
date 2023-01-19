package Main.Connectors.Data.Tinkoff;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.DateTime;

public class PortfolioPositions {

    protected String figi;
    protected String instrumentType;
    protected UnitsNano quantity;
    protected CurrencyUnitsNano averagePositionPrice;
    protected UnitsNano expectedYield;
    protected CurrencyUnitsNano currentNkd;
    protected UnitsNano averagePositionPricePt;
    protected CurrencyUnitsNano currentPrice;
    protected CurrencyUnitsNano averagePositionPriceFifo;
    protected UnitsNano quantityLots;
    protected boolean blocked;
    protected String positionUid;
    protected String instrumentUid;
    protected CurrencyUnitsNano varMargin;
    protected UnitsNano expectedYieldFifo;
    protected DateTime expireDate;


}
