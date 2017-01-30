package com.javalec.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParameter
 */
@WebServlet(urlPatterns = { "/InitParameter" }, 
			initParams = { @WebInitParam(name = "id", value = "zico"),
							@WebInitParam(name = "pw", value = "1234")})
public class InitParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// getInitParameter() : GenericServlet의 메소드이므로 바로 사용 가능
		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		// Context Parameter는 모든 서블렛이 공유하는 변수이다.
		String gender = getServletContext().getInitParameter("gender");
		
		System.out.println(id);
		System.out.println(pw);
		System.out.println(gender);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
