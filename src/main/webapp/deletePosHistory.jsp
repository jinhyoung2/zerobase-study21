<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="zerobase.wifi.service.PosHistoryService" %>

<%
// 요청으로부터 위치 이력의 ID를 받아오기
int id = Integer.parseInt(request.getParameter("id"));

// 삭제
PosHistoryService posHistoryService = new PosHistoryService();
posHistoryService.deletePosHistory(id);

response.sendRedirect("history.jsp");
%>
