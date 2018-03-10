package com.api.reader;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpGetValidatorWithCollectionTester {
    
    private HttpGetValidator httpGetValidator = HttpGetValidator.getInstance();
    
    @Test
    public void ValidateCollectionRequestDetectsValidUrls() {
        Map<String, String> onePair = new HashMap<>();
        Map<String, String> twoPairs = new HashMap<>();
        Map<String, String> twoPairsWithEmptyValue = new HashMap<>();
        onePair.put("key1", "value1");
        assertTrue(httpGetValidator.validateCollectionRequest(onePair));
    
        twoPairs.put("key1", "value1");
        twoPairs.put("key2", "value2");
        assertTrue(httpGetValidator.validateCollectionRequest(twoPairs));
    
        twoPairsWithEmptyValue.put("key1", "value1");
        twoPairsWithEmptyValue.put("key2", "");
        assertTrue(httpGetValidator.validateCollectionRequest(twoPairsWithEmptyValue));
    }
    
    @Test
    public void ValidateCollectionRequestDetectsNotValidUrls() {
        Map<String, String> onePairEmptyKey = new HashMap<>();
        Map<String, String> twoPairsEmptyKey = new HashMap<>();
        Map<String, String> twoPairsWithEmptyKeyAndValue = new HashMap<>();
        Map<String, String> onePairNullValue = new HashMap<>();
        Map<String, String> onePairWithQuotes = new HashMap<>();
    
        onePairEmptyKey.put("", "value1");
        assertFalse(httpGetValidator.validateCollectionRequest(onePairEmptyKey));
    
        twoPairsEmptyKey.put("", "value1");
        twoPairsEmptyKey.put("", "value2");
        assertFalse(httpGetValidator.validateCollectionRequest(twoPairsEmptyKey));
    
        twoPairsWithEmptyKeyAndValue.put("key1", "value1");
        twoPairsWithEmptyKeyAndValue.put("", "");
        assertFalse(httpGetValidator.validateCollectionRequest(twoPairsWithEmptyKeyAndValue));
    
        onePairNullValue.put("key1", null);
        assertFalse(httpGetValidator.validateCollectionRequest(onePairNullValue));
        
        onePairWithQuotes.put("key1", "\"value1\"");
        assertFalse(httpGetValidator.validateCollectionRequest(onePairWithQuotes));
    }
    
}
