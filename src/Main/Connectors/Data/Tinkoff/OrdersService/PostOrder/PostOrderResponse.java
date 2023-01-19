package Main.Connectors.Data.Tinkoff.OrdersService.PostOrder;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

public class PostOrderResponse {
    protected String orderId;
    protected String executionReportStatus;
    protected int lotsRequested;
    protected int lotsExecuted;
    protected CurrencyUnitsNano initialOrderPrice;
    protected CurrencyUnitsNano executedOrderPrice;
    protected CurrencyUnitsNano totalOrderAmount;
    protected CurrencyUnitsNano initialCommission;
    protected CurrencyUnitsNano executedCommission;
    protected CurrencyUnitsNano aciValue;
    protected String figi;
    protected String direction;
    protected CurrencyUnitsNano initialSecurityPrice;
    protected String orderType;
    protected String message;
    protected UnitsNano initialOrderPricePt;
    protected String instrumentUid;

    public PostOrderResponse(String orderId, String executionReportStatus, int lotsRequested, int lotsExecuted,
                             CurrencyUnitsNano initialOrderPrice, CurrencyUnitsNano executedOrderPrice, CurrencyUnitsNano totalOrderAmount,
                             CurrencyUnitsNano initialCommission, CurrencyUnitsNano executedCommission,
                             CurrencyUnitsNano aciValue, String figi, String direction, CurrencyUnitsNano initialSecurityPrice,
                             String orderType, String message, UnitsNano initialOrderPricePt, String instrumentUid) {
        this.orderId = orderId;
        this.executionReportStatus = executionReportStatus;
        this.lotsRequested = lotsRequested;
        this.lotsExecuted = lotsExecuted;
        this.initialOrderPrice = initialOrderPrice;
        this.executedOrderPrice = executedOrderPrice;
        this.totalOrderAmount = totalOrderAmount;
        this.initialCommission = initialCommission;
        this.executedCommission = executedCommission;
        this.aciValue = aciValue;
        this.figi = figi;
        this.direction = direction;
        this.initialSecurityPrice = initialSecurityPrice;
        this.orderType = orderType;
        this.message = message;
        this.initialOrderPricePt = initialOrderPricePt;
        this.instrumentUid = instrumentUid;
    }
}
