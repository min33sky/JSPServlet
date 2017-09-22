<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="bbs" class="bbs.Bbs"/>
<jsp:setProperty property="bbsTitle" name="bbs"/>
<jsp:setProperty property="bbsContent" name="bbs"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제목을 넣으세요</title>
</head>
<body>
	<% 
		// 세션에서 아이디 값 가져오기. (로그인 유무 확인)
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		
		// 로그인 되어 있지않다면 로그인 화면으로 이동
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		}else{
			
			// 입력값이 완전하지 않을 경우 경고
			if(bbs.getBbsTitle() == null || bbs.getBbsContent() == null){
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				
			}else{
				
				BbsDAO bbsDao = new BbsDAO();
				int result = bbsDao.write(bbs.getBbsTitle(), userID, bbs.getBbsContent());				
				if(result == 1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'bbs.jsp'");
					script.println("</script>");
				}else if(result == -1){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기 실패....')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
			
			
		}
	
		
	
	%>
 
</body>
</html>