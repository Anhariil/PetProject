package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.DateTime;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetOrderStateResponseMap extends Mapping {
    protected OrderState order;

    public GetOrderStateResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        int index = 0; // to arrayCUN
        List<OrderStage> stageList = new ArrayList<>();

        String orderId = "";
        String executionReportStatus = "";
        int lotsRequested = 0;
        int lotsExecuted = 0;
        CurrencyUnitsNano[] arrayCUN = new CurrencyUnitsNano[8]; // for all in list
        String currency = "";
        String units = "";
        int nano = 0;

        String figi = "";
        String direction = "";
        OrderStage[] stages = null;
        CurrencyUnitsNano stageCUN = null;
        int quantity = 0;
        String tradeId = "";
        String orderCurrency = "";
        String orderType = "";
        DateTime orderDate = null;
        String instrumentUid = "";

        int startId = 0;
        int finalId = 35;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("{"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 1:
                            orderId = nowLine.substring(12, nowLine.indexOf("\","));
                            break;
                        case 2:
                            executionReportStatus = nowLine.substring(26, nowLine.indexOf("\","));
                            break;
                        case 3:
                            lotsRequested = Integer.valueOf(nowLine.substring(18, nowLine.indexOf("\",")));
                            break;
                        case 4:
                            lotsExecuted = Integer.valueOf(nowLine.substring(17, nowLine.indexOf("\",")));
                            break;
                        // step 5 - start new array from 0 to 5
                        case 6:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 7:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 8:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 9: // step wth "},"
                            arrayCUN[index] = new CurrencyUnitsNano(units, nano, currency);
                            index++;
                            if (index < 6)
                                startId = 4; // repeat for all array
                            break;
                        case 10:
                            figi = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 11:
                            direction = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        // step 12 wth initialSecurityPrice {
                        case 13:
                            currency = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 14:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 15:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 16: // step wth "},"
                            arrayCUN[6] = new CurrencyUnitsNano(units, nano, currency);
                            break;
                        case 17:
                            if (nowLine.indexOf("]") != -1) { // stages is empty
                                stages = null;
                                startId = 25;
                            }
                            break;
                        /* TODO need not empty result to test */
                        // step 18 wth price {
                        case 19:
                            currency = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 20:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 21:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 22: // step wth "},"
                            stageCUN = new CurrencyUnitsNano(units, nano, currency);
                            break;
                        case 23:
                            quantity = Integer.valueOf(nowLine.substring(12, nowLine.indexOf("\",")));
                            break;
                        case 24:
                            tradeId = nowLine.substring(12, nowLine.length() - 1);
                            break;
                        case 25:
                            if (nowLine.indexOf("}]") == -1) { // more than one stage?
                                startId = 20; // set to new stage
                            }
                            stageList.add(new OrderStage(stageCUN, quantity, tradeId));
                            break;
                        // step 26 wth serviceCommission {
                        case 27:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 28:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 29:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 30: // step wth "},"
                            arrayCUN[7] = new CurrencyUnitsNano(units, nano, currency);
                            break;
                        case 31:
                            orderCurrency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 32:
                            orderType = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 33:
                            orderDate = DateTime.valueOf(nowLine.substring(14, nowLine.indexOf("\",")));
                            break;
                        case 34:
                            instrumentUid = nowLine.substring(18, nowLine.length() - 1);
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {
                        stages = new OrderStage[stageList.size()];
                        stageList.toArray(stages);
                        stageList.removeAll(stageList);
                        this.order = new OrderState(orderId, executionReportStatus, lotsRequested, lotsExecuted,
                                arrayCUN[0], arrayCUN[1], arrayCUN[2], arrayCUN[4],
                                arrayCUN[5], null, figi, direction, arrayCUN[6], orderType,
                                null, null, instrumentUid, arrayCUN[3], stages, arrayCUN[7], orderCurrency, orderDate);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Errors in \"GetOrderResponseMap\", line: " + startId + "\n" + e);
        }
    }

    public Order getOrder() {
        return order;
    }
}
