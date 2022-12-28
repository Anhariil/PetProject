package Connectors.Data.Tinkoff.InsrumentService.Shares;

public class UnitsNano {
    protected String units;
    protected int nano;

    public UnitsNano() {
    }

    /**
     * @param units
     * @param nano
     */
    public UnitsNano(String units, int nano) {
        this.units = units;
        this.nano = nano;
    }
}
