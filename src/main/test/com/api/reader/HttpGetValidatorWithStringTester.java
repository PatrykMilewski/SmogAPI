package com.api.reader;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpGetValidatorWithStringTester {
    
    private HttpGetValidator httpGetValidator = HttpGetValidator.getInstance();
    
    @Test
    public void ValidateStringRequestForStringWithoutHeadersCorrectlyDetectsValidUrls() {
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/12/else"));
        assertTrue(httpGetValidator.validateStringRequest("https://example.com/something/12/else"));
        assertTrue(httpGetValidator.validateStringRequest("http://www.example.com/something/12/else"));
        assertTrue(httpGetValidator.validateStringRequest("https://www.example.com/something/12/else"));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/12/else/"));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/12/else/?"));
    }
    
    @Test
    public void ValidateStringRequestForStringWithoutHeadersCorrectlyDetectsNotValidUrls() {
        assertFalse(httpGetValidator.validateStringRequest("protocol://example.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("example.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("www.example.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http:://example.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example..com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example.comm/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http://www.examp le.com/something/12/else"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com\\something\\12\\else"));
    }
    
    @Test
    public void ValidateStringRequestForStringWithHeadersCorrectlyDetectsValidUrls() {
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/?first=value1&second=value2"));
        assertTrue(httpGetValidator.validateStringRequest("https://example.com/something/?first=value1&second=value2"));
        assertTrue(httpGetValidator.validateStringRequest("http://www.example.com/something/?first=value1&second=value2"));
        assertTrue(httpGetValidator.validateStringRequest("https://www.example.com/something/?first=value1&second=value2"));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/?first=val%20ue1"));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/?first=val+ue1"));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/?first=value1&second="));
        assertTrue(httpGetValidator.validateStringRequest("http://example.com/something/thing?first=value1"));
    }
    
    @Test
    public void ValidateStringRequestForStringWithHeadersCorrectlyDetectsNotValidUrls() {
        assertFalse(httpGetValidator.validateStringRequest("protocol://example.com/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("example.com/something/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("www.example.com/something/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http:://example.com/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example..com/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example.comm/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http:///example.com/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://www.examp le.com/something/12/else/?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com\\something\\12\\else\\?first=value1&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com/something/12/else/?first=value1&&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com/something/12/else/?first=value1&second==value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com/something/12/else/?first=value1&=&second=value2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com/something/12/else/?first=value1&second=valu e2"));
        assertFalse(httpGetValidator.validateStringRequest("http://example.com/something/?first=\"value1\""));
    
    }
}
