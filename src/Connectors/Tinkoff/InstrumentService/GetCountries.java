package Connectors.Tinkoff.InstrumentService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Main Class to do smth
 */
public class GetCountries extends InstrumentsService {

    private static final String method = "GetCountries";

    @Override
    public void setUrl(String type) {
        super.setUrl(type);
        this.URl += method;
    }

    public GetCountries(String urlType, String method ) throws IOException {
        setUrl(urlType);
        setHeaders();
        setJsonInputString();
        setMethod(method);

//        String TinkoffUrlProd = this.URl;
//        String TinkoffUrlSandbox = "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.InstrumentsService/GetCountries";

        //URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
        //URL url = new URL(TinkoffUrlSandbox);
        URL url = new URL(this.URl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //method = POST
        con.setRequestMethod(method);

        /*Map<String,String> parametrs = new HashMap<>();
        parametrs.put("",""); */

        con.setDoOutput(true); //если нужно получить ответ

//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("accept", "application/json");

        for (String str : this.headers.keySet()) {
            con.setRequestProperty(str,this.headers.get(str));
        }

        String jsonInputString = this.jsonOutputString;

        con.setConnectTimeout(5000); // 5 секунд
        con.setReadTimeout(5000);

        // записываем запрос
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        con.disconnect();
        con.connect();

        // write into console status and message
        System.out.println(con.getResponseCode());
        System.out.println(con.getResponseMessage());

        // записываем ответ
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            this.jsonInputString = response.toString();
            System.out.println(this.jsonInputString); // выписываем построчно ответ TODO сделать парсер для каждго коннектора
        }
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer content = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            content.append(inputLine);
//        }
//        in.close();

        con.disconnect();

    }
}
