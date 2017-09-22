package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO(){
		try{
			String url = "jdbc:mysql://localhost:3306/ajax";
			String id = "root";
			String pw = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> search(String userName){
		String sql = "Select * FROM user WHERE userName LIKE ?";
		ArrayList<User> userList = new ArrayList<>();
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + userName + "%");
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setUserAge(rs.getInt(2));
				user.setUserGender(rs.getString(3));
				user.setUserEmail(rs.getString(4));
				userList.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public int register(User user){
		String sql = "INSERT INTO USER VALUES(?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setInt(2, user.getUserAge());
			pstmt.setString(3, user.getUserGender());
			pstmt.setString(4, user.getUserEmail());
			return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1; // DB Error
	}
}
