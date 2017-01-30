package com.oracle.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	
//	String url ="jdbc:oracle:thin:@localhost:1521:xe";
//	String user = "scott05";
//	String password = "tiger05";
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	DataSource dataSource;
	
	public MemberDao() {
		try {
			// �삤�씪�겢 �뱶�씪�씠踰� 濡쒕뱶
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 紐⑤뱺 硫ㅻ쾭 媛��졇�삤湲�
	 */
	public List<MemberVo> getMemberAll(){
		List<MemberVo> list = new ArrayList<>();
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("Select * From member Order by id");
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String phone = rs.getString(4);
				list.add(new MemberVo(id, pw, name, phone));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/*
	 * �쉶�썝 異붽��븯湲�
	 */
	public void insertMember(String id, String pw, String name, String phone){
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("Insert into member Values (?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, pw);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
