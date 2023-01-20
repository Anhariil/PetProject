package Main.Connectors.Services.Tinkoff.OrderService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.OrdersService.GetOrdersResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetOrders extends OrderService {
    private static final String method = "GetOrders";
    protected GetOrdersResponseMap response;

    public GetOrders(String accountId) {
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

//    public static void main(String[] args) throws IOException {
//        GetOrders test = new GetOrders();
//        test.getConnection();
//        // System.out.println(test.openResponse().getAccountId());
//    }
}