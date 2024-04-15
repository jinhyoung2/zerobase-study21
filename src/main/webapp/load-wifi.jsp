<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="zerobase.wifi.service.WifiApiComponent" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <style>
        h1 {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }
       
        .home-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
    <title>
        <%-- WifiApiComponent 인스턴스 생성 --%>
        <% WifiApiComponent wifiApiComponent = new WifiApiComponent(); %>
        
        <%-- 정보 개수 가져오기 --%>
        <% int infoCount = wifiApiComponent.getWifiInfoCount(1); %>
        
        <%-- 정보 개수에 따라 메시지 생성 --%>
        <% 
        String message = "";
        if (infoCount > 0) {
            message = infoCount + "개의 WIFI 정보를 정상적으로 저장하였습니다.";
        } else {
            message = "와이파이 정보를 가져오지 못했습니다.";
        }
        %>
        
        <%= message %>
    </title>
</head>
<body>
    <h1>
        <%= message %>
    </h1>
    
    <div class="home-link">
        <a href="index.jsp">홈으로</a>
    </div>
</body>
</html>
