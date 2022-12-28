package Connectors.Data.Tinkoff.InsrumentService.Shares;

public class CurrencyUnitsNano extends UnitsNano {
    protected String currency;

    public CurrencyUnitsNano() {
        super();

    }

    /**
     * @param units
     * @param nano
     * @param currency
     */
    public CurrencyUnitsNano(String units, int nano, String currency) {
        super(units, nano);
        this.currency = currency;
    }
}
