package com.api.reader;

import com.api.reader.exceptions.CustomIOException;
import com.api.reader.exceptions.CustomIllegalArgumentException;
import com.api.reader.exceptions.CustomURISyntaxException;
import com.api.reader.exceptions.NoDataReceivedException;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public abstract class HttpGetSenderAndReader implements HttpReader {
    
    private HttpGetValidator httpGetValidator = HttpGetValidator.getInstance();
    
    HttpGetSenderAndReader() {}
    
    @Override
    public JSONArray sendRequest(String request) throws CustomIllegalArgumentException, CustomIOException,
            NoDataReceivedException {
        if (!httpGetValidator.validateStringRequest(request))
            throw new CustomIllegalArgumentException("Given request URL is not valid.");
    
        try {
            return executeRequest(request);
        } catch (IOException e) {
            throw new CustomIllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public JSONArray sendRequest(String url, Map<String, String> keyValue) throws CustomIllegalArgumentException,
            CustomIOException, CustomURISyntaxException, NoDataReceivedException {
        if (!httpGetValidator.validateCollectionRequest(keyValue))
            throw new CustomIllegalArgumentException("Given Map collection is not valid");

        try {
            String request = buildRequest(url, keyValue);
            return executeRequest(request);
        } catch (URISyntaxException e) {
            throw new CustomURISyntaxException(e);
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }

    }

    private String buildRequest(String url, Map<String, String> keyValue) throws CustomURISyntaxException {
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            throw new CustomURISyntaxException(e);
        }
    
        // put key=value into uri
        for (Map.Entry<String, String> entrySet : keyValue.entrySet()) {
            uriBuilder.addParameter(entrySet.getKey(), entrySet.getValue());
        }
        
        return uriBuilder.toString();
    }

    private JSONArray executeRequest(String request) throws IOException, NoDataReceivedException {
        URL url = new URL(request);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line = br.readLine();
        if (line == null || line.length() == 0)
            throw new NoDataReceivedException(request);
        
        if (line.startsWith("[") && line.endsWith("]"))
            return new JSONArray(line);
        else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(new JSONObject(line));
            return jsonArray;
        }
    }
}
