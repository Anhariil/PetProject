package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.SandboxService.SandboxPayInResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class SandboxPayIn extends SandboxService {
    private static final String method = "SandboxPayIn";
    protected SandboxPayInResponseMap response;

    public SandboxPayIn(String url, String method, String accountId, CurrencyUnitsNano amount) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(accountId, amount);
        setMethod(method);
    }

    public SandboxPayIn(String accountId) { // default for test (add 100 rub)
        CurrencyUnitsNano amount = new CurrencyUnitsNano("10000", 0, "rub");
        setUrl("test");
        setHeaders();
        setJsonOutputString(accountId, amount);
        setMethod("POST");
    }

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    protected void setResponse(BufferedReader bufferedReader) {
        if (this.responseCode > 299) {
            try {
                Mapping.LogErrorResponse(bufferedReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.response = new SandboxPayInResponseMap(bufferedReader);
        }
    }

    protected void setJsonOutputString(String accountId, CurrencyUnitsNano amount) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"accountId\":\"" + accountId + "\",";
        this.jsonOutputString += "\"amount\":";
        this.jsonOutputString += "{";
        this.jsonOutputString += "\"nano\":\"" + amount.getNano() + "\",";
        this.jsonOutputString += "\"currency\":\"" + amount.getCurrency() + "\",";
        this.jsonOutputString += "\"units\":\"" + amount.getUnits() + "\"";
        this.jsonOutputString += "}";
        this.jsonOutputString += "}";
    }

    public static void main(String[] args) throws IOException {
        SandboxPayIn test = new SandboxPayIn("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }
}