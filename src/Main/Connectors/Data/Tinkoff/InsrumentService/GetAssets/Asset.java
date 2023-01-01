package Main.Connectors.Data.Tinkoff.InsrumentService.GetAssets;

public class Asset {
    private final String uid;
    private final String type;
    private final String name;
    private final AssetsInstrument[] instruments;

    public Asset(String uid, String type, String name, AssetsInstrument[] instruments) {
        this.uid = uid;
        this.type = type;
        this.name = name;
        this.instruments = instruments;
    }
}
