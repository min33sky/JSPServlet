package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
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
	 * 서버의 날짜 가져오기
	 * @return
	 */
	public String getDate(){
		String sql = "SELECT NOW()";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";	// DB 에러
	}
	
	/**
	 * 다음 게시물 ID 가져오기
	 * @return
	 */
	public int getNext(){
		String sql = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1) + 1;
			}
			return 1;	// 처음 등록 글일 경우 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;	// DB 에러
	}
	
	// 글 작성
	public int write(String bbsTitle, String userID, String bbsContent){
		String sql = "INSERT INTO bbs VALUES(?,?,?,?,?,?)";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;	// DB 에러
	}
	
	/**
	 * 게시물 가져오기
	 * getNext()가 다음 게시물 ID를 가져오기 때문에 -1을 해준다.
	 * 1페이지에 10개의 게시물을 출력한다.
	 * @param pageNumber
	 * @return
	 */
	public ArrayList<Bbs> getList(int pageNumber){
		String sql = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 다음 페이지 존재 유무 확인
	 * @param pageNumber
	 * @return
	 */
	public boolean nextPage(int pageNumber){
		String sql = "SELECT * FROM bbs WHERE bbsID < ? AND bbsAvailable = 1";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * 게시물 가져오기
	 * @param bbsID
	 * @return
	 */
	public Bbs getBbs(int bbsID){
		String sql = "SELECT * FROM BBS WHERE bbsID = ?";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 글 수정
	 * @param bbsID
	 * @param bbsTitle
	 * @param bbsContent
	 * @return
	 */
	public int update(int bbsID, String bbsTitle, String bbsContent){
		String sql = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;	// DB 에러
	}

	/**
	 * 글 삭제
	 * @param bbsID
	 * @return
	 */
	public int delete(int bbsID){
		String sql = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;	// DB 에러
	}
	
	
}
