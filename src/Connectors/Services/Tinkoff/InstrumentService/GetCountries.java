package Connectors.Services.Tinkoff.InstrumentService;

import Connectors.Data.Tinkoff.InsrumentService.GetCountries.GetCountriesResponseMap;

import java.io.IOException;

/**
 * Main Class to do smth
 */
public class GetCountries extends InstrumentsService {

    private static final String method = "GetCountries";
    protected GetCountriesResponseMap response;

    public GetCountries(String urlType, String method) {
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
        this.response = new GetCountriesResponseMap(this.jsonInputString);
    }

    @Override
    public GetCountriesResponseMap openResponse() {
        return this.response;
    }
}
