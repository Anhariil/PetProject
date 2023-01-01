package Main.Connectors.Data.Tinkoff.InsrumentService;

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

    public String getUnits() {
        return units;
    }

    public int getNano() {
        return nano;
    }
}
