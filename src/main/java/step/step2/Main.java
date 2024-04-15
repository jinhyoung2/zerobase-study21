package step.step2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        try {
            String apiUrl = "http://openapi.seoul.go.kr:8088/454469627a646c773131386a634b4e45/json/TbPublicWifiInfo/2/1000/";
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject wifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
            JsonArray rows = wifiInfo.getAsJsonArray("row");
            for (JsonElement rowElement : rows) {
                JsonObject row = rowElement.getAsJsonObject();
                //저장
                String managerNumber = row.get("X_SWIFI_MGR_NO").getAsString().replace("-", "");
                String borough = row.get("X_SWIFI_WRDOFC").getAsString();
                String wifiName = row.get("X_SWIFI_MAIN_NM").getAsString();
                String roadAddress = row.get("X_SWIFI_ADRES1").getAsString();
                String detailAddress = row.get("X_SWIFI_ADRES2").getAsString();
                String installationFloor = ""; // API 응답에는 해당 정보 없음
                String installationType = row.get("X_SWIFI_INSTL_TY").getAsString();
                String installationAgency = row.get("X_SWIFI_INSTL_MBY").getAsString();
                String serviceType = row.get("X_SWIFI_SVC_SE").getAsString();
                String networkType = row.get("X_SWIFI_CMCWR").getAsString();
                int installationYear = Integer.parseInt(row.get("X_SWIFI_CNSTC_YEAR").getAsString());
                String indoorOutdoorType = row.get("X_SWIFI_INOUT_DOOR").getAsString();
                String wifiConnectionEnvironment = row.get("X_SWIFI_REMARS3").getAsString();
                double latitude = Double.parseDouble(row.get("LAT").getAsString());
                double longitude = Double.parseDouble(row.get("LNT").getAsString());
                String workDate = row.get("WORK_DTTM").getAsString();

                //삽입
                
                DatabaseUtil.insertData(managerNumber, borough, wifiName, roadAddress, detailAddress, installationFloor,
                        installationType, installationAgency, serviceType, networkType, installationYear,
                        indoorOutdoorType, wifiConnectionEnvironment, latitude, longitude, workDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
