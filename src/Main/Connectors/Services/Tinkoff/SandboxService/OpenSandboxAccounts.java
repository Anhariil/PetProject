package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.SandboxService.OpenSandboxAccountsResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class OpenSandboxAccounts extends SandboxService {
    private static final String method = "CloseSandboxAccount";
    protected OpenSandboxAccountsResponseMap response;

    public OpenSandboxAccounts() {
        setUrl("test");
        setHeaders();
        setJsonOutputString();
        setMethod("POST");
    }

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    public OpenSandboxAccountsResponseMap openResponse() {
        return this.response;
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
            this.response = new OpenSandboxAccountsResponseMap(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        OpenSandboxAccounts test = new OpenSandboxAccounts();
        test.getConnection();
        System.out.println(test.openResponse().getAccountId());
    }

}
