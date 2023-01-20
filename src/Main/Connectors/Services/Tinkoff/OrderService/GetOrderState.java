package Main.Connectors.Services.Tinkoff.OrderService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.OrdersService.GetOrderStateResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetOrderState extends OrderService {
    private static final String method = "GetOrderState";
    protected GetOrderStateResponseMap response;

    public GetOrderState(String accountId, String orderId) {
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

//    public static void main(String[] args) throws IOException {
//        GetOrders test = new GetOrders();
//        test.getConnection();
//        // System.out.println(test.openResponse().getAccountId());
//    }
}
