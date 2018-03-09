package com.test.reader;


import org.json.JSONObject;

import java.util.Map;

/**
 * Interface used for sending HTTP GET request and receiving JSON response.
 */

public interface HttpReader {
    
    /**
     * Posting data through GET HTTP request.
      * @param request properly formed GET request, with & as separators and = as value equals, eg.:
     *             name=john&surname=kowalski
     *              user/16/listItems
     */

    JSONObject sendRequest(String request);
    
    /**
     * Posting data though GET HTTP request.
     * @param keyValueRequest key of map entry is a key in get request, value of an entry is a value in get request, eg.:
     *                        key="name", value="john", equals to http://example.com/?name=john
     */
    JSONObject sendRequest(Map<String, String> keyValueRequest);
}
