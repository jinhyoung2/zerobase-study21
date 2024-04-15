<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="zerobase.wifi.dto.WifiInfoDto" %>
<%@ page import="zerobase.wifi.model.WifiInfoModel" %>
<%@ page import="zerobase.wifi.service.WifiService" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>

<%
    //  위도와 경도 가져오기
    double latitude = Double.parseDouble(request.getParameter("latitude"));
    double longitude = Double.parseDouble(request.getParameter("longitude"));

    WifiService wifiService = new WifiService();

    WifiInfoModel wifiInfoModel = new WifiInfoModel();
    wifiInfoModel.setLatitude(latitude);
    wifiInfoModel.setLongitude(longitude);

    List<WifiInfoDto> wifiList = wifiService.getList(wifiInfoModel);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: green;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>주변 와이파이 정보</h2>

<table>
    <tr>
        <th>와이파이명</th>
        <th>거리(Km)</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
    </tr>
    <% 
        //  거리를 소수점 첫째자리까지 표시하도록 설정
        DecimalFormat df = new DecimalFormat("#.#");
        
        // 위도와 경도를 이용하여 각 와이파이의 거리 계산 및 출력
        for (WifiInfoDto wifiInfo : wifiList) {
            double distance = Math.sqrt(Math.pow((wifiInfo.getLatitude() - latitude), 2) + Math.pow((wifiInfo.getLongitude() - longitude), 2));
            // 거리를 킬로미터 단위로 변환
            distance *= 111.32;
    %>
    <tr>
        <td><%= wifiInfo.getWifiName() %></td>
        <td><%= df.format(distance) %></td>
        <td><%= wifiInfo.getRoadAddress() %></td>
        <td><%= wifiInfo.getDetailAddress() %></td>
        <td><%= wifiInfo.getInstallationFloor() %></td>
        <td><%= wifiInfo.getInstallationAgency() %></td>
        <td><%= wifiInfo.getServiceType() %></td>
        <td><%= wifiInfo.getNetworkType() %></td>
        <td><%= wifiInfo.getInstallationYear() %></td>
        <td><%= wifiInfo.getIndoorOutdoorType() %></td>
        <td><%= wifiInfo.getWifiConnectionEnvironment() %></td>
    </tr>
    <% } %>
</table>

</body>
</html>
