package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap.GetSandboxPortfolioResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetSandboxPortfolio extends SandboxService {
    private static final String method = "GetSandboxPortfolio";
    protected GetSandboxPortfolioResponseMap response;

    public GetSandboxPortfolio(String accountId, String currency) {
        setUrl("test");
        setHeaders();
        setJsonOutputString(accountId, currency);
        setMethod("POST");
    }

    /**
     * default response wth currency in RUB
     *
     * @param accountId
     */
    public GetSandboxPortfolio(String accountId) {
        setUrl("test");
        setHeaders();
        setJsonOutputString(accountId, "rub");
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
            this.response = new GetSandboxPortfolioResponseMap(bufferedReader);
        }
    }

    protected void setJsonOutputString(String accountId, String currency) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"accountId\":\"" + accountId + "\",";
        this.jsonOutputString += "\"currency\":\"" + currency + "\"";
        this.jsonOutputString += "}";
    }

    public static void main(String[] args) throws IOException {
        GetSandboxPortfolio test = new GetSandboxPortfolio("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }

    @Override
    public GetSandboxPortfolioResponseMap openResponse() {
        return this.response;
    }
}
