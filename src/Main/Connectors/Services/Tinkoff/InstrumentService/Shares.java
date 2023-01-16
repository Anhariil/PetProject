package Main.Connectors.Services.Tinkoff.InstrumentService;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.SharesResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * This method return
 */
public class Shares extends InstrumentsService {

    private static final String method = "Shares";
    protected SharesResponseMap response;

    /**
     * @param urlType          default test
     * @param method           default POST
     * @param instrumentStatus default INSTRUMENT_STATUS_BASE, also can be INSTRUMENT_STATUS_UNSPECIFIED and INSTRUMENT_STATUS_ALL
     */
    public Shares(String urlType, String method, String instrumentStatus) {
        setUrl(urlType);
        setHeaders();
        setJsonOutputString(instrumentStatus);
        setMethod(method);
    }

    /**
     * Default constructor to test
     */
    public Shares() {
        setUrl("test");
        setHeaders();
        setJsonOutputString("INSTRUMENT_STATUS_BASE");
        setMethod("POST");
    }

//    // need this one?
//    @Override
//    public void getConnection() throws IOException {
//        super.getConnection();
//    }

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    public SharesResponseMap openResponse() {
        return this.response;
    }

    @Override
    public void setJsonOutputString() {
        this.jsonOutputString = "{\"instrumentStatus\": \"INSTRUMENT_STATUS_UNSPECIFIED\"}";
    }

    public void setJsonOutputString(String instrumentStatus) {
        this.jsonOutputString = "{\"instrumentStatus\": \"" + instrumentStatus + "\"}";
    }


    @Override
    public void setResponse(BufferedReader bufferedReader) {
        this.response = new SharesResponseMap(bufferedReader);
    }

    @Override
    public String toString() {
        return "Share api, response: " + this.response.toString();
    }

    public static void main() throws IOException {
        Shares test = new Shares("test", "POST", "INSTRUMENT_STATUS_BASE");
        test.getConnection();
    }
}
