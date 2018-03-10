package com.api.reader.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomIllegalArgumentException extends IllegalArgumentException implements ExceptionDetailsToJSONParser {
    
    private final static String ERROR_CODE = "1";
    private final static String ERROR_DESCRIPTION = "Application fail, incorrect urls in constants values of a class.";
    
    private JSONArray errorInJSON = new JSONArray();
    
    {
        JSONObject tempJSON = new JSONObject();
        tempJSON.put("error", ExceptionDetailsToJSONParser.generateErrorDetailsJSON(ERROR_CODE, ERROR_DESCRIPTION,
                this.getMessage()));
        errorInJSON.put(tempJSON);
    }
    
    public CustomIllegalArgumentException(String msg) {
        super(msg);
    }
    
    @Override
    public JSONArray getErrorDetailsInJSON() {
        return errorInJSON;
    }
}
