package Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetCandlesResponseMap extends Mapping {
    private Candle[] candles;

    public GetCandlesResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        List<Candle> candlesList = new ArrayList<>();
        int index = 0; // index to arrayUN

        UnitsNano[] arrayUN = new UnitsNano[5]; // array wht open, hight ...
        String units = "";
        int nano = -1;
        String volume = "";
        String time = "";
        boolean isComplete = false;

        int startId = 0;
        int finalId = 8;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("\"candles\": [{"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 2:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 3:
                            nano = Integer.valueOf(nowLine.substring(8));
                            break;
                        case 4: // step wth "},"
                            arrayUN[index] = new UnitsNano(units, nano);
                            index++;
                            if (index < 4) startId = 0; // repeat for all array
                            break;
                        case 5:
                            volume = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 6:
                            time = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 7:
                            isComplete = nowLine.substring(14).equalsIgnoreCase("true");
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {
                        candlesList.add(new Candle(arrayUN[0], arrayUN[1], arrayUN[2], arrayUN[3], volume, time, isComplete));
                        startId = 0;
                        finalId = 8;
                        index = 0; // to new array
                    }
                }
            }
            // after set all copy into primary
//            this.instruments = new Instrument[instrumentList.size()];
//            instrumentList.toArray(this.instruments);
//            instrumentList.removeAll(instrumentList);
        } catch (Exception e) {
            System.out.println("Errors in \"GetCandlesResponseMap\", line: " + candlesList.size() + "\n" + e);
        }
    }
}
