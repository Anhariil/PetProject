package Main.Connectors.Services.Tinkoff.OrderService;

import Main.Connectors.Services.Tinkoff.Tinkoff;

public class OrderService extends Tinkoff {
    private static final String name = "OrderService/";

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += name;
    }
}
