package Main.Connectors.Data.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;

import java.io.BufferedReader;

public class SandboxPayInResponseMap extends Mapping {
    protected CurrencyUnitsNano balance;

    public SandboxPayInResponseMap(BufferedReader bufferedReader) {
        boolean start = false;

        String currency = "";
        String units = "";
        int nano = 0;

        int startId = 0;
        int finalId = 5;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("\"balance\": {"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 1:
                            currency = nowLine.substring(13, nowLine.indexOf("\","));
                            break;
                        case 2:
                            units = nowLine.substring(10, nowLine.indexOf("\","));
                            break;
                        case 3:
                            nano = Integer.valueOf(nowLine.substring(8, nowLine.length()));
                            break;
                    }
                    startId++;
                }
            }
            this.balance = new CurrencyUnitsNano(units, nano, currency);
        } catch (Exception e) {
            System.out.println("Errors in \"SandboxPayInResponseMap\" in line: " + startId + "\n" + e);
        }
    }

    public CurrencyUnitsNano getBalance() {
        return balance;
    }
}
