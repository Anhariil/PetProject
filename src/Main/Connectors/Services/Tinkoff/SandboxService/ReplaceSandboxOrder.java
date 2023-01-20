package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderResponseMap;
import Main.Connectors.Data.Tinkoff.OrdersService.ReplaceOrderRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class ReplaceSandboxOrder extends SandboxService {
    private static final String method = "ReplaceSandboxOrder";
    protected ReplaceOrderRequest request;
    protected PostOrderResponseMap response;

    public ReplaceSandboxOrder(String url, String method, ReplaceOrderRequest request) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(request);
        setMethod(method);
    }

    public ReplaceSandboxOrder(ReplaceOrderRequest request) {
        setUrl("test");
        setHeaders();
        setJsonOutputString(request);
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
            this.response = new PostOrderResponseMap(bufferedReader);
        }
    }

    @Override
    public PostOrderResponseMap openResponse() {
        return this.response;
    }

    protected void setJsonOutputString() {
        this.jsonOutputString = this.request.toJsonString();
    }

    protected void setJsonOutputString(ReplaceOrderRequest request) {
        this.jsonOutputString = request.toJsonString();
    }

    public static void main(String[] args) throws IOException {
        ReplaceOrderRequest request = ReplaceOrderRequest.newReplaceOrder("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6",
                "500cc19b-8d55-4b01-bd80-533976792abd", "3", new UnitsNano("38", 0), "currency");
        ReplaceSandboxOrder test = new ReplaceSandboxOrder(request);
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }
}
