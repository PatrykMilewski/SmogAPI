package com.api.reader;


import org.apache.commons.validator.UrlValidator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class HttpGetValidator {
    
    private final static HttpGetValidator INSTANCE = new HttpGetValidator();
    
    private HttpGetValidator() {}
    
    /**
     * Used to get instance of HttpGetValidator class, since this class is singleton.
     * @return instance of a class.
     */
    static HttpGetValidator getInstance() {
        return INSTANCE;
    }
    
    boolean validateStringRequest(String request) {
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(request))
            return false;
        
        if (request.contains("=") || request.contains("&"))
            return validateCollectionRequest(request);
        else
            return validateOtherRequest(request);
    }
    
    boolean validateCollectionRequest(Map<String, String> keyValue) {
        for (Map.Entry<String, String> entrySet : keyValue.entrySet()) {
            if (!isKeyAndValueValid(entrySet.getKey(), entrySet.getValue()))
                return false;
        }
        return true;
    }
    
    // can't see any variants for validation in this case, but it stays for future
    private boolean validateOtherRequest(String request) {
        return true;
    }
    
    private boolean isKeyAndValueValid(String keyValue) {
        String key = keyValue.substring(0, keyValue.indexOf("="));
        String value = keyValue.substring(keyValue.indexOf("=") + 1, keyValue.length());
        return isKeyAndValueValid(key, value);
    }
    
    private boolean isKeyAndValueValid(String key, String value) {
        if (key == null || key.length() == 0 || value == null)
            return false;
        
        // may cause trouble for GC on big tasks, but for small tasks it's fine
        try {
            URI uri = new URI(key);
            uri = new URI(value);
        } catch (URISyntaxException e) {
            return false;
        }
        
        return true;
    }
    
    private boolean validateCollectionRequest(String request) {
        String requestCopy = request;
        int countEqualsSymbols = request.length() - requestCopy.replace("=", "").length();
        requestCopy = request;
        int countAmpersandSymbols = request.length() - requestCopy.replace("&", "").length();
        
        if (countEqualsSymbols != countAmpersandSymbols + 1)
            return false;
    
        String headers = request.substring(request.indexOf("?") + 1, request.length());
        return validateHeadersString(headers);
    }
    
    private boolean validateHeadersString(String headers) {
        
        List<String> keyValueList = Arrays.asList(headers.split("&"));
        for (String element : keyValueList) {
            if (!element.contains("=") && element.length() == 0)
                return false;
            else if (!isKeyAndValueValid(element))
                return false;
        }
        return true;
    }
    
}
