package Connectors.Data.Tinkoff.InsrumentService.GetAssets;

public class Asset {
    private String uid;
    private String type;
    private String name;
    private AssetsInstrument[] instruments;

    public Asset(String uid, String type, String name, AssetsInstrument[] instruments) {
        this.uid = uid;
        this.type = type;
        this.name = name;
        this.instruments = instruments;
    }
}
