package step.step2;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/zerobase"; 
    private static final String USER = "user";
    private static final String PASSWORD = "1234"; 

    public static void insertData(String managerNumber, String borough, String wifiName, String roadAddress,
                                  String detailAddress, String installationFloor, String installationType,
                                  String installationAgency, String serviceType, String networkType, int installationYear,
                                  String indoorOutdoorType, String wifiConnectionEnvironment, double latitude,
                                  double longitude, String workDate) {

        String query = "INSERT INTO wifiInfo (managerNumber, borough, wifiName, roadAddress, detailAddress, "
                + "installationFloor, installationType, installationAgency, serviceType, networkType, installationYear, "
                + "indoorOutdoorType, wifiConnectionEnvironment, latitude, longitude, workDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, managerNumber);
            pstmt.setString(2, borough);
            pstmt.setString(3, wifiName);
            pstmt.setString(4, roadAddress);
            pstmt.setString(5, detailAddress);
            pstmt.setString(6, installationFloor);
            pstmt.setString(7, installationType);
            pstmt.setString(8, installationAgency);
            pstmt.setString(9, serviceType);
            pstmt.setString(10, networkType);
            pstmt.setInt(11, installationYear);
            pstmt.setString(12, indoorOutdoorType);
            pstmt.setString(13, wifiConnectionEnvironment);
            pstmt.setDouble(14, latitude);
            pstmt.setDouble(15, longitude);
            pstmt.setString(16, workDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

