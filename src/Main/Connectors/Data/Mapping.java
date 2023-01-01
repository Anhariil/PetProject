package Main.Connectors.Data;

import java.io.BufferedReader;
import java.io.IOException;

public class Mapping {

    public Mapping() {
    }

    public Mapping(BufferedReader bufferedReader) throws IOException {
        try (BufferedReader br = bufferedReader) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response); // выписываем построчно ответ TODO сделать парсер для каждго коннектора
        }
    }

    @Override
    public String toString() {
        return "Zero Mapping";
    }

    public static void LogErrorResponse(BufferedReader bufferedReader) throws IOException {
        boolean start = false;

        String code = "";
        String message = "";
        String description = "";

        int startId = 0;
        int finalId = 4;

        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                if (nowLine.equalsIgnoreCase("{"))
                    start = true;
                if (nowLine.equalsIgnoreCase("}"))
                    start = false; // ignore last string  //TODO search more beautiful decision
                if (start) {
                    switch (startId) {
                        case 0:
                            code = nowLine.substring(8, nowLine.indexOf("\","));
                            break;
                        case 1:
                            message = nowLine.substring(11, nowLine.indexOf("\","));
                            break;
                        case 2:
                            description = nowLine.substring(9, nowLine.indexOf("\","));
                            break;
                    }
                    startId++;
                    if (startId >= finalId) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Errors in \"LogErrorResponse\" " + e);
        }
    }
}
