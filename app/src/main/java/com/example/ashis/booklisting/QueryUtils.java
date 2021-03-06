package com.example.ashis.booklisting;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashis on 10/14/2016.
 */
public final class QueryUtils {

    public QueryUtils() {
        throw new AssertionError("No QueryUtils instance for you!");
    }

    public static List<Books> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = makeHTTP(url);

        List<Books> books = extractFromJson(jsonResponse);

        return books;
    }

    private static URL createUrl(String requestUrl) {

        URL url = null;
        try {
            url = new URL(requestUrl);
            try {
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static List<Books> extractFromJson(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        final List<Books> booksArray = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray itemsArray = root.optJSONArray("items");
            if (itemsArray.length() < 1) {
                return null;
            }
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject singleItem = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = singleItem.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                Log.i("title_book", title);

                String author = volumeInfo.getString("authors");
                Log.i("author_book", author);
                String publisher = "No Publisher";
                if (volumeInfo.has("publisher"))
                    publisher = volumeInfo.getString("publisher");
                String website = volumeInfo.getString("previewLink");
                Log.i("url", website);

                booksArray.add(new Books(title, author, publisher, website));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booksArray;
    }

    public static String makeHTTP(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            int serverCode = connection.getResponseCode();
            Log.i("server_Code", String.valueOf(serverCode));
            if (serverCode == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferReader = new BufferedReader(inputReader);
            String line = bufferReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferReader.readLine();
            }
        }
        return output.toString();
    }
}
