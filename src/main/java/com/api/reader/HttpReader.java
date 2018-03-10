package com.api.reader;


import com.api.reader.exceptions.CustomIOException;
import com.api.reader.exceptions.CustomIllegalArgumentException;
import com.api.reader.exceptions.CustomURISyntaxException;
import com.api.reader.exceptions.NoDataReceivedException;
import org.json.JSONArray;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Interface used for sending HTTP GET request and receiving JSON response.
 */

public interface HttpReader {
    
    /**
     * Posting data through GET HTTP request.
     * @param request properly formed URL with optional GET parameters request, eg.:
     *              http://example.com/name=john&surname=kowalski
     *              http://example.com/user/16/listItems
     * @throws CustomIllegalArgumentException if given request URL in not valid. The check is done by Apache Commons Validator
     *                                      and counting '=' and '&' symbols.
     * @throws CustomIOException if there are problems with reading data from given URL, this exception is threw.
     * @return JSONArray that contains data that website responded with. Empty if there was no data.
     */

    JSONArray sendRequest(String request) throws CustomIllegalArgumentException, CustomIOException, NoDataReceivedException;
    
    /**
     * Posting data though GET HTTP request.
     * @param url correct url to a remote directory, where get request will be sent.
     * @param keyValue key of map entry is a key in get request, value of an entry is a value in get request, eg.:
     *                        key="name", value="john", equals to http://example.com/?name=john
     * @throws CustomIllegalArgumentException if given request URL in not valid. The check is done by Apache Commons Validator
     *                                      and counting '=' and '&' symbols.
     * @throws CustomIOException if there are problems with reading data from given URL, this exception is threw.
     * @throws CustomURISyntaxException see {@link URISyntaxException}, caused by url param.
     * @return JSONArray that contains data that website responded with. Empty if there was no data.
     */
    JSONArray sendRequest(String url, Map<String, String> keyValue) throws CustomIllegalArgumentException,
            CustomIOException, CustomURISyntaxException, NoDataReceivedException;
}
