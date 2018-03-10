package com.api.reader.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ExceptionDetailsToJSONParser {
    
    static JSONObject generateErrorDetailsJSON(String errorCode, String errorDescription, String exceptionMessage) {
        JSONObject errorDetails = new JSONObject();
        errorDetails.put("id", errorCode);
        errorDetails.put("description", errorDescription);
        errorDetails.put("exceptionMessage", exceptionMessage);
        return errorDetails;
    }
    
    JSONArray getErrorDetailsInJSON();
    
}
