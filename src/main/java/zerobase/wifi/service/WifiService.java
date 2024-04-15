package zerobase.wifi.service;


import zerobase.wifi.dto.WifiInfoDto;
import zerobase.wifi.model.WifiInfoDetailModel;
import zerobase.wifi.model.WifiInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WifiService extends MySqlConnection {
	
	 private WifiApiComponent wifiApiComponent;

	    public WifiService() {
	        this.wifiApiComponent = new WifiApiComponent();
	    }

    /**
     * 와이파이 정보를 저장
     */
	    private boolean add(WifiInfoDto model) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = getConnection(); // MySqlConnection 클래스의 메서드 호출
	            String sql = "INSERT INTO wifiInfo (managerNumber, borough, wifiName, roadAddress, detailAddress, "
	                    + "installationFloor, installationType, installationAgency, serviceType, networkType, installationYear, "
	                    + "indoorOutdoorType, wifiConnectionEnvironment, latitude, longitude, workDate) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	            
	            pstmt = conn.prepareStatement(sql);
	            
	            String managerNumber = model.getManagerNumber();
	            if (managerNumber != null && managerNumber.contains("-")) {
	                managerNumber = managerNumber.replace("-", "");
	            }
	            
	            pstmt.setString(1, managerNumber);
	            pstmt.setString(2, model.getBorough());
	            pstmt.setString(3, model.getWifiName());
	            pstmt.setString(4, model.getRoadAddress());
	            pstmt.setString(5, model.getDetailAddress());
	            pstmt.setString(6, model.getInstallationFloor());
	            pstmt.setString(7, model.getInstallationType());
	            pstmt.setString(8, model.getInstallationAgency());
	            pstmt.setString(9, model.getServiceType());
	            pstmt.setString(10, model.getNetworkType());
	            pstmt.setInt(11, model.getInstallationYear());
	            pstmt.setString(12, model.getIndoorOutdoorType());
	            pstmt.setString(13, model.getWifiConnectionEnvironment());
	            pstmt.setDouble(14, model.getLatitude());
	            pstmt.setDouble(15, model.getLongitude());
	            pstmt.setTimestamp(16, model.getWorkDate());

	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            close(null, pstmt, conn);
	        }
	    }

    /**
     * 와이파이 정보의 목록을 리턴
     */
	    public List<WifiInfoDto> getList(WifiInfoModel parameter) {
	        List<WifiInfoDto> wifiList = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            conn = getConnection();
	            String sql = "SELECT * FROM wifiInfo";
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                WifiInfoDto wifiInfo = new WifiInfoDto();
	                wifiInfo.setManagerNumber(rs.getString("managerNumber"));
	                wifiInfo.setBorough(rs.getString("borough"));
	                wifiInfo.setWifiName(rs.getString("wifiName"));
	                wifiInfo.setRoadAddress(rs.getString("roadAddress"));
	                wifiInfo.setDetailAddress(rs.getString("detailAddress"));
	                wifiInfo.setInstallationFloor(rs.getString("installationFloor"));
	                wifiInfo.setInstallationType(rs.getString("installationType"));
	                wifiInfo.setInstallationAgency(rs.getString("installationAgency"));
	                wifiInfo.setServiceType(rs.getString("serviceType"));
	                wifiInfo.setNetworkType(rs.getString("networkType"));
	                wifiInfo.setInstallationYear(rs.getInt("installationYear"));
	                wifiInfo.setIndoorOutdoorType(rs.getString("indoorOutdoorType"));
	                wifiInfo.setWifiConnectionEnvironment(rs.getString("wifiConnectionEnvironment"));
	                wifiInfo.setLatitude(rs.getDouble("latitude"));
	                wifiInfo.setLongitude(rs.getDouble("longitude"));
	                wifiInfo.setWorkDate(rs.getTimestamp("workDate"));

	                double distance = Math.sqrt(Math.pow((wifiInfo.getLatitude() - parameter.getLatitude()), 2) + Math.pow((wifiInfo.getLongitude() - parameter.getLongitude()), 2));
	                distance *= 111.32;
	                wifiInfo.setDistance(distance);

	                wifiList.add(wifiInfo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rs, pstmt, conn);
	        }

	        wifiList.sort(Comparator.comparingDouble(WifiInfoDto::getDistance));

	        return wifiList.subList(0, Math.min(wifiList.size(), 20));
	    }




    /**
     * 오픈API에서 와이파이 정보를 가져오고,
     * 그 내용을 데이터베이스에 저장하고,
     * 최종적으로 저장한 개수를 리턴
     */
public int saveWifiInfo() {
    int savedCount = 0;

    try {
        int pageIndex = 1;
        String json = wifiApiComponent.getWifiInfoList(pageIndex);

        while (json != null && !json.isEmpty()) {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject wifiInfo = jsonObject.getAsJsonObject("TbPublicWifiInfo");
            JsonArray rows = wifiInfo.getAsJsonArray("row");
            
            for (JsonElement rowElement : rows) {
                JsonObject row = rowElement.getAsJsonObject();
                
                WifiInfoDto wifiInfoDto = new WifiInfoDto();
                wifiInfoDto.setManagerNumber(row.get("X_SWIFI_MGR_NO").getAsString());
                wifiInfoDto.setBorough(row.get("X_SWIFI_WRDOFC").getAsString());
                
                boolean success = add(wifiInfoDto);
                if (success) {
                    savedCount++;
                }
            }
            
            pageIndex++;
            json = wifiApiComponent.getWifiInfoList(pageIndex);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return savedCount;
}


    /**
     * 와이파이 상세 정보 리턴
     */
public WifiInfoDto getDetail(WifiInfoDetailModel parameter) {
    WifiInfoDto wifiInfoDto = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = getConnection(); 
        String sql = "SELECT * FROM wifiInfo WHERE managerNumber = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, parameter.getManagerNumber());
        rs = pstmt.executeQuery();

        if (rs.next()) {
            wifiInfoDto = new WifiInfoDto();
            wifiInfoDto.setManagerNumber(rs.getString("managerNumber"));
            wifiInfoDto.setBorough(rs.getString("borough"));
            wifiInfoDto.setWifiName(rs.getString("wifiName"));
            wifiInfoDto.setRoadAddress(rs.getString("roadAddress"));
            wifiInfoDto.setDetailAddress(rs.getString("detailAddress"));
            wifiInfoDto.setInstallationFloor(rs.getString("installationFloor"));
            wifiInfoDto.setInstallationType(rs.getString("installationType"));
            wifiInfoDto.setInstallationAgency(rs.getString("installationAgency"));
            wifiInfoDto.setServiceType(rs.getString("serviceType"));
            wifiInfoDto.setNetworkType(rs.getString("networkType"));
            wifiInfoDto.setInstallationYear(rs.getInt("installationYear"));
            wifiInfoDto.setIndoorOutdoorType(rs.getString("indoorOutdoorType"));
            wifiInfoDto.setWifiConnectionEnvironment(rs.getString("wifiConnectionEnvironment"));
            wifiInfoDto.setLatitude(rs.getDouble("latitude"));
            wifiInfoDto.setLongitude(rs.getDouble("longitude"));
            wifiInfoDto.setWorkDate(rs.getTimestamp("workDate"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        close(rs, pstmt, conn);
    }

    return wifiInfoDto;
}
}