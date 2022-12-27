package Connectors.Data.Tinkoff.InsrumentService.GetAssets;

import Connectors.Data.Mapping;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetAssetsResponseMap extends Mapping {

    private Asset[] assets;

    public GetAssetsResponseMap(String jsonInputString) {
        String notParsedResponse = jsonInputString; // general string wth response
        String[] subSubStrings;

        notParsedResponse = notParsedResponse.substring(12, notParsedResponse.length() - 4); // delete {"assets": [ and "}]} in the end
        String[] subString = notParsedResponse.split("]}]},", -1); // get substring wth information about assets before one wth links

        int AssetLength = 0;
        for (int i = 0; i < subString.length; i++) {
            String[] subStrings = subString[i].split("]},", -1); // get substring wth information about one asset
            AssetLength += subStrings.length;
        }

        this.assets = new Asset[AssetLength];
        for (int i1 = 0; i1 < subString.length; i1++) {
            String[] subStrings = subString[i1].split("]},", -1); // get substring wth information about one asset
            for (int i = 0; i < subStrings.length; i++) {
                AssetsInstrument[] instruments;
                System.out.println(i1 + "." + i + " " + subStrings[i]);
                subStrings[i] = subStrings[i].substring(10); // delete {"uid": "
                // search instruments array
                if (subStrings[i].indexOf("[") != subStrings[i].length() - 1) {
                    String subStringInstruments = subStrings[i].substring(subStrings[i].indexOf("[") + 3); // get info about instrument inside [{}]
                    String[] subStringInstrument = subStringInstruments.split("\"uid\": \"", -1); // get substring wth information about one instrument
                    instruments = new AssetsInstrument[subStringInstrument.length];
                    // set info about every instrument
                    for (int j = 0; j < subStringInstrument.length; j++) {
                        Link[] links;
                        // search links array
                        if (subStringInstrument[j].indexOf("[") != subStringInstrument[j].length() - 1) {
                            String subStringLinks = subStringInstrument[j].substring(subStringInstrument[j].indexOf("["), subStringInstrument[j].length() - 1); // get info about links inside []
                            String[] subStringLink = subStringLinks.split("\\{\"instrumentUid\": \"", -1); // get substring wth information about one link
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
                subStrings[i] = subStrings[i].substring(0, subStrings[i].indexOf("\",\"instruments\": [")); // delete all about instruments and last "
                subSubStrings = subStrings[i].split("\",\"", -1); // get 3 pair - "name":"value"
                String uid = subSubStrings[0];
                String type = subSubStrings[1].substring(8); // delete type": "
                String name = subSubStrings[2].substring(8); // delete name": "
                this.assets[i] = new Asset(uid, type, name, instruments);
            }
        }
        //this.instruments = new Instrument[subStrings.length];
    }

    public GetAssetsResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
        StringBuilder response = new StringBuilder();
        List<Asset> assetList = new ArrayList<>();

        String uid = "";
        String type = "";
        String name = "";
        List<AssetsInstrument> instruments = new ArrayList<>();
        String uidInstrumen = "";
        String figi = "";
        String instrumentType = "";
        String ticker = "";
        String classCode = "";
        List<Link> links = new ArrayList<>();
        Link[] linksArray;
        String linksType = "";
        String instrumentUid = "";

        int startId = 0;
        int finalId = 5;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string
                response.append(responseLine.trim()); // test response string

                if (nowLine.equalsIgnoreCase("\"assets\": [{")) start = true;
                if (start) {
                    switch (startId) {
                        case 1:
                            uid = nowLine.substring(8, nowLine.indexOf("\","));
                            break;
                        case 2:
                            type = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 3:
                            name = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 4:
                            if (nowLine.indexOf("]") == -1) { // search array of instruments
                                finalId = 12; // set to fill instrument
                            } else {
                                instruments.add(null);
                            }
                            break;
                        // for each instrument
                        case 5:
                            uidInstrumen = nowLine.substring(8, nowLine.indexOf("\","));
                            break;
                        case 6:
                            figi = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 7:
                            instrumentType = nowLine.substring(19, nowLine.indexOf("\","));
                            break;
                        case 8:
                            ticker = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 9:
                            classCode = nowLine.substring(14, nowLine.indexOf("\","));
                            break;
                        case 10:
                            if (nowLine.indexOf("]") == -1) { // search array of links
                                startId = 11; // step over to links
                                finalId = 16; // set to fill links
                            } else {
                                links.add(null);
                            }
                            break;
                        case 11:
                            if (nowLine.indexOf("}]") == -1) { // more than one instrument?
                                startId = 4; // set to new instrument
                                finalId = 12;
                            }
                            linksArray = new Link[links.size()];
                            links.toArray(linksArray); // copy into array
                            links.removeAll(links); // clear for next instrument
                            instruments.add(new AssetsInstrument(figi, ticker, classCode, uid, instrumentType, linksArray));
                            break;
                        // for each list
                        case 12:
                            linksType = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                        case 13:
                            instrumentUid = nowLine.substring(18, nowLine.substring(18).indexOf("\""));
                            break;
                        case 14:
                            if (nowLine.indexOf("}]") == -1) { // more than one link?
                                startId = 11; // set to new link
                                finalId = 16;
                            }
                            links.add(new Link(instrumentUid, linksType));
                            break;
                        case 15: //
                            if (nowLine.indexOf("}]") == -1) { // more than one instrument?
                                startId = 4; // set to new instrument
                                finalId = 12;
                            }
                            linksArray = new Link[links.size()];
                            links.toArray(linksArray); // copy into array
                            links.removeAll(links); // clear for next instrument
                            instruments.add(new AssetsInstrument(figi, ticker, classCode, uid, instrumentType, linksArray));
                            break;
                        case 16: // end of the world
                            if (nowLine.indexOf("}, {") == -1) {
                                start = false; // stop search and read string
                            }
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {

                        AssetsInstrument[] assetsInstruments = new AssetsInstrument[instruments.size()];
                        instruments.toArray(assetsInstruments);
                        assetList.add(new Asset(uid, type, name, assetsInstruments));

                        startId = 0;
                        finalId = 5;
                        instruments.removeAll(instruments);
                    }
                }
            }
            // after set all copy into primary
            this.assets = new Asset[assetList.size()];
            assetList.toArray(this.assets);
            //System.out.println(response.toString()); // test one
        } catch (Exception e) {
            System.out.println("Errors in \"GetAssetsResponseMap\" " + e);
        }
    }


}
