package Connectors.Data.Tinkoff.InsrumentService.GetAssets;

import Connectors.Data.Mapping;

public class GetAssetsResponseMap extends Mapping {
    private Asset[] assets;

    public GetAssetsResponseMap(String jsonInputString) {
        String notParsedResponse = jsonInputString; // general string wth response
        String subSubStrings[];

        notParsedResponse = notParsedResponse.substring(12, notParsedResponse.length() - 4); // delete {"assets": [ and "}]} in the end
        String[] subStrings = notParsedResponse.split("]},", 0); // get substring wth information about one asset


        this.assets = new Asset[subStrings.length];
        for (int i = 0; i < subStrings.length; i++) {
            AssetsInstrument[] instruments;
            subStrings[i] = subStrings[i].substring(10, subStrings[i].length()); // delete {"uid": "
            // search instruments array
            if (subStrings[i].indexOf("[") != subStrings[i].length() - 1) {
                String subStringInstruments = subStrings[i].substring(subStrings[i].indexOf("[") + 3, subStrings[i].length() - 2); // get info about instrument inside [{}]
                String subStringInstrument[] = subStringInstruments.split("\"uid\": \"", -1); // get substring wth information about one instrument
                instruments = new AssetsInstrument[subStringInstrument.length];
                // set info about every instrument
                for (int j = 0; j < subStringInstrument.length; j++) {
                    Link[] links;
                    // search links array
                    if (subStringInstrument[j].indexOf("[") != subStringInstrument[j].length() - 1) {
                        String subStringLinks = subStringInstrument[j].substring(subStringInstrument[j].indexOf("["), subStringInstrument[j].length() - 1); // get info about links inside []
                        String subStringLink[] = subStringLinks.split("\\{\"instrumentUid\": \"", -1); // get substring wth information about one link
                        links = new Link[subStringLink.length];
                        for (int k = 0; k < subStringLink.length; k++) {
                            subSubStrings = subStringLink[k].split("\",\"", -1); // get 2 pair - "name":"value"
                            String instrumentUid = subSubStrings[0];
                            String linkType = subSubStrings[1].substring(8); // delete type": "
                            links[k] = new Link(instrumentUid, linkType);
                        }
                    } else {
                        links = null; // if array of links is empty
                    }
                    // after keep info about links
                    subStringInstrument[j] = subStringInstrument[j].substring(0, subStringInstrument[j].indexOf("\",\"links\": [")); // delete all about links and last "
                    subSubStrings = subStringInstrument[j].split("\",\"", -1); // get 5 pair - "name":"value"
                    String uid = subSubStrings[0].substring(7); // delete uid": "
                    String figi = subSubStrings[1].substring(8); // delete figi": "
                    String instrumentType = subSubStrings[2].substring(18); // delete instrumentType": "
                    String ticker = subSubStrings[3].substring(10); // delete ticker": "
                    String classCode = subSubStrings[4].substring(13); // delete classCode": "
                    instruments[j] = new AssetsInstrument(figi, ticker, classCode, uid, instrumentType, links);
                }
            } else {
                instruments = null; // if not instrument inside
            }
            // after keep info about instrument
            //System.out.println(subStrings[i]);
            subStrings[i] = subStrings[i].substring(0, subStrings[i].indexOf("\",\"instruments\": [")); // delete all about instruments and last "
            subSubStrings = subStrings[i].split("\",\"", -1); // get 3 pair - "name":"value"
            String uid = subSubStrings[0];
            String type = subSubStrings[1].substring(8); // delete type": "
            String name = subSubStrings[2].substring(8); // delete name": "
            this.assets[i] = new Asset(uid, type, name, instruments);
        }
        //this.instruments = new Instrument[subStrings.length];
    }
}
