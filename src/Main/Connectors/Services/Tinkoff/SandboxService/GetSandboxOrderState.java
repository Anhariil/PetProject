package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.OrdersService.GetOrderStateResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetSandboxOrderState extends SandboxService {
    private static final String method = "GetSandboxOrderState";
    protected GetOrderStateResponseMap response;

    public GetSandboxOrderState(String accountId, String orderId) {
        setUrl("test");
        setHeaders();
        setJsonOutputString(accountId, orderId);
        setMethod("POST");
    }

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    protected void setJsonOutputString(String accountId, String orderId) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"accountId\":\"" + accountId + "\",";
        this.jsonOutputString += "\"orderId\":\"" + orderId + "\"";
        this.jsonOutputString += "}";
    }

    @Override
    public GetOrderStateResponseMap openResponse() {
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
            this.response = new GetOrderStateResponseMap(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        GetSandboxOrderState test = new GetSandboxOrderState("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6", "4580ca65-ca86-482a-8750-6e216b3268b8");
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }
}
