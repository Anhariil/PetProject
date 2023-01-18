package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseSandboxAccount extends SandboxService {
    private static final String method = "CloseSandboxAccount";

    public CloseSandboxAccount(String url, String method, String accountId) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(accountId);
        setMethod(method);
    }

    public CloseSandboxAccount(String accountId) {
        setUrl("test");
        setHeaders();
        setJsonOutputString(accountId);
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
        }
    }

    protected void setJsonOutputString(String accountId) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"accountId\":\"" + accountId + "\"";
        this.jsonOutputString += "}";
    }

    public static void main(String[] args) throws IOException {
        CloseSandboxAccount test = new CloseSandboxAccount("3a56c018-a270-4b96-8674-331da25dadfd");
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }

}
