package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxAccount;

import Main.Connectors.Data.Mapping;
import Main.DateTime;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetSandboxAccountsResponseMap extends Mapping {
    protected Account[] accounts;

    public GetSandboxAccountsResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        List<Account> accountList = new ArrayList<>();

        String id = "";
        String type = "";
        String name = "";
        String status = "";
        DateTime openDate = null;
        String accessLevel = "";

        int startId = 0;
        int finalId = 7;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("\"accounts\": [{"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 1:
                            id = nowLine.substring(7, nowLine.indexOf("\","));
                            break;
                        case 2:
                            type = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 3:
                            name = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 4:
                            status = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 5:
                            openDate = new DateTime(nowLine.substring(15, nowLine.indexOf("\",")));
                            break;
                        case 6:
                            accessLevel = nowLine.substring(16, nowLine.length() - 1);
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {
                        accountList.add(new Account(id, type, name, status, openDate, accessLevel));
                        startId = 0;
                        finalId = 7;
                    }
                }
            }
            this.accounts = new Account[accountList.size()];
            accountList.toArray(this.accounts);
        } catch (Exception e) {
            System.out.println("Errors in \"GetSandboxAccountsResponseMap\" in line: " + accountList.size() + "\n" + e);
        }
    }

    public Account[] getAccounts() {
        return accounts;
    }
}
