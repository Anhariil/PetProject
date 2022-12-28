package Connectors.Data;

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

}
