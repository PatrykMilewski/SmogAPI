package com.api.reader;


import org.json.JSONObject;

import java.io.IOException;
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
     *  @throws IllegalArgumentException if given request URL in not valid. The check is done by Apache Commons Validator
     *                                      and counting '=' and '&' symbols.
     */

    JSONObject sendRequest(String request) throws IllegalArgumentException, IOException;
    
    /**
     * Posting data though GET HTTP request.
     * @param keyValue key of map entry is a key in get request, value of an entry is a value in get request, eg.:
     *                        key="name", value="john", equals to http://example.com/?name=john
     * @throws IllegalArgumentException if given request URL in not valid. The check is done by Apache Commons Validator
     *                                      and counting '=' and '&' symbols.
     */
    JSONObject sendRequest(String url, Map<String, String> keyValue) throws IllegalArgumentException, IOException;
}
