package Main.Connectors;

import Main.Connectors.Data.Mapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Connectors {
    protected Map<String, String> headers = new HashMap<>();
    protected String URl;
    protected String jsonOutputString;
    protected String jsonInputString;
    protected String method;
    protected Mapping response;
    protected int responseCode;
    protected String responseMessage;
    protected BufferedReader bufferedResponse;

    private HttpURLConnection con;
    private Class<? extends Mapping> response1;

    public Connectors() {
    }

    public void getConnection() throws IOException {
        URL url = new URL(this.URl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(this.method);
        con.setDoOutput(true); //если нужно получить ответ

        for (String str : this.headers.keySet()) {
            con.setRequestProperty(str, this.headers.get(str));
        }

        String jsonInputString = this.jsonOutputString;

        con.setConnectTimeout(5000); // 5 секунд
        con.setReadTimeout(5000);

        // записываем запрос
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // set response status and message
        this.responseCode = con.getResponseCode();
        this.responseMessage = con.getResponseMessage();

        // write into console status and message
        System.out.println(this.responseCode);
        System.out.println(this.responseMessage);

        // записываем ответ
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            this.setResponse(br); // Call response parsers for everyone child class or set default
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            this.jsonInputString = response.toString();
//            //System.out.println(this.jsonInputString); // выписываем построчно ответ TODO сделать парсер для каждго коннектора
        }

        con.disconnect();
    }

    /**
     * Add two default headers -- <b>Content-Type</b> and <b>accept</b>
     */
    protected void setHeaders() {
        this.headers.put("Content-Type", "application/json");
        this.headers.put("accept", "application/json");

        //TODO move to another class
        this.headers.put("Authorization", "Bearer t.G0DB8pV9U4b3OCqqJ7nn0EUiZrcykNlT0X4i1jNOLGiNonUAXswIE0yIv2_5K_xLTb03_m3RhvKfd1UgZN2xVQ");
    }

    protected void setJsonOutputString() {
        this.jsonOutputString = "{}";
    }

    protected void setJsonOutputString(String input) {
        this.jsonOutputString = input;
    }

    protected void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Main.Connectors{" +
                "response=" + response.toString() +
                '}';
    }

    /**
     * If not 200 OK -- return error code and message
     * Else -- return response.toString()
     *
     * @return code + " " + message
     */
    public String getResponse() {
        if (this.responseCode > 299) {
            return this.responseCode + " " + this.responseMessage;
        } else {
            return this.response.toString();
        }
    }

//    public void setResponse(){
//        try {
//            this.response = new Mapping();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    protected void setResponse(BufferedReader bufferedReader) {
        try {
            this.response = new Mapping(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Mapping openResponse() {
        return this.response;
    }
}
