<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="zerobase.wifi.dto.WifiInfoDto" %>
<%@ page import="zerobase.wifi.model.WifiInfoModel" %>
<%@ page import="zerobase.wifi.service.WifiService" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.sql.*, java.io.*" %>


<%
  
    double latitude = Double.parseDouble(request.getParameter("latitude"));
    double longitude = Double.parseDouble(request.getParameter("longitude"));
    
    String url = "jdbc:mysql://localhost:3306/zerobase";
    String username = "user";
    String password = "1234";

    String sql = "INSERT INTO posHistory (latitude, longitude, visitTime) VALUES (?, ?, ?)";

    try {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(url, username, password);

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, latitude);
        pstmt.setDouble(2, longitude);
        pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        out.println("");
    } catch (Exception e) {
        out.println("오류 발생: " + e.getMessage());
    }
  

    WifiService wifiService = new WifiService();

    WifiInfoModel wifiInfoModel = new WifiInfoModel();
    wifiInfoModel.setLatitude(latitude);
    wifiInfoModel.setLongitude(longitude);

    List<WifiInfoDto> wifiList = wifiService.getList(wifiInfoModel);

    DecimalFormat df = new DecimalFormat("#.#");

    for (WifiInfoDto wifiInfo : wifiList) {
        double distance = Math.sqrt(Math.pow((wifiInfo.getLatitude() - latitude), 2) + Math.pow((wifiInfo.getLongitude() - longitude), 2));
        distance *= 111.32;
        wifiInfo.setDistance(Double.parseDouble(df.format(distance))); // 와이파이 정보에 거리 추가
    }
%>


<!DOCTYPE html>
<html>
<head>
<style>
table {
  width: 100%;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid black;
}

th, td {
  padding: 15px;
  text-align: left;
}

table#t01 tr:nth-child(even) {
  background-color: #eee;
}

table#t01 tr:nth-child(odd) {
 background-color: #fff;
}

table#t01 th {
  background-color: green;
  color: white;
}

form {
  margin-bottom: 20px; 
}

.links, form {
  margin-bottom: 20px; /* Adjust the margin as needed */
}
</style>
</head>
<body>



<h2>와이파이 정보 구하기</h2>

<div class="links">
    <a href="index.jsp">홈</a> | 
    <a href="history.jsp">위치 히스토리 목록</a> | 
    <a href="load-wifi.jsp">OPEN API 위치정보 가져오기</a>
</div>

<form action="location_search.jsp" method="get">
    <label for="latitude">LAT:</label>
    <input type="text" id="latitude" name="latitude" required>
    <label for="longitude">LNT:</label>
    <input type="text" id="longitude" name="longitude" required>
    <button type="submit">내 위치 가져오기</button>
    <button type="submit">근처 WIFI 정보 가져오기</button>
</form>

<table id="t01">
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th> 
        <th>자치구</th> 
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    <% 
        for (WifiInfoDto wifiInfo : wifiList) {
    %>
    <tr>
        <td><%= wifiInfo.getDistance() %></td>
        <td><%= wifiInfo.getManagerNumber() %></td>
        <td><%= wifiInfo.getBorough() %></td>
        <td><%= wifiInfo.getWifiName() %></td>
        <td><%= wifiInfo.getRoadAddress() %></td>
        <td><%= wifiInfo.getDetailAddress() %></td>
        <td><%= wifiInfo.getInstallationFloor() %></td>
        <td><%= wifiInfo.getInstallationAgency() %></td>
        <td><%= wifiInfo.getServiceType() %></td>
        <td><%= wifiInfo.getNetworkType() %></td>
        <td><%= wifiInfo.getInstallationYear() %></td>
        <td><%= wifiInfo.getIndoorOutdoorType() %></td>
        <td><%= wifiInfo.getWifiConnectionEnvironment() %></td>
        <td><%= wifiInfo.getLatitude() %></td>
        <td><%= wifiInfo.getLongitude() %></td>
        <td><%= wifiInfo.getWorkDate() %></td>
    </tr>
    <% } %>
</table>

</body>
</html>
