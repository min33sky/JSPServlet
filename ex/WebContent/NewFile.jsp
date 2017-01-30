<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제목을 넣으세요</title>
</head>
<body>

	<%-- 
		<jsp:include page=""></jsp:include>
	 --%>
	
	<jsp:forward page="a.jsp">
		<jsp:param value="abcde" name="id" />
		<jsp:param value="12345" name="pw" />
	</jsp:forward>

</body>
</html>