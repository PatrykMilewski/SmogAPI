package com.api.reader.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class NoDataReceivedException extends Exception implements ExceptionDetailsToJSONParser {
    
    private final static String ERROR_CODE = "4";
    private final static String ERROR_DESCRIPTION = "NoDataReceivedException while sending request.";
    
    private JSONArray errorInJSON = new JSONArray();
    
    {
        JSONObject tempJSON = new JSONObject();
        tempJSON.put("error", ExceptionDetailsToJSONParser.generateErrorDetailsJSON(ERROR_CODE, ERROR_DESCRIPTION,
                this.getMessage()));
        errorInJSON.put(tempJSON);
    }
    
    public NoDataReceivedException(String request) {
        super("After execution of request, no data was received. Request: " + request);
    }
    
    @Override
    public JSONArray getErrorDetailsInJSON() {
        return errorInJSON;
    }
}
