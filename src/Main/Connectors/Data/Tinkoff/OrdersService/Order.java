package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

public class Order {
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

    public Order() {
    }

    public Order(String orderId, String executionReportStatus, int lotsRequested, int lotsExecuted,
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

    public String getOrderId() {
        return orderId;
    }

    public String getExecutionReportStatus() {
        return executionReportStatus;
    }

    public int getLotsRequested() {
        return lotsRequested;
    }

    public int getLotsExecuted() {
        return lotsExecuted;
    }

    public CurrencyUnitsNano getInitialOrderPrice() {
        return initialOrderPrice;
    }

    public CurrencyUnitsNano getExecutedOrderPrice() {
        return executedOrderPrice;
    }

    public CurrencyUnitsNano getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public CurrencyUnitsNano getInitialCommission() {
        return initialCommission;
    }

    public CurrencyUnitsNano getExecutedCommission() {
        return executedCommission;
    }

    public CurrencyUnitsNano getAciValue() {
        return aciValue;
    }

    public String getFigi() {
        return figi;
    }

    public String getDirection() {
        return direction;
    }

    public CurrencyUnitsNano getInitialSecurityPrice() {
        return initialSecurityPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getMessage() {
        return message;
    }

    public UnitsNano getInitialOrderPricePt() {
        return initialOrderPricePt;
    }

    public String getInstrumentUid() {
        return instrumentUid;
    }
}
