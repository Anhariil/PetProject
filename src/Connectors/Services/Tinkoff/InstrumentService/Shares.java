package Connectors.Services.Tinkoff.InstrumentService;

import Connectors.Data.Tinkoff.InsrumentService.Shares.SharesResponseMap;

import java.io.IOException;

/**
 * Main Class to do smth
 */
public class Shares extends InstrumentsService {

    private static final String method = "Shares";
    protected SharesResponseMap response;

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    public Shares(String urlType, String method) {
        setUrl(urlType);
        setHeaders();
        setJsonOutputString();
        setMethod(method);
    }

    @Override
    public void getConnection() throws IOException {
        super.getConnection();
        this.response = new SharesResponseMap(this.jsonInputString);
    }

    @Override
    public SharesResponseMap openResponse() {
        return this.response;
    }

    public static void main() throws IOException {
        Shares test = new Shares("test", "POST");
        test.getConnection();
    }

    @Override
    public void setJsonOutputString() {
        this.jsonOutputString = "{\"instrumentStatus\": \"INSTRUMENT_STATUS_UNSPECIFIED\"}";
    }
}
