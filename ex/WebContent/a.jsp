<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제목을 넣으세요</title>
</head>
<body>
	
	<h1>a.jsp</h1>
	<%!
		String id;
		String pw;
	%>
	
	<%
		id = request.getParameter("id");
		pw = request.getParameter("pw");
	%>
	id : <%=id %><br>
	pw : <%=pw %>
 
</body>
</html>