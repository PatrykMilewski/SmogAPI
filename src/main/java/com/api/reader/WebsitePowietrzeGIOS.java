package com.api.reader;


import com.api.reader.exceptions.CustomIOException;
import com.api.reader.exceptions.CustomIllegalArgumentException;
import com.api.reader.exceptions.NoDataReceivedException;
import org.json.JSONArray;

public class WebsitePowietrzeGIOS extends HttpGetSenderAndReader {

    private static final String ID_CONST = "&ID&";
    private static final String WEBSITE_URL = "http://api.gios.gov.pl/pjp-api/rest/";
    private static final String STATION_LIST_PATH = WEBSITE_URL + "station/findAll";
    private static final String STATION_PATH = WEBSITE_URL + "station/sensors/" + ID_CONST;
    private static final String SENSOR_PATH = WEBSITE_URL + "data/getdata/" + ID_CONST;
    private static final String AIR_QUALITY_INDEX_PATH = WEBSITE_URL + "aqindex/getIndex/" + ID_CONST;
    
    public JSONArray getStationList() {
        return getData(STATION_LIST_PATH);
    }
    
    public JSONArray getStationDetails(int stationID) {
        return getData(STATION_PATH, stationID);
    }
    
    public JSONArray getDataFromSensor(int sensorID) {
        return getData(SENSOR_PATH, sensorID);
    }
    
    public JSONArray getAirQualityIndex(int stationID) {
        return getData(AIR_QUALITY_INDEX_PATH, stationID);
    }
    
    private JSONArray getData(String path) {
        try {
            return sendRequest(path);
        } catch (CustomIOException | CustomIllegalArgumentException | NoDataReceivedException e) {
            return e.getErrorDetailsInJSON();
        }
    }
    
    private JSONArray getData(String path, int index) {
        path = path.replace(ID_CONST, String.valueOf(index));
        try {
            return sendRequest(path);
        } catch (CustomIOException | CustomIllegalArgumentException | NoDataReceivedException e) {
            return e.getErrorDetailsInJSON();
        }
    }
}
