package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderRequest;
import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class PostSandboxOrder extends SandboxService {
    private static final String method = "PostSandboxOrder";
    protected PostOrderResponseMap response;
    protected PostOrderRequest request;

    public PostSandboxOrder(String url, String method, PostOrderRequest request) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(request);
        setMethod(method);
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

    protected void setJsonOutputString() {
        this.jsonOutputString = this.request.toJsonString();
    }

    protected void setJsonOutputString(PostOrderRequest request) {
        this.jsonOutputString = request.toJsonString();
    }

    public static void main(String[] args) throws IOException {
        PostOrderRequest request = PostOrderRequest.newRequest("BBG000BN56Q9", "1", new UnitsNano("40", 0),
                "buy", "bf9a5fe2-1db1-4d63-962c-931a28fb5ba6", "market", "BBG000BN56Q9");
        PostSandboxOrder test = new PostSandboxOrder("test", "POST", request);
        test.getConnection();
        // System.out.println(test.openResponse().getAccountId());
    }
}