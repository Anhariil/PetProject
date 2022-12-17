package Connectors.Data.Tinkoff.InsrumentService.Shares;

import Connectors.Data.Mapping;
import Connectors.Data.Tinkoff.InsrumentService.Instrument;

public class SharesResponseMap extends Mapping {

    private final Instrument[] instruments;

    /**
     * wronge Api ?
     *
     * @param jsonInputString
     */
    public SharesResponseMap(String jsonInputString) {
        String notParsedResponse = jsonInputString; // general string wth response
        String subSubStrings[];
        String tempSubString[];

        notParsedResponse = notParsedResponse.substring(17, notParsedResponse.length() - 4); // delete {"instruments": [ and "}]} in the end
        String forSearch = "\"},";
        String[] subStrings = notParsedResponse.split(forSearch, 0); // get substring wth information about one instrument
        System.out.println(subStrings[0]); // test one
        this.instruments = new Instrument[subStrings.length];
//        for (int i = 0; i < subStrings.length; i++) {
//            subStrings[i] = subStrings[i].substring(2); // delete {"
//            tempSubString = subStrings[i].split("\"klong\"", 1); // take part before "klong"
//            subSubStrings = tempSubString[1].split("\",\"", -1); // get 4 pair - "name":"value"
//            int a = 0;
//            alfaTwo = subSubStrings[0].substring(12); // delete +"alfaTwo":"
//            alfaThree = subSubStrings[1].substring(13); // delete +"alfaThree":"
//            name = subSubStrings[2].substring(8); // delete +"name":"
//            nameBrief = subSubStrings[3].substring(13); // delete +"nameBrief":"
//            this.countries[i] = new Country(alfaTwo, alfaThree, name, nameBrief);
//        }
//
//        System.out.println(subStrings[0]);
    }

    @Override
    public String toString() {
        return ""; // заглушка
    }
}
