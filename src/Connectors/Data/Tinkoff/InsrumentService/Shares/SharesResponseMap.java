package Connectors.Data.Tinkoff.InsrumentService.Shares;

import Connectors.Data.Mapping;
import Connectors.Data.Tinkoff.InsrumentService.Instrument;
import Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class SharesResponseMap extends Mapping {

    private Instrument[] instruments;

    /**
     *
     */
    public SharesResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        List<Instrument> instrumentList = new ArrayList<>();
        int index = 0; // index to arrayKD

        //TODO HashMap to fewer fields?

        String figi = "";
        String ticker = "";
        String classCode = "";
        String isin = "";
        int lot = -1;
        String currency = "";
        UnitsNano[] arrayKD = new UnitsNano[6]; // array wht klong,kshort ...
        boolean shortEnabledFlag = false;
        String name = "";
        String exchange = "";
        String ipoDate = "";
        String issueSize = "";
        String countryOfRisk = "";
        String countryOfRiskName = "";
        String sector = "";
        String issueSizePlan = "";
        CurrencyUnitsNano nominal = null;
        String currencyUN = "";
        String units = "";
        int nano = -1;
        String tradingStatus = "";
        boolean otcFlag = false;
        boolean buyAvailableFlag = false;
        boolean sellAvailableFlag = false;
        boolean divYieldFlag = false;
        String shareType = "";
        UnitsNano minPriceIncrement = null;
        boolean apiTradeAvailable = false;
        String uid = "";
        String realExchange = "";
        String positionUid = "";
        boolean forIisFlag = false;
        boolean forQualInvestorFlag = false;
        boolean weekendFlag = false;
        boolean blockedTcaFlag = false;
        String first1minCandleDate = "";
        String first1dayCandleDate = "";

        int startId = 0;
        int finalId = 47;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                //System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("\"instruments\": [{")) start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 1:
                            figi = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 2:
                            ticker = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 3:
                            classCode = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 4:
                            isin = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 5:
                            lot = Integer.valueOf(nowLine.substring(7, nowLine.indexOf(",")));
                            break;
                        case 6:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 7:
                            if (nowLine.indexOf("{") == -1) { // search pages klong
                                startId = 12; // step away
                                for (int i = 0; i < 6; i++)
                                    arrayKD[i] = null; // don't have information for this instrument
                                shortEnabledFlag = nowLine.substring(20, nowLine.indexOf(",")).equalsIgnoreCase("true"); // set info
                            }
                            break;
                        case 8:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 9:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 10: // step wth "},"
                            arrayKD[index] = new UnitsNano(units, nano);
                            index++;
                            if (index < 6) startId = 6; // repeat for all array
                            else startId++; // step away to 12
                            break;
                        case 12:
                            shortEnabledFlag = nowLine.substring(20, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 13:
                            name = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 14:
                            exchange = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 15: // may don't exist
                            if (nowLine.indexOf("ipoDate") != -1) { // search page of ipoDate
                                ipoDate = nowLine.substring(12, nowLine.indexOf("\","));
                                break;
                            } else {
                                ipoDate = "";
                                startId++; // go to 16
                            }
                        case 16:
                            issueSize = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 17:
                            countryOfRisk = nowLine.substring(18, nowLine.indexOf("\","));
                            break;
                        case 18:
                            countryOfRiskName = nowLine.substring(22, nowLine.indexOf("\","));
                            break;
                        case 19:
                            sector = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 20:
                            issueSizePlan = nowLine.substring(18, nowLine.indexOf("\","));
                            break;
                        case 21:
                            if (nowLine.indexOf("}") != -1) { // search page of nominal
                                nominal = null;
                            }
                            break;
                        case 22:
                            currencyUN = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 23:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 24:
                            nano = Integer.valueOf(nowLine.substring(8));
                            nominal = new CurrencyUnitsNano(units, nano, currencyUN);
                            break;
                        case 26:
                            tradingStatus = nowLine.substring(18, nowLine.indexOf("\","));
                            break;
                        case 27:
                            otcFlag = nowLine.substring(11, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 28:
                            buyAvailableFlag = nowLine.substring(20, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 29:
                            sellAvailableFlag = nowLine.substring(21, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 30:
                            divYieldFlag = nowLine.substring(16, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 31:
                            shareType = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 32:
                            if (nowLine.indexOf("}") != -1) { // search page of minPriceIncrement
                                minPriceIncrement = null;
                            }
                            break;
                        case 33:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 34:
                            nano = Integer.valueOf(nowLine.substring(8));
                            minPriceIncrement = new UnitsNano(units, nano);
                            break;
                        case 36:
                            apiTradeAvailable = nowLine.substring(25, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 37:
                            uid = nowLine.substring(8, nowLine.indexOf("\","));
                            break;
                        case 38:
                            realExchange = nowLine.substring(17, nowLine.indexOf("\","));
                            break;
                        case 39:
                            positionUid = nowLine.substring(16, nowLine.indexOf("\","));
                            break;
                        case 40:
                            forIisFlag = nowLine.substring(14, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 41:
                            forQualInvestorFlag = nowLine.substring(23, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 43:
                            weekendFlag = nowLine.substring(18, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 44: // may be empty
                            if (nowLine.indexOf("blockedTcaFlag") != -1) { // search page of minPriceIncrement
                                blockedTcaFlag = nowLine.substring(15, nowLine.indexOf(",")).equalsIgnoreCase("true");
                                break;
                            } else {// else - go next
                                blockedTcaFlag = false;
                                startId++;
                            }
                        case 45:
                            first1minCandleDate = nowLine.substring(24, nowLine.indexOf("\","));
                            break;
                        case 46:
                            first1dayCandleDate = nowLine.substring(24);
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {
                        instrumentList.add(new Instrument(figi, ticker, classCode, isin, lot, currency, arrayKD[0], arrayKD[1], arrayKD[2], arrayKD[3], arrayKD[4], arrayKD[5], shortEnabledFlag, name, exchange, issueSize, countryOfRisk, countryOfRiskName, sector, issueSizePlan, nominal, tradingStatus, otcFlag, buyAvailableFlag, sellAvailableFlag, divYieldFlag, shareType, minPriceIncrement, apiTradeAvailable, uid, realExchange, positionUid, forIisFlag, forQualInvestorFlag, weekendFlag, blockedTcaFlag, first1minCandleDate, first1dayCandleDate));
                        startId = 0;
                        finalId = 47;
                        index = 0; // to new array
                    }
                }
            }
            // after set all copy into primary
            this.instruments = new Instrument[instrumentList.size()];
            instrumentList.toArray(this.instruments);
            instrumentList.removeAll(instrumentList);
        } catch (Exception e) {
            System.out.println("Errors in \"GetAssetsResponseMap\", line: " + instrumentList.size() + "\n" + e);
        }
    }

    @Override
    public String toString() {
        return ""; // заглушка
    }

    public Instrument getInstrumentByName(String name) { //TODO sql requests?
        int index = 0;
        while (true) {
            if (this.instruments[index].getName().equalsIgnoreCase(name)) break;
            index++;
        }
        return this.instruments[index];
    }

    public String[] getAllNames() {
        String[] arrayOfNames = new String[this.instruments.length];
        for (int i = 0; i < this.instruments.length; i++) {
            arrayOfNames[i] = this.instruments[i].getName();
        }
        return arrayOfNames;
    }
}
