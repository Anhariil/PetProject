package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Tinkoff.ErrorResponse;
import Main.Connectors.Data.Tinkoff.OrdersService.CancelOrderResponseMap;
import Main.Connectors.Data.Tinkoff.OrdersService.Order;

import java.io.BufferedReader;
import java.io.IOException;

public class CancelSandboxOrder extends SandboxService {
    private static final String method = "CancelSandboxOrder";
    protected CancelOrderResponseMap response;

    public CancelSandboxOrder(String accountId, String orderId) {
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
    public CancelOrderResponseMap openResponse() {
        return this.response;
    }

    @Override
    protected void setResponse(BufferedReader bufferedReader) {
        if (this.responseCode > 299) {
            this.errorResponse = new ErrorResponse(bufferedReader);
        } else {
            this.response = new CancelOrderResponseMap(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        GetSandboxOrders orders = new GetSandboxOrders("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
        orders.getConnection();
        for (Order o : orders.openResponse().getOrders()) {
            CancelSandboxOrder test = new CancelSandboxOrder("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6", o.getOrderId());
            test.getConnection();
        }
        // System.out.println(test.openResponse().getAccountId());
    }
}
