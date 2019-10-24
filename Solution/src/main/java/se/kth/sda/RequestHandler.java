package se.kth.sda;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Provides a static method to make simple HTTP Requests using GET
 */
class RequestHandler {
    /**
     * Takes in an url and makes a get request to the given url and returns the body of the response as a string.
     * Returns null if response code is not 200 or some unexpected error occurs with the connection.
     *
     * @param urlString
     * @return
     */
    public String getResponseBodyFromUrl(String urlString) {
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            // Code 200 is the standard response for get requests in the HTTP protocol.
            if (responseCode != 200) {
                System.out.println("Something went wrong with fetching the resource!\n" +
                        "Code: " + responseCode);
                return null;
            } else {
                Scanner responseScanner = new Scanner(connection.getInputStream());
                while (responseScanner.hasNext()) {
                    response = responseScanner.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
