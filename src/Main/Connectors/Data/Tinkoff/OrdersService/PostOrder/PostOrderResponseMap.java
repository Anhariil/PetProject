package Main.Connectors.Data.Tinkoff.OrdersService.PostOrder;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.OrdersService.Order;

import java.io.BufferedReader;

public class PostOrderResponseMap extends Mapping {

    protected Order orderInfo;

    public PostOrderResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        int index = 0; // to arrayCUN

        String orderId = "";
        String executionReportStatus = "";
        int lotsRequested = 0;
        int lotsExecuted = 0;
        CurrencyUnitsNano[] arrayCUN = new CurrencyUnitsNano[6]; // may be 5 if aciValue empty
        String currency = "";
        String units = "";
        int nano = 0;

        String figi = "";
        String direction = "";
        String orderType = "";
        String message = "";
        CurrencyUnitsNano initialSecurityPrice = null;
        CurrencyUnitsNano initialOrderPricePt = null; // may be null
        String instrumentUid = "";

        int startId = 0;
        int finalId = 25;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
//                System.out.println(nowLine); // test now string

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
                        // step 5 - start new array
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
                            if (index < 5)
                                startId = 4; // repeat for all array
                            break;
                        case 10:
                            if (nowLine.indexOf("{") == -1) { //  page aciValue not exist
                                figi = nowLine.substring(10, nowLine.indexOf("\","));
                                startId = 15; // step away
                                arrayCUN[5] = null;
                            }
                            break;
                        case 11:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 12:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 13:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 14: // step wth "},"
                            arrayCUN[index] = new CurrencyUnitsNano(units, nano, currency);
                        case 15:
                            figi = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 16:
                            direction = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        // step 17 wth initialSecurityPrice {
                        case 18:
                            currency = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 19:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 20:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 21: // step wth "},"
                            initialSecurityPrice = new CurrencyUnitsNano(units, nano, currency);
                            break;
                        case 22:
                            orderType = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 23:
                            message = nowLine.substring(12, nowLine.indexOf("\","));
                            break;
                        case 24:
                            instrumentUid = nowLine.substring(18, nowLine.length() - 1);
                            break;
                    }
                    startId++;
                }
            }
            // after set all copy into primary
            this.orderInfo = new Order(orderId, executionReportStatus, lotsRequested, lotsExecuted, arrayCUN[0],
                    arrayCUN[1], arrayCUN[2], arrayCUN[3], arrayCUN[4], arrayCUN[5], figi, direction, initialSecurityPrice,
                    orderType, message, initialOrderPricePt, instrumentUid);
        } catch (Exception e) {
            System.out.println("Errors in \"PostOrderResponseMap\", line: " + startId + "\n" + e);
        }
    }

    public Order getOrderInfo() {
        return orderInfo;
    }
}
