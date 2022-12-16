package Connectors.Data.Tinkoff.InsrumentService.GetCountries;

import Connectors.Data.Mapping;

public class GetCountriesResponseMap extends Mapping {
    protected final Country[] countries;

    /**
     * Constructor, where we get arrays of Country from response string
     *
     * @param response - json string
     */
    public GetCountriesResponseMap(String response) {
        String notParsedResponse = response; // general string wth response
        String[] subSubStrings; // string wth info about one country
        String alfaTwo;
        String alfaThree;
        String name;
        String nameBrief;

        notParsedResponse = notParsedResponse.substring(14, notParsedResponse.length() - 4); // delete {"countries": [ and "}]} in the end
        String forSearch = "\"},";
        String[] subStrings = notParsedResponse.split(forSearch, 0); // get substring wth information about one country
        this.countries = new Country[subStrings.length];
        for (int i = 0; i < subStrings.length; i++) {
            subStrings[i] = subStrings[i].substring(2); // delete {"
            subSubStrings = subStrings[i].split("\",\"", -1); // get 4 pair - "name":"value"
            alfaTwo = subSubStrings[0].substring(12); // delete +"alfaTwo":"
            alfaThree = subSubStrings[1].substring(13); // delete +"alfaThree":"
            name = subSubStrings[2].substring(8); // delete +"name":"
            nameBrief = subSubStrings[3].substring(13); // delete +"nameBrief":"
            this.countries[i] = new Country(alfaTwo, alfaThree, name, nameBrief);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.countries.length; i++) {
            result += this.countries[i].toString() + "\n";
        }
        return result;
    }

    /**
     * Return length of countries
     *
     * @return
     */
    public int coutriesLength() {
        return this.countries.length;
    }

    public Country[] getCountries() {
        return this.countries;
    }

    public String[] getAllName() {
        String[] stringToReturn = new String[this.coutriesLength()];
        int j = 0;
        for (int i = 0; i < this.coutriesLength(); i++) {
            if (this.getCountries()[i].getName().length() > 0) {
                stringToReturn[j] = this.getCountries()[i].getName();
                j++;
            }
        }
        String[] s = new String[j];
        for (int i = 0; i < j; i++) {
            s[i] = stringToReturn[i];
        }
        return s;
    }

    public String getAlfaThree(String name) {
        for (int i = 0; i < this.coutriesLength(); i++) {
            String nowName = this.countries[i].getName();
            if (nowName == name) {
                return this.countries[i].getAlfaThree();
            }
        }
        return "";
    }
}
