package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxAccount.GetSandboxAccountsResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetSandboxAccounts extends SandboxService {
    private static final String method = "GetSandboxAccounts";
    protected GetSandboxAccountsResponseMap response;

    public GetSandboxAccounts() {
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
    public GetSandboxAccountsResponseMap openResponse() {
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
            this.response = new GetSandboxAccountsResponseMap(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        GetSandboxAccounts test = new GetSandboxAccounts();
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }

}
