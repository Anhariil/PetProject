package Connectors.Services.Tinkoff.InstrumentService;

import Connectors.Data.Tinkoff.InsrumentService.GetAssets.GetAssetsResponseMap;

import java.io.BufferedReader;
import java.io.IOException;

public class GetAssets extends InstrumentsService {

    private static final String method = "GetAssets";
    protected GetAssetsResponseMap response;

    public GetAssets(String urlType, String method) {
        setUrl(urlType);
        setHeaders();
        setJsonOutputString();
        setMethod(method);
    }

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    @Override
    public void getConnection() throws IOException {
        super.getConnection();
        //this.response = new GetAssetsResponseMap(this.bufferedResponse);
    }

    @Override
    public void setResponse(BufferedReader bufferedReader) {
        this.response = new GetAssetsResponseMap(bufferedReader);
    }

    public static void main() throws IOException {
        GetAssets test = new GetAssets("test", "POST");
        test.getConnection();
    }
}
