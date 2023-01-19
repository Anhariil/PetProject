package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Data.Tinkoff.OrdersService.PostOrder.PostOrderResponseMap;

import java.io.BufferedReader;

public class PostSandboxOrderResponseMap extends PostOrderResponseMap {

    public PostSandboxOrderResponseMap(BufferedReader bufferedReader) {
        super(bufferedReader);
    }
}
