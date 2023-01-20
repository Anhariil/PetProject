package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;

public class OrderStage {
    protected CurrencyUnitsNano price;
    protected int quantity;
    protected String tradeId;

    public OrderStage(CurrencyUnitsNano price, int quantity, String tradeId) {
        this.price = price;
        this.quantity = quantity;
        this.tradeId = tradeId;
    }
}
