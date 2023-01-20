package Main.Connectors.Data.Tinkoff;

import java.io.BufferedReader;

public class ErrorResponse {
    protected int code;
    protected String message;
    protected String description;

    public ErrorResponse(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ErrorResponse(BufferedReader bufferedReader) {
        try (BufferedReader br = bufferedReader) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                String nowLine = responseLine.trim();
                System.out.println(nowLine); // test now string

                String[] subString = nowLine.split(",", -1);
                code = Integer.valueOf(subString[0].substring(8, subString[0].length()));
                message = subString[1].substring(11, subString[1].length() - 1);
                description = subString[2].substring(15, subString[2].length() - 2);
            }
        } catch (Exception e) {
            System.out.println("Errors in \"ErrorResponse\" " + e);
        }
    }
}
