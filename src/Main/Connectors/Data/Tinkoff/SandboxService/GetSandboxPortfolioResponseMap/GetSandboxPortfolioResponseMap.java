package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.Connectors.Data.Tinkoff.PortfolioPositions;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetSandboxPortfolioResponseMap extends Mapping {
    private Portfolio portfolio;

    public GetSandboxPortfolioResponseMap(BufferedReader bufferedReader) { //TODO continue after positive response
        boolean start = false;
        List<PortfolioPositions> portfolioPositionsList = new ArrayList<>();
        List<PortfolioPositions> virtualPortfolioPositionsList = new ArrayList<>();
        int index = 0; // index to totalAmounts
        int indexQ = 0; // index to quantity
        int indexA = 0; // index to average

        //TODO HashMap to fewer fields?

        CurrencyUnitsNano[] totalAmounts = new CurrencyUnitsNano[5];
        int nano = 0;
        String currency = null;
        String units = null;

        PortfolioPositions[] positions = null;
        String figi = null;
        String instrumentType = null;
        // or only two quantity, or all ?
        boolean isAverage = false;
        UnitsNano[] quantity = new UnitsNano[3];
        CurrencyUnitsNano[] average = new CurrencyUnitsNano[3];
        Boolean blocked = null;
        String positionUid = null;
        String instrumentUid = null;

        String accountId = null;
        PortfolioPositions[] virtualPositions = null;

        int startId = 0;
        int finalId = 27;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                //System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("{")) // start on first {
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        // step 1  wth "totalAmountShares": {
                        case 2:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 3:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 4:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 5: // step wth "},"
                            totalAmounts[index] = new CurrencyUnitsNano(units, nano, currency);
                            index++;
                            if (index < 5)
                                startId = 0; // repeat for all array
                            break;
                        case 6:
                            if (nowLine.indexOf("]") != -1) { // positions is empty
                                startId = 25; // step away
                            }
                            break;
                        case 7:
                            figi = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 8:
                            instrumentType = nowLine.substring(19, nowLine.indexOf("\","));
                            break;
                        // step 9 wth quantity {
                        case 10:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 11:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 12: // step wth "},"
                            quantity[indexQ] = new UnitsNano(units, nano);
                            indexQ++;
                            break;
                        case 13: // may don't exist
                            if (nowLine.indexOf("quantityLots") != -1) { // averagePositionPrice not exist or wrote
                                for (CurrencyUnitsNano c : average) // set all average null
                                    c = null;
                                quantity[indexQ] = null; // set averagePositionPricePt null
                                indexQ++;
                                startId = 18; // step away to quantityLots
                            }
                            break;
                        case 14:
                            if (nowLine.indexOf("currency") != -1) { // currency exist
                                currency = nowLine.substring(13, nowLine.indexOf("\","));
                                break;
                            } else {
                                currency = null;
                                startId++; // step away
                            }
                        case 15:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 16:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 17: // step wth "},"
                            if (currency != null) { // its average
                                average[indexA] = new CurrencyUnitsNano(units, nano, currency);
                                indexA++;
                            } else {
                                quantity[indexQ] = new UnitsNano(units, nano);
                                indexQ++;
                            }
                            if (indexA < 3) { // repeat until averagePositionPriceFifo
                                startId = 12;
                            }
                            break;
                        // step 18 wth quantityLots {
                        case 19:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 20:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 21: // step wth "},"
                            quantity[indexQ] = new UnitsNano(units, nano);
                            break;
                        case 22:
                            blocked = nowLine.substring(11, nowLine.indexOf(",")).equalsIgnoreCase("true");
                            break;
                        case 23:
                            positionUid = nowLine.substring(16, nowLine.indexOf("\","));
                            break;
                        case 24:
                            instrumentUid = nowLine.substring(18, nowLine.length() - 1);
                            break;
                        case 25:
                            if (nowLine.indexOf("}]") == -1) { // its last position?
                                startId = 6; // start new positions
                            }
                            portfolioPositionsList.add(new PortfolioPositions(figi, instrumentType, quantity[0],
                                    average[0], quantity[1], average[1],
                                    average[2], quantity[2], blocked, positionUid, instrumentUid));
                            indexQ = 0;
                            indexA = 0;
                            for (CurrencyUnitsNano c : average) // set all average null
                                c = null;
                            for (UnitsNano c : quantity) // set all quantity null
                                c = null;
                            break;
                        case 26:
                            accountId = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 27: // step wth virtualPositions
                            if (nowLine.indexOf("]") != -1) { // virtual position is empty ?
                                break;
                            }
                            /* TODO some more code wth parsing virtual position */
//                        case 28:

                    }
                    startId++;
                    if (startId >= finalId) {
                        positions = new PortfolioPositions[portfolioPositionsList.size()];
                        portfolioPositionsList.toArray(positions);
                        virtualPositions = new PortfolioPositions[virtualPortfolioPositionsList.size()];
                        virtualPortfolioPositionsList.toArray(virtualPositions);
                        this.portfolio = new Portfolio(totalAmounts[0], totalAmounts[1],
                                totalAmounts[2], totalAmounts[3], totalAmounts[4],
                                positions, accountId, virtualPositions);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Errors in \"GetSandboxPortfolioResponseMap\", line: " + startId + "\n" + e);
        }
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
