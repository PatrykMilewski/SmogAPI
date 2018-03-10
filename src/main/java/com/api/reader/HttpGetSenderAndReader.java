package com.api.reader;

import org.apache.commons.validator.UrlValidator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpGetSenderAndReader implements HttpReader {
    
    private static final HttpGetSenderAndReader INSTANCE = new HttpGetSenderAndReader();
    
    private HttpGetSenderAndReader() {
        throw new AssertionError("This class is expected to be constructed with abstract factory, " +
                "see documentation for available methods.");
    }
    
    public static HttpGetSenderAndReader getInstance() {
        return INSTANCE;
    }

    @Override
    public JSONObject sendRequest(String request) throws IllegalArgumentException, IOException {
        if (!validateStringRequest(request))
            throw new IllegalArgumentException("Given request URL is not valid.");

        return executeRequest(request);
    }

    @Override
    public JSONObject sendRequest(String url, Map<String, String> keyValue) throws IllegalArgumentException, IOException {
        if (!validateCollectionRequest(keyValue))
            throw new IllegalArgumentException("Given Map collection is not valid");

        String request = buildRequest(url, keyValue);

        return executeRequest(request);
    }

    private String buildRequest(String url, Map<String, String> keyValue) {
        StringBuilder sb = new StringBuilder(url);
        if (url.lastIndexOf('?') != url.length()) {
            if (url.lastIndexOf('/') != url.length())
                sb.append("/?");
        }
        else if (url.lastIndexOf('/') != url.length() - 1)
            sb.append("/?");

        for (Map.Entry<String, String> entrySet : keyValue.entrySet()) {
            sb.append(entrySet.getKey());
            sb.append('=');
            sb.append(entrySet.getValue());
            sb.append('&');
        }
        int index = sb.lastIndexOf("&");
        sb.replace(index, index, "");
        return sb.toString();
    }

    private boolean validateStringRequest(String request) {
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(request))
            return false;

        if (request.contains("=") || request.contains("&"))
            return validateKeyValueRequest(request);
        else
            return validateOtherRequest(request);
    }

    private boolean validateCollectionRequest(Map<String, String> keyValue) {
        for (Map.Entry<String, String> entrySet : keyValue.entrySet()) {
            if (!isKeyAndValueValid(entrySet.getKey(), entrySet.getValue()))
                return false;
        }
        return true;
    }

    private boolean isKeyAndValueValid(String key, String value) {
        return key != null && key.length() != 0 && value != null && value.length() != 0;
    }

    private boolean validateKeyValueRequest(String request) {
        String requestCopy = request;
        int countEqualsSymbols = request.length() - requestCopy.replace("=", "").length();
        requestCopy = request;
        int countAmpersandSymbols = request.length() - requestCopy.replace("&", "").length();

        return countEqualsSymbols + 1 == countAmpersandSymbols;
    }

    private boolean validateOtherRequest(String request) {
        return true;
    }

    private JSONObject executeRequest(String request) throws IOException {
        URL url = new URL(request);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line = br.readLine();
        if (line != null && line.length() > 0)
            return new JSONObject(line);

        else
            return new JSONObject();
    }
}
