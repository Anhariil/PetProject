package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.PortfolioPositions;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetSandboxPortfolioResponseMap extends Mapping {
    private Portfolio portfolio;

    public GetSandboxPortfolioResponseMap(BufferedReader bufferedReader) { //TODO continue after positive response
        boolean start = false;
        List<PortfolioPositions> portfolioPositions = new ArrayList<>();
        List<PortfolioPositions> virtualPortfolioPositions = new ArrayList<>();
        int index = 0; // index to arrayKD

        //TODO HashMap to fewer fields?

        CurrencyUnitsNano[] totalAmounts = new CurrencyUnitsNano[5];
        String nano = "";
        String currency = "";
        String units = "";

        PortfolioPositions[] positions;
        String accountId;
        PortfolioPositions[] virtualPositions;

        int startId = 0;
        int finalId = 47;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                //System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("{"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
//                if (start) {
//                    switch (startId) {
//
//                        case 1:
//                            if (nowLine.indexOf("{") == -1) { // search pages totalAmount
//                            }
//                            break;
//                        case 8:
//                            units = nowLine.substring(10, nowLine.indexOf("\","));
//                            break;
//                        case 9:
//                            nano = Integer.valueOf(nowLine.substring(8));
//                            break;
//                        case 10: // step wth "},"
//                            arrayKD[index] = new UnitsNano(units, nano);
//                            index++;
//                            if (index < 6) startId = 6; // repeat for all array
//                            else startId++; // step away to 12
//                            break;
//                        case 12:
//                            shortEnabledFlag = nowLine.substring(20, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 13:
//                            name = nowLine.substring(9, nowLine.indexOf("\","));
//                            break;
//                        case 14:
//                            exchange = nowLine.substring(13, nowLine.indexOf("\","));
//                            break;
//                        case 15: // may don't exist
//                            if (nowLine.indexOf("ipoDate") != -1) { // search page of ipoDate
//                                ipoDate = nowLine.substring(12, nowLine.indexOf("\","));
//                                break;
//                            } else {
//                                ipoDate = "";
//                                startId++; // go to 16
//                            }
//                        case 16:
//                            issueSize = nowLine.substring(14, nowLine.indexOf("\","));
//                            break;
//                        case 17:
//                            countryOfRisk = nowLine.substring(18, nowLine.indexOf("\","));
//                            break;
//                        case 18:
//                            countryOfRiskName = nowLine.substring(22, nowLine.indexOf("\","));
//                            break;
//                        case 19:
//                            sector = nowLine.substring(11, nowLine.indexOf("\","));
//                            break;
//                        case 20:
//                            issueSizePlan = nowLine.substring(18, nowLine.indexOf("\","));
//                            break;
//                        case 21:
//                            if (nowLine.indexOf("}") != -1) { // search page of nominal
//                                nominal = null;
//                            }
//                            break;
//                        case 22:
//                            currencyUN = nowLine.substring(13, nowLine.indexOf("\","));
//                            break;
//                        case 23:
//                            units = nowLine.substring(10, nowLine.indexOf("\","));
//                            break;
//                        case 24:
//                            nano = Integer.valueOf(nowLine.substring(8));
//                            nominal = new CurrencyUnitsNano(units, nano, currencyUN);
//                            break;
//                        case 26:
//                            tradingStatus = nowLine.substring(18, nowLine.indexOf("\","));
//                            break;
//                        case 27:
//                            otcFlag = nowLine.substring(11, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 28:
//                            buyAvailableFlag = nowLine.substring(20, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 29:
//                            sellAvailableFlag = nowLine.substring(21, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 30:
//                            divYieldFlag = nowLine.substring(16, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 31:
//                            shareType = nowLine.substring(14, nowLine.indexOf("\","));
//                            break;
//                        case 32:
//                            if (nowLine.indexOf("}") != -1) { // search page of minPriceIncrement
//                                minPriceIncrement = null;
//                            }
//                            break;
//                        case 33:
//                            units = nowLine.substring(10, nowLine.indexOf("\","));
//                            break;
//                        case 34:
//                            nano = Integer.valueOf(nowLine.substring(8));
//                            minPriceIncrement = new UnitsNano(units, nano);
//                            break;
//                        case 36:
//                            apiTradeAvailable = nowLine.substring(25, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 37:
//                            uid = nowLine.substring(8, nowLine.indexOf("\","));
//                            break;
//                        case 38:
//                            realExchange = nowLine.substring(17, nowLine.indexOf("\","));
//                            break;
//                        case 39:
//                            positionUid = nowLine.substring(16, nowLine.indexOf("\","));
//                            break;
//                        case 40:
//                            forIisFlag = nowLine.substring(14, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 41:
//                            forQualInvestorFlag = nowLine.substring(23, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 43:
//                            weekendFlag = nowLine.substring(18, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                            break;
//                        case 44: // may be empty
//                            if (nowLine.indexOf("blockedTcaFlag") != -1) { // search page of minPriceIncrement
//                                blockedTcaFlag = nowLine.substring(15, nowLine.indexOf(",")).equalsIgnoreCase("true");
//                                break;
//                            } else {// else - go next
//                                blockedTcaFlag = false;
//                                startId++;
//                            }
//                        case 45:
//                            first1minCandleDate = nowLine.substring(24, nowLine.indexOf("\","));
//                            break;
//                        case 46:
//                            first1dayCandleDate = nowLine.substring(24);
//                            break;
//                    }
//                    startId++;
//                    if (startId >= finalId) {
//                        instrumentList.add(new Instrument(figi, ticker, classCode, isin, lot, currency, arrayKD[0],
//                                arrayKD[1], arrayKD[2], arrayKD[3], arrayKD[4], arrayKD[5], shortEnabledFlag, name,
//                                exchange, ipoDate, issueSize, countryOfRisk, countryOfRiskName, sector, issueSizePlan, nominal,
//                                tradingStatus, otcFlag, buyAvailableFlag, sellAvailableFlag, divYieldFlag, shareType,
//                                minPriceIncrement, apiTradeAvailable, uid, realExchange, positionUid, forIisFlag,
//                                forQualInvestorFlag, weekendFlag, blockedTcaFlag, first1minCandleDate, first1dayCandleDate));
//                        startId = 0;
//                        finalId = 47;
//                        index = 0; // to new array
//                    }
//                }
            }
            // after set all copy into primary
            this.portfolio = new Portfolio();
        } catch (Exception e) {
            System.out.println("Errors in \"GetAssetsResponseMap\", line: " + startId + "\n" + e);
        }
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
