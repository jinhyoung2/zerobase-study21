<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  width: 100%;
  border-collapse: collapse;
}

#t02 td {
  text-align: center; 
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

<form action="location_search.jsp" method="post">
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
</table>

<table id="t02">
  <tr>
    <td>위치 정보를 입력한 후에 조회해 주세요.</td>
  </tr>
</table>

</body>
</html>
