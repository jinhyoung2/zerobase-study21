package zerobase.wifi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WifiApiComponent {

    /**
     * OpenAPI를 통해서 와이파이 정보의 JSON 문자열을 리턴함
     */
    public String getWifiInfoList(int pageIndex) {
        String apiUrl = "http://openapi.seoul.go.kr:8088/454469627a646c773131386a634b4e45/json/TbPublicWifiInfo/" + pageIndex + "/1000/";

        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
    public int getWifiInfoCount(int pageIndex) {
        String wifiJson = getWifiInfoList(pageIndex);

        int count = 0;
        try {
            JsonObject jsonObject = JsonParser.parseString(wifiJson).getAsJsonObject();
            JsonObject wifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
            JsonArray rows = wifiInfo.getAsJsonArray("row");
            count = rows.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
