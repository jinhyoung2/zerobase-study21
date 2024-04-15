package ch11;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDataToDatabase {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/zerobase";
    private static final String USER = "user";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        List<WifiData> wifiDataList = parseDataFromAPI();
        saveDataToDatabase(wifiDataList);
    }

    private static List<WifiData> parseDataFromAPI() {
        List<WifiData> wifiDataList = new ArrayList<>();

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/454469627a646c773131386a634b4e45/xml/TbPublicWifiInfo/1/1000/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(conn.getInputStream());
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("row");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    WifiData wifiData = new WifiData(
                            element.getElementsByTagName("X_SWIFI_MGR_NO").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_WRDOFC").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_MAIN_NM").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_ADRES1").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_ADRES2").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_INSTL_FLOOR").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_INSTL_TY").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_INSTL_MBY").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_SVC_SE").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_CMCWR").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("X_SWIFI_CNSTC_YEAR").item(0).getTextContent()),
                            element.getElementsByTagName("X_SWIFI_INOUT_DOOR").item(0).getTextContent(),
                            element.getElementsByTagName("X_SWIFI_REMARS3").item(0).getTextContent(),
                            Double.parseDouble(element.getElementsByTagName("LAT").item(0).getTextContent()),
                            Double.parseDouble(element.getElementsByTagName("LNT").item(0).getTextContent()),
                            Timestamp.valueOf(element.getElementsByTagName("WORK_DTTM").item(0).getTextContent())
                    );
                    wifiDataList.add(wifiData);
                }
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wifiDataList;
    }

    private static void saveDataToDatabase(List<WifiData> wifiDataList) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zerobase", 
                "user", "1234")) {
            String sql = "INSERT INTO wifiInfo (managerNumber, borough, wifiName, roadAddress, detailAddress, " +
                    "installationFloor, installationType, installationAgency, serviceType, networkType, " +
                    "installationYear, indoorOutdoorType, wifiConnectionEnvironment, latitude, longitude, workDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (WifiData wifiData : wifiDataList) {
                    pstmt.setString(1, wifiData.getManagerNumber());
                    pstmt.setString(2, wifiData.getBorough());
                    pstmt.setString(3, wifiData.getWifiName());
                    pstmt.setString(4, wifiData.getRoadAddress());
                    pstmt.setString(5, wifiData.getDetailAddress());
                    pstmt.setString(6, wifiData.getInstallationFloor());
                    pstmt.setString(7, wifiData.getInstallationType());
                    pstmt.setString(8, wifiData.getInstallationAgency());
                    pstmt.setString(9, wifiData.getServiceType());
                    pstmt.setString(10, wifiData.getNetworkType());
                    pstmt.setInt(11, wifiData.getInstallationYear());
                    pstmt.setString(12, wifiData.getIndoorOutdoorType());
                    pstmt.setString(13, wifiData.getWifiConnectionEnvironment());
                    pstmt.setDouble(14, wifiData.getLatitude());
                    pstmt.setDouble(15, wifiData.getLongitude());
                    pstmt.setTimestamp(16, wifiData.getWorkDate());

                    pstmt.executeUpdate();
                }
                System.out.println("데이터베이스에 성공적으로 데이터를 삽입했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
