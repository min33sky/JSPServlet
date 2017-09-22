package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try{
			String url = "jdbc:mysql://localhost:3306/bbs";
			String id = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			// mysql 드라이버는 web-inf/lib에 넣는다.
			conn = DriverManager.getConnection(url, id, password);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Login
	 * @param id
	 * @param password
	 * @return
	 */
	public int login(String id, String password){
		String sql = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString(1).equals(password)){
					return 1;	// 로그인 성공
				}
				return 0;		// 로그인 실패
			}
			return -1;			// 해당 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;	// DB 오류
	}
	
	
	public int join(User user){
		String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1; // DB ERROR!!
	}
}
