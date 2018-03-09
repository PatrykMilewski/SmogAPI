package com.test.reader;

import org.json.JSONObject;

import java.util.Map;

public class PowietrzeGiosGovPl implements HttpReader {
    
    private static final PowietrzeGiosGovPl INSTANCE = new PowietrzeGiosGovPl();
    
    private PowietrzeGiosGovPl() {
        throw new AssertionError("This class is expected to be constructed with abstract factory, " +
                "see documentation for available methods.");
    }
    
    public static PowietrzeGiosGovPl getInstance() {
        return INSTANCE;
    }
    
    public JSONObject sendRequest(String request) {
    
    
    
    }
    
    public JSONObject sendRequest(Map<String, String> keyValueRequest) {
        return null;
    }
    
    private boolean validateStringRequest(String request) {
        if (request.contains("=") || request.contains("&"))
            return validateKeyValueRequest(request);
        else
            return validateOtherRequest(request);
    }
}
