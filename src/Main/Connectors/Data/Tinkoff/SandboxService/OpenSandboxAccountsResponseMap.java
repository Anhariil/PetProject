package Main.Connectors.Data.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;

import java.io.BufferedReader;


public class OpenSandboxAccountsResponseMap extends Mapping {
    protected String accountId;

    public OpenSandboxAccountsResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        int index = 0; // index to arrayUN

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
                    switch (index) {
                        case 1:
                            this.accountId = nowLine.substring(14, nowLine.length() - 1);
                    }
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println("Errors in \"OpenSandboxAccountsResponseMap\"" + "\n" + e);
        }
    }

    public String getAccountId() {
        return accountId;
    }
}
