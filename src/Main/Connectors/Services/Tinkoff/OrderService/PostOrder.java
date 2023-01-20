package Main.Connectors.Services.Tinkoff.OrderService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderRequest;
import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class PostOrder extends OrderService {
    private static final String method = "PostOrder";
    protected PostOrderRequest request;
    protected PostOrderResponseMap response;

    public PostOrder(String url, String method, PostOrderRequest request) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(request);
        setMethod(method);
    }

//    public PostOrder(String accountId) {
//        setUrl("test");
//        setHeaders();
//        setJsonOutputString();
//        setMethod("POST");
//    }

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

    protected void setJsonOutputString() {
        this.jsonOutputString = this.request.toJsonString();
    }

    protected void setJsonOutputString(PostOrderRequest request) {
        this.jsonOutputString = request.toJsonString();
    }

    @Override
    public PostOrderResponseMap openResponse() {
        return this.response;
    }
//    public static void main(String[] args) throws IOException {
//        PostOrder test = new PostOrder("bf9a5fe2-1db1-4d63-962c-931a28fb5ba6");
//        test.getConnection();
//        // System.out.println(test.openResponse().getAccountId());
//    }
}