package Connectors.Data.Tinkoff.InsrumentService.GetAssets;

import Connectors.Data.Mapping;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class GetAssetsResponseMap extends Mapping {

    private Asset[] assets;

    public GetAssetsResponseMap(BufferedReader bufferedReader) {
        boolean start = false;
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
                //System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("\"assets\": [{")) start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
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
                            instruments.add(new AssetsInstrument(figi, ticker, classCode, uidInstrumen, instrumentType, linksArray));
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
            assetList.removeAll(assetList);
            //System.out.println(response.toString()); // test one
        } catch (Exception e) {
            System.out.println("Errors in \"GetAssetsResponseMap\" " + e);
        }
    }

}
