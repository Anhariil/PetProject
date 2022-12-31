package Connectors.Data.Tinkoff.InsrumentService.Shares;

import Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

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

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
