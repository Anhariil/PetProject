package Connectors.Services.Tinkoff.MarketDataService;

import Connectors.Data.Tinkoff.MarketDataService.GetCandles.GetCandlesResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetCandles extends MarketDataService {

    private static final String method = "GetCandles";
    protected GetCandlesResponseMap response;

    public GetCandles(String url, String method, String figi, String from, String to, String interval) {
        setUrl(url);
        setHeaders();
        setJsonOutputString(figi, from, to, interval);
        setMethod(method);
    }

    public GetCandles() {
        setUrl("test");
        setHeaders();
        setJsonOutputString();
        setMethod("POST");
    }

    // need this one?
//    @Override
//    public void getConnection() throws IOException {
//        super.getConnection();
//    }

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    public GetCandlesResponseMap openResponse() {
        return this.response;
    }

//    private HashMap<String, String> setResponseHashMap(String figi, String from, String to, String interval) {
//        HashMap<String, String> request = new HashMap<String, String>();
//        request.put("instrumentId", figi);
//        request.put("interval", interval);
//        request.put("to", to);
//        request.put("from", from);
//        request.put("figi", figi);
//        return request;
//    }

    /**
     * default request for test positive response
     */
    @Override
    protected void setJsonOutputString() {
        setJsonOutputString("BBG00C4DL9L4", "2022-12-22T11:26:44.177Z", "2022-12-26T11:26:44.177Z", "CANDLE_INTERVAL_HOUR");
    }

    protected void setJsonOutputString(String figi, String from, String to, String interval) {
        this.jsonOutputString = "{";
        this.jsonOutputString += "\"figi\":\"" + figi + "\",";
        this.jsonOutputString += "\"from\":\"" + from + "\",";
        this.jsonOutputString += "\"to\":\"" + to + "\",";
        this.jsonOutputString += "\"interval\":\"" + interval + "\",";
        this.jsonOutputString += "\"instrumentId\":\"" + figi + "\"";
        this.jsonOutputString += "}\"";
    }

    @Override
    protected void setResponse(BufferedReader bufferedReader) {
        if (this.responseCode > 299) {
            try {
                this.response.LogErrorResponse(bufferedReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.response = new GetCandlesResponseMap(bufferedReader);
        }
    }

    public static void main() throws IOException {
        GetCandles test = new GetCandles();
        test.getConnection();
    }
}
