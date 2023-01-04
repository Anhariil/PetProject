package Main.Connectors.Data.Tinkoff.InsrumentService.GetAssets;

public class Link {
    private String instrumentUid;
    private String type;

    public Link() {
    }

    public Link(String instrumentUid, String type) {
        this.instrumentUid = instrumentUid;
        this.type = type;
    }

    public String getInstrumentUid() {
        return instrumentUid;
    }

    public void setInstrumentUid(String instrumentUid) {
        this.instrumentUid = instrumentUid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
