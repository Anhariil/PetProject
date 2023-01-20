package Main.Connectors.Data.Tinkoff.OrdersService;

import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.DateTime;

public class ReplaceOrderRequest {

    protected String accountId;
    protected String orderId;
    protected String idempotencyKey;
    protected String quantity;
    protected UnitsNano price;
    protected String priceType;

    /**
     * @param accountId
     * @param orderId
     * @param idempotencyKey
     * @param quantity
     * @param price
     * @param priceType      PRICE_TYPE_POINT for futures and bonds
     *                       PRICE_TYPE_CURRENCY	for other (default value)
     */
    public ReplaceOrderRequest(String accountId, String orderId, String idempotencyKey, String quantity, UnitsNano price, String priceType) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.idempotencyKey = idempotencyKey;
        this.quantity = quantity;
        this.price = price;
        this.priceType = priceType;
    }

    /**
     * @param accountId
     * @param orderId
     * @param quantity
     * @param price
     * @param priceType <b>point</b> for futures and bonds, <b>currency</b> for other
     * @return
     */
    public static ReplaceOrderRequest newReplaceOrder(String accountId, String orderId, String quantity, UnitsNano price, String priceType) {
        String idempotencyKey = DateTime.now() + accountId;
        idempotencyKey = idempotencyKey.substring(0, 35); // max length 36 symbols
        switch (priceType) {
            case "point":
                priceType = "PRICE_TYPE_POINT";
                break;
            case "currency":
                priceType = "PRICE_TYPE_CURRENCY";
                break;
            default:
                priceType = "PRICE_TYPE_UNSPECIFIED";
        }
        return new ReplaceOrderRequest(accountId, orderId, idempotencyKey, quantity, price, priceType);
    }

    public String toJsonString() {
        String toReturn = "";
        toReturn += "{";
        toReturn += "\"accountId\": \"" + this.accountId + "\",";
        toReturn += "\"orderId\": \"" + this.orderId + "\",";
        toReturn += "\"idempotencyKey\": \"" + this.idempotencyKey + "\",";
        toReturn += "\"quantity\": \"" + this.quantity + "\",";

        toReturn += "\"price\": {";
        toReturn += "\"nano\": \"" + this.price.getNano() + "\",";
        toReturn += "\"units\": \"" + this.price.getUnits() + "\"";
        toReturn += "},";

        toReturn += "\"priceType\": \"" + this.priceType + "\"";
        toReturn += "}";
        return toReturn;
    }
}
