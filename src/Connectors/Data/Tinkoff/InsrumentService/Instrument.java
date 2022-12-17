package Connectors.Data.Tinkoff.InsrumentService;

import Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Connectors.Data.Tinkoff.InsrumentService.Shares.UnitsNano;

public class Instrument {
    protected String figi;
    protected String ticker;
    protected String classCode;
    protected String isin;
    protected int lot;
    protected String currency;
    protected UnitsNano klong;
    protected UnitsNano kshort;
    protected UnitsNano dlong;
    protected UnitsNano dshort;
    protected UnitsNano dlongMin;
    protected UnitsNano dshortMin;
    protected boolean shortEnabledFlag;
    protected String name;
    protected String exchange;
    protected String issueSize;
    protected String countryOfRisk;
    protected String countryOfRiskName;
    protected String sector;
    protected String issueSizePlan;
    protected CurrencyUnitsNano nominal;
    protected String tradingStatus;
    protected boolean otcFlag;
    protected boolean buyAvailableFlag;
    protected boolean sellAvailableFlag;
    protected boolean divYieldFlag;
    protected String shareType;
    protected UnitsNano minPriceIncrement;
    protected boolean apiTradeAvailable;
    protected String uid;
    protected String realExhange;
    protected String positionUid;
    protected boolean forIisFlag;
    protected String first1minCandleDate;
    protected String first1dayCandleDate;

    public Instrument() {
    }

    public Instrument(String figi, String ticker, String classCode, String uid) {
        this.figi = figi;
        this.ticker = ticker;
        this.classCode = classCode;
        this.uid = uid;
    }

    public Instrument(String figi, String ticker, String classCode, String isin, int lot, String currency, UnitsNano klong, UnitsNano kshort, UnitsNano dlong,
                      UnitsNano dshort, UnitsNano dlongMin, UnitsNano dshortMin, boolean shortEnabledFlag, String name, String exchange, String issueSize, String countryOfRisk,
                      String countryOfRiskName, String sector, String issueSizePlan, CurrencyUnitsNano nominal, String tradingStatus, boolean otcFlag,
                      boolean buyAvailableFlag, boolean sellAvailableFlag, boolean divYieldFlag, String shareType, UnitsNano minPriceIncrement, boolean apiTradeAvailable, String uid,
                      String realExhange, String positionUid, boolean forIisFlag, String first1minCandleDate,
                      String first1dayCandleDate) {
        this.buyAvailableFlag = buyAvailableFlag;
        this.figi = figi;
        this.ticker = ticker;
        this.classCode = classCode;
        this.isin = isin;
        this.lot = lot;
        this.currency = currency;
        this.klong = klong;
        this.kshort = kshort;
        this.dshort = dshort;
        this.dlong = dlongMin;
        this.dshortMin = dshortMin;
        this.shortEnabledFlag = shortEnabledFlag;
        this.name = name;
        this.exchange = exchange;
        this.issueSize = issueSize;
        this.countryOfRisk = countryOfRisk;
        this.countryOfRiskName = countryOfRiskName;
        this.sector = sector;
        this.issueSizePlan = issueSizePlan;
        this.nominal = nominal;
        this.tradingStatus = tradingStatus;
        this.otcFlag = otcFlag;
        this.sellAvailableFlag = sellAvailableFlag;
        this.divYieldFlag = divYieldFlag;
        this.shareType = shareType;
        this.minPriceIncrement = minPriceIncrement;
        this.apiTradeAvailable = apiTradeAvailable;
        this.uid = uid;
        this.realExhange = realExhange;
        this.positionUid = positionUid;
        this.forIisFlag = forIisFlag;
        this.first1minCandleDate = first1minCandleDate;
        this.first1dayCandleDate = first1dayCandleDate;
    }
}
