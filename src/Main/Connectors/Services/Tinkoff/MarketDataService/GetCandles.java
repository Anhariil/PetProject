package Main.Connectors.Services.Tinkoff.MarketDataService;

import Main.Connectors.Data.Mapping;
import Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles.GetCandlesResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetCandles extends MarketDataService {

    private static final String method = "GetCandles";
    protected GetCandlesResponseMap response;

    /**
     * for intervals CANDLE_INTERVAL_1_MIN, CANDLE_INTERVAL_5_MIN, CANDLE_INTERVAL_15_MIN - maximum period is a day
     * <p>
     * CANDLE_INTERVAL_HOUR - maximum period is a week
     * <p>
     * CANDLE_INTERVAL_DAY - maximum period is a year
     * <p>
     * if u need more candles, should do few requests
     *
     * @param url      test or prod
     * @param method   POST
     * @param figi
     * @param from
     * @param to
     * @param interval 0 - CANDLE_INTERVAL_UNSPECIFIED, 1 - CANDLE_INTERVAL_1_MIN, 5 - CANDLE_INTERVAL_5_MIN,
     *                 15 - CANDLE_INTERVAL_15_MIN, 60 - CANDLE_INTERVAL_HOUR, 24 - CANDLE_INTERVAL_DAY
     */
    public GetCandles(String url, String method, String figi, String from, String to, int interval) {
        setUrl(url);
        setHeaders();
        int i = 0;
        String[] inter = {"CANDLE_INTERVAL_UNSPECIFIED", "CANDLE_INTERVAL_1_MIN", "CANDLE_INTERVAL_5_MIN", "CANDLE_INTERVAL_15_MIN", "CANDLE_INTERVAL_HOUR", "CANDLE_INTERVAL_DAY"};
        switch (interval) {
            case 5:
                i = 2;
                break;
            case 15:
                i = 3;
                break;
            case 60:
                i = 4;
                break;
            case 24:
                i = 5;
                break;
        }
        setJsonOutputString(figi, from, to, inter[i]);
        setMethod(method);
    }

    public GetCandles() {
        setUrl("test");
        setHeaders();
        setJsonOutputString();
        setMethod("POST");
    }

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    public GetCandlesResponseMap openResponse() {
        return this.response;
    }

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
                Mapping.LogErrorResponse(bufferedReader);
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
