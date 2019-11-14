package pl.jg.nearby_earthquakes.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//Class used to read data from external API and return them as a String
public class JSONHandler {

    public static String getJson(String url) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream content = urlConnection.getInputStream();
            int read = 0;

            while ((read = content.read()) != -1) {
                stringBuilder.append((char) read);
            }
            content.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
