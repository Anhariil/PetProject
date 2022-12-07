package Connectors.Data;

import java.util.Arrays;

public class GetCountriesResponseMap extends Mapping {
    private Country countries[];

    public GetCountriesResponseMap(String response){
        String notParsedResponse = response; // general string wth response
        String subSubStrings[]; // string wth
        String alfaTwo;
        String alfaThree;
        String name;
        String nameBrief;

        notParsedResponse = notParsedResponse.substring(14,notParsedResponse.length()-4); // delete {"countries": [ and "}]} in the end
        //notParsedResponse.trim(); //delete all " " in string
        String forSearch = "\"},";
        String subStrings[] = notParsedResponse.split(forSearch,0); // get substring wth information about one country
        this.countries = new Country[subStrings.length];
        for (int i = 0; i < subStrings.length; i++) {
            subStrings[i] = subStrings[i].substring(2); // delete {"
            subSubStrings = subStrings[i].split("\",\"",-1); // get 4 pair - "name":"value"
            alfaTwo = subSubStrings[0].substring(12); // delete +"alfaTwo":"
            alfaThree = subSubStrings[1].substring(13); // delete +"alfaThree":"
            name = subSubStrings[2].substring(8); // delete +"name":"
            nameBrief = subSubStrings[3].substring(13); // delete +"nameBrief":"
            this.countries[i] = new Country(alfaTwo,alfaThree,name,nameBrief);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0 ; i < this.countries.length ; i++) {
            result += this.countries[i].toString() + "\n";
        }
        return result;
    }
}
