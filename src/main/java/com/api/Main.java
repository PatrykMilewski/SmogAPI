package com.api;


import com.api.reader.WebsitePowietrzeGIOS;
import org.json.JSONArray;

public class Main {
    
    
    public static void main(String args[]) {
        WebsitePowietrzeGIOS websitePowietrzeGIOS = new WebsitePowietrzeGIOS();
        JSONArray stationList = websitePowietrzeGIOS.getStationList();
        JSONArray stationDetails = websitePowietrzeGIOS.getStationDetails(733);
        JSONArray dataFromSensor = websitePowietrzeGIOS.getDataFromSensor(5550);
        JSONArray airQualityIndex = websitePowietrzeGIOS.getAirQualityIndex(733);
    }
    
}
