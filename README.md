# SmogAPI

API for Java, that gets data from http://api.gios.gov.pl/pjp-api/rest/
Data is returned as org.json.JSONArray

See http://powietrze.gios.gov.pl/pjp/content/api for details about available data.

Examples:

```java
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
```

This project is not finished yet. There may occur some strange things, like a getting exception when you ask for data from sensor, that not exists.
