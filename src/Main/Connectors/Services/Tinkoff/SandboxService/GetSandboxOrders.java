package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.OrdersService.GetOrdersResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetSandboxOrders extends SandboxService {
    private static final String method = "GetSandboxOrders";
    protected GetOrdersResponseMap response;

    public GetSandboxOrders(String accountId) {
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

    protected void setJsonOutputString(String accountId) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"accountId\":\"" + accountId + "\"";
        this.jsonOutputString += "}";
    }

    @Override
    public GetOrdersResponseMap openResponse() {
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
            this.response = new GetOrdersResponseMap(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        GetSandboxOrders test = new GetSandboxOrders("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }
}
