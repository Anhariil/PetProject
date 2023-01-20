package Main.Connectors.Data.Tinkoff.OrdersService.PostOrder;

import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.DateTime;

public class PostOrderRequest {
    protected String figi;
    protected String quantity;
    protected UnitsNano price;
    protected String direction;
    protected String accountId;
    protected String orderType;
    protected String orderId;
    protected String instrumentId;

    /**
     * @param figi
     * @param quantity
     * @param price
     * @param direction    should take values: ORDER_DIRECTION_UNSPECIFIED, ORDER_DIRECTION_BUY, ORDER_DIRECTION_SELL
     * @param accountId
     * @param orderType    should take values: ORDER_TYPE_UNSPECIFIED, ORDER_TYPE_LIMIT, ORDER_TYPE_MARKET
     * @param orderId      should be universal for accountId
     * @param instrumentId
     */
    public PostOrderRequest(String figi, String quantity, UnitsNano price, String direction, String accountId, String orderType, String orderId, String instrumentId) {
        this.figi = figi;
        this.quantity = quantity;
        this.price = price;
        this.direction = direction;
        this.accountId = accountId;
        this.orderType = orderType;
        this.orderId = orderId;
        this.instrumentId = instrumentId;
    }

    public PostOrderRequest(String figi, String quantity, UnitsNano price, String direction, String accountId, String orderType, String instrumentId) {
        this.figi = figi;
        this.quantity = quantity;
        this.price = price;
        this.direction = direction;
        this.accountId = accountId;
        this.orderType = orderType;
        this.orderId = DateTime.now() + figi + accountId; // set unique value like complicated key
        this.instrumentId = instrumentId;
    }

    public String toJsonString() {
        String toReturn = "";
        toReturn += "{";
        toReturn += "\"figi\": \"" + this.figi + "\",";
        toReturn += "\"quantity\": \"" + this.quantity + "\",";

        toReturn += "\"price\": {";
        toReturn += "\"nano\": \"" + this.price.getNano() + "\",";
        toReturn += "\"units\": \"" + this.price.getUnits() + "\"";
        toReturn += "},";

        toReturn += "\"direction\": \"" + this.direction + "\",";
        toReturn += "\"accountId\": \"" + this.accountId + "\",";
        toReturn += "\"orderType\": \"" + this.orderType + "\",";
        toReturn += "\"orderId\": \"" + this.orderId + "\",";
        toReturn += "\"instrumentId\": \"" + this.instrumentId + "\"";
        toReturn += "}";
        return toReturn;
    }

    /**
     * @param direction buy or sell
     * @param orderType limit or market
     * @return
     */
    public static PostOrderRequest newRequest(String figi, String quantity, UnitsNano price, String direction, String accountId, String orderType, String instrumentId) {
        switch (direction) {
            case "buy":
                direction = "ORDER_DIRECTION_BUY";
                break;
            case "sell":
                direction = "ORDER_DIRECTION_SELL";
                break;
            default:
                direction = "ORDER_DIRECTION_UNSPECIFIED";
        }
        switch (orderType) {
            case "limit":
                orderType = "ORDER_TYPE_LIMIT";
                break;
            case "market":
                orderType = "ORDER_TYPE_MARKET";
                break;
            default:
                orderType = "ORDER_TYPE_UNSPECIFIED";
        }
        return new PostOrderRequest(figi, quantity, price, direction, accountId, orderType, instrumentId);
    }
}
