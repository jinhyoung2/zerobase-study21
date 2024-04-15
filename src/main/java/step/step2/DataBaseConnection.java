package step.step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataBaseConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/zerobase";
    private static final String USER = "user";
    private static final String PASSWORD = "1234";

    public static void test() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if (conn != null) {
                System.out.println("MySQL 데이터베이스 연결 성공");

                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT CURDATE(), CURTIME();");

                while (rs.next()) {
                    String currentDate = rs.getString(1);
                    String currentTime = rs.getString(2);

                    System.out.println("현재 날짜: " + currentDate);
                    System.out.println("현재 시간: " + currentTime);
                }

                conn.close();
            } else {
                System.out.println("MySQL 데이터베이스 연결 실패");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL 드라이버를 찾을 수 없음");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("MySQL 데이터베이스 연결 오류");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
}

