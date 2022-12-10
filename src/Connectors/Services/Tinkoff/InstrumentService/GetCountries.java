package Connectors.Services.Tinkoff.InstrumentService;

import Connectors.Data.GetCountriesResponseMap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Main Class to do smth
 */
public class GetCountries extends InstrumentsService {

    private static final String method = "GetCountries";
    protected GetCountriesResponseMap response;

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    public GetCountries(String urlType, String method) {
        setUrl(urlType);
        setHeaders();
        setJsonInputString();
        setMethod(method);
    }

    @Override
    public void getConnection() throws IOException {
        super.getConnection();
        this.response = new GetCountriesResponseMap(this.jsonInputString);
    }

    @Override
    public GetCountriesResponseMap openResponse(){
        return this.response;
    }
}
