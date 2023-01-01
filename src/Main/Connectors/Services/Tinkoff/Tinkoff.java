package Main.Connectors.Services.Tinkoff;

import Main.Connectors.Connectors;

public class Tinkoff extends Connectors {
    private static final String UrlTinkoffProd = "https://invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.";
    private static final String UrlTinkoffSandBox = "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.";

    /**
     * This method set url for connector
     *
     * @param type can be <b>prod</b> or <b>test</b>
     * @return url
     */
    protected void setUrl(String type) {
        if (type == "prod") {
            this.URl = UrlTinkoffProd;
        } else this.URl = UrlTinkoffSandBox;
    }
}
