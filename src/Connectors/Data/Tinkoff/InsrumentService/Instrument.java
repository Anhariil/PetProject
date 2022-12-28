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
    protected String realExchange;
    protected String positionUid;
    protected boolean forIisFlag;
    protected boolean forQualInvestorFlag;
    protected boolean weekendFlag;
    protected boolean blockedTcaFlag;
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

    /**
     * @param figi
     * @param ticker
     * @param classCode
     * @param isin
     * @param lot
     * @param currency
     * @param klong
     * @param kshort
     * @param dlong
     * @param dshort
     * @param dlongMin
     * @param dshortMin
     * @param shortEnabledFlag
     * @param name
     * @param exchange
     * @param issueSize
     * @param countryOfRisk
     * @param countryOfRiskName
     * @param sector
     * @param issueSizePlan
     * @param nominal
     * @param tradingStatus
     * @param otcFlag
     * @param buyAvailableFlag
     * @param sellAvailableFlag
     * @param divYieldFlag
     * @param shareType
     * @param minPriceIncrement
     * @param apiTradeAvailable
     * @param uid
     * @param realExchange
     * @param positionUid
     * @param forIisFlag
     * @param forQualInvestorFlag
     * @param weekendFlag
     * @param blockedTcaFlag
     * @param first1minCandleDate
     * @param first1dayCandleDate
     */
    public Instrument(String figi, String ticker, String classCode, String isin, int lot, String currency, UnitsNano klong, UnitsNano kshort, UnitsNano dlong, UnitsNano dshort, UnitsNano dlongMin, UnitsNano dshortMin, boolean shortEnabledFlag, String name, String exchange, String issueSize, String countryOfRisk, String countryOfRiskName, String sector, String issueSizePlan, CurrencyUnitsNano nominal, String tradingStatus, boolean otcFlag, boolean buyAvailableFlag, boolean sellAvailableFlag, boolean divYieldFlag, String shareType, UnitsNano minPriceIncrement, boolean apiTradeAvailable, String uid, String realExchange, String positionUid, boolean forIisFlag, boolean forQualInvestorFlag, boolean weekendFlag, boolean blockedTcaFlag, String first1minCandleDate, String first1dayCandleDate) {
        this.figi = figi;
        this.ticker = ticker;
        this.classCode = classCode;
        this.isin = isin;
        this.lot = lot;
        this.currency = currency;
        this.klong = klong;
        this.kshort = kshort;
        this.dlong = dlong;
        this.dshort = dshort;
        this.dlongMin = dlongMin;
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
        this.buyAvailableFlag = buyAvailableFlag;
        this.sellAvailableFlag = sellAvailableFlag;
        this.divYieldFlag = divYieldFlag;
        this.shareType = shareType;
        this.minPriceIncrement = minPriceIncrement;
        this.apiTradeAvailable = apiTradeAvailable;
        this.uid = uid;
        this.realExchange = realExchange;
        this.positionUid = positionUid;
        this.forIisFlag = forIisFlag;
        this.forQualInvestorFlag = forQualInvestorFlag;
        this.weekendFlag = weekendFlag;
        this.blockedTcaFlag = blockedTcaFlag;
        this.first1minCandleDate = first1minCandleDate;
        this.first1dayCandleDate = first1dayCandleDate;
    }
}
