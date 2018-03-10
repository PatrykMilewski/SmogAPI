package com.api.reader.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class CustomIOException extends IOException implements ExceptionDetailsToJSONParser {
    
    private final static String ERROR_CODE = "2";
    private final static String ERROR_DESCRIPTION = "Application fail, IOException while reading data from received JSON.";
    
    private JSONArray errorInJSON = new JSONArray();
    
    {
        JSONObject tempJSON = new JSONObject();
        tempJSON.put("error", ExceptionDetailsToJSONParser.generateErrorDetailsJSON(ERROR_CODE, ERROR_DESCRIPTION,
                this.getMessage()));
        errorInJSON.put(tempJSON);
    }
    
    public CustomIOException(String msg) {
        super(msg);
    }
    
    @Override
    public JSONArray getErrorDetailsInJSON() {
        return errorInJSON;
    }
}
