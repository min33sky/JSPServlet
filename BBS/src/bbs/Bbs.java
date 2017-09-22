package bbs;

public class Bbs {
	private int bbsID;			// 게시판 번호
	private String bbsTitle;	// 게시물 제목
	private String userID;		// 게시자 ID
	private String bbsDate;		// 게시일
	private String bbsContent;	// 내용
	private int bbsAvailable;	// 글의 삭제 유무 확인
	
	
	public Bbs() {
	}


	public Bbs(int bbsID, String bbsTitle, String userID, String bbsDate, String bbsContent, int bbsAvailable) {
		this.bbsID = bbsID;
		this.bbsTitle = bbsTitle;
		this.userID = userID;
		this.bbsDate = bbsDate;
		this.bbsContent = bbsContent;
		this.bbsAvailable = bbsAvailable;
	}


	public int getBbsID() {
		return bbsID;
	}


	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}


	public String getBbsTitle() {
		return bbsTitle;
	}


	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getBbsDate() {
		return bbsDate;
	}


	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}


	public String getBbsContent() {
		return bbsContent;
	}


	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}


	public int getBbsAvailable() {
		return bbsAvailable;
	}


	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	
	
	
}
