package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Mapping;
import Main.DateTime;

import java.io.BufferedReader;

public class CancelOrderResponseMap extends Mapping {

    protected DateTime cancelTime;

    public CancelOrderResponseMap(BufferedReader bufferedReader) {
        boolean start = false;

        DateTime cancelTime = null;

        int startId = 0;
        int finalId = 25;

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
                            cancelTime = DateTime.valueOf(nowLine.substring(9, nowLine.length() - 1));
                            break;
                    }
                    startId++;
                }
            }
            // after set all copy into primary
            this.cancelTime = cancelTime;
        } catch (Exception e) {
            System.out.println("Errors in \"CancelOrderResponseMap\", line: " + startId + "\n" + e);
        }
    }

    public DateTime getCancelTime() {
        return cancelTime;
    }
}
