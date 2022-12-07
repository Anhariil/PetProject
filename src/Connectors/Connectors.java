package Connectors;

import Connectors.Data.Mapping;

import java.util.HashMap;
import java.util.Map;

public class Connectors {
    protected Map<String,String> headers = new HashMap<>();
    protected String URl;
    protected String jsonOutputString;
    protected String jsonInputString;
    protected String method;
    protected Mapping response;

    /**
     * Add two default headers -- <b>Content-Type</b> and <b>accept</b>
     */
    public void setHeaders() {
        this.headers.put("Content-Type", "application/json");
        this.headers.put("accept", "application/json");

        //TODO move to another class
        this.headers.put("Authorization","Bearer t.G0DB8pV9U4b3OCqqJ7nn0EUiZrcykNlT0X4i1jNOLGiNonUAXswIE0yIv2_5K_xLTb03_m3RhvKfd1UgZN2xVQ");
    }

    public void setJsonInputString() {
        this.jsonOutputString = "{}" ;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Connectors{" +
                "response=" + response.toString() +
                '}';
    }

    public String getResponse(){
        return  response.toString();
    }
}
