package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.DateTime;

public class OrderState extends Order {

    protected CurrencyUnitsNano averagePositionsPrice;
    protected OrderStage[] stages;
    protected CurrencyUnitsNano serviceCommission;
    protected String currency;
    protected DateTime orderDate;

    public OrderState(String orderId, String executionReportStatus, int lotsRequested, int lotsExecuted, CurrencyUnitsNano initialOrderPrice, CurrencyUnitsNano executedOrderPrice, CurrencyUnitsNano totalOrderAmount, CurrencyUnitsNano initialCommission, CurrencyUnitsNano executedCommission, CurrencyUnitsNano aciValue, String figi, String direction, CurrencyUnitsNano initialSecurityPrice, String orderType, String message, UnitsNano initialOrderPricePt, String instrumentUid, CurrencyUnitsNano averagePositionsPrice, OrderStage[] stages, CurrencyUnitsNano serviceCommission, String currency, DateTime orderDate) {
        super(orderId, executionReportStatus, lotsRequested, lotsExecuted, initialOrderPrice, executedOrderPrice, totalOrderAmount, initialCommission, executedCommission, aciValue, figi, direction, initialSecurityPrice, orderType, message, initialOrderPricePt, instrumentUid);
        this.averagePositionsPrice = averagePositionsPrice;
        this.stages = stages;
        this.serviceCommission = serviceCommission;
        this.currency = currency;
        this.orderDate = orderDate;
    }

}
