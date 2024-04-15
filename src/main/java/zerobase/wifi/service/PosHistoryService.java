package zerobase.wifi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import zerobase.wifi.model.PosHistoryModel;

public class PosHistoryService extends MySqlConnection {

    /**
     * 사용자의 위치 이력을 저장하는 메서드
     */
    public void addPosHistory(double latitude, double longitude) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection(); // MySqlConnection 클래스의 메서드 호출
            String sql = "INSERT INTO posHistory (latitude, longitude, visitTime) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, pstmt, conn);
        }
    }

    /**
     * 사용자의 위치 이력을 조회하는 메서드
     */
    public List<PosHistoryModel> getPosHistory() {
        List<PosHistoryModel> historyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM posHistory ORDER BY visitTime DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                Timestamp visitTime = rs.getTimestamp("visitTime");
                PosHistoryModel history = new PosHistoryModel(id, latitude, longitude, visitTime);
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }
        return historyList;
    }
    
    public void deletePosHistory(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            String sql = "DELETE FROM posHistory WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, pstmt, conn);
        }
    }
}
