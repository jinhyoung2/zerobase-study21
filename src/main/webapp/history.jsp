<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="zerobase.wifi.model.PosHistoryModel" %>
<%@ page import="zerobase.wifi.service.PosHistoryService" %>
<%@ page import="java.text.SimpleDateFormat" %>

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
}

table#t01 th {
  background-color: green;
  color: white;
  text-align: center; /* 테이블 헤더만 가운데 정렬 */
}

table#t01 td {
  text-align: left; /* 셀 내용은 왼쪽 정렬 */
}

table#t01 tr:nth-child(even) {
  background-color: #f2f0f0;
}

table#t01 tr:nth-child(odd) {
  background-color: #ffffff;
}

form {
  margin-bottom: 20px; 
}

.links, form {
  margin-bottom: 20px; /* Adjust the margin as needed */
}

</style>
<script>
function deleteHistory(id) {
    var row = document.getElementById("row_" + id);
    if (confirm('정말로 삭제하시겠습니까?')) {
        // 여기에 삭제 기능을 구현할 JavaScript 코드 작성
        row.remove(); // 해당 행 삭제
    }
    return false; // 폼의 기본 제출 동작 방지
}
</script>
</head>
<body>

<h2>위치 히스토리 목록</h2>

<div class="links">
    <a href="index.jsp">홈</a> | 
    <a href="history.jsp">위치 히스토리 목록</a> | 
    <a href="load-wifi.jsp">OPEN API 위치정보 가져오기</a>
</div>

<table id="t01">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>

<%
PosHistoryService posHistoryService = new PosHistoryService();
List<PosHistoryModel> historyList = posHistoryService.getPosHistory();

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

for (PosHistoryModel history : historyList) {
%>
    <tr id="row_<%= history.getId() %>">
        <td><%= history.getId() %></td>
        <td><%= history.getLatitude() %></td>
        <td><%= history.getLongitude() %></td>
        <td><%= dateFormat.format(history.getVisitTime()) %></td>
        <td class="delete-button" style="text-align: center;">
    <button type="button" onclick="deleteHistory(<%= history.getId() %>)">삭제</button>
</td>
    </tr>
<%
}
%>
</table>

</body>
</html>
