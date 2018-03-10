package com.api.reader.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class CustomURISyntaxException extends URISyntaxException implements ExceptionDetailsToJSONParser {
    
    private final static String ERROR_CODE = "3";
    private final static String ERROR_DESCRIPTION = "Application fail, URISyntaxException while forming URI.";
    
    private JSONArray errorInJSON = new JSONArray();
    
    {
        JSONObject tempJSON = new JSONObject();
        tempJSON.put("error", ExceptionDetailsToJSONParser.generateErrorDetailsJSON(ERROR_CODE, ERROR_DESCRIPTION,
                this.getMessage()));
        errorInJSON.put(tempJSON);
    }
    
    public CustomURISyntaxException(URISyntaxException e) {
        super(e.getInput(), e.getReason(), e.getIndex());
    }
    
    @Override
    public JSONArray getErrorDetailsInJSON() {
        return null;
    }
}
