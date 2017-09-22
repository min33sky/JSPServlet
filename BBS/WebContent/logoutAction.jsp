<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제목을 넣으세요</title>
</head>
<body>
	<%
		session.invalidate();		// 세션에서 제거
	%>
	<script>
		location.href = 'main.jsp';
	</script>
	
</body>
</html>