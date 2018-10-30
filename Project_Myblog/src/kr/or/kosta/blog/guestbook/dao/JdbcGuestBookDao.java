package kr.or.kosta.blog.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.guestbook.domain.GuestBook;

public class JdbcGuestBookDao implements GuestBookDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(GuestBook guestBook) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO guestBook \r\n" + 
				     "VALUES      (GUESTBOOK_SEQ.NEXTVAL, \r\n" + 
				     "            ?, \r\n" + 
				     "            ?, \r\n" +  
				     "            SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestBook.getUser_id());
			pstmt.setString(2, guestBook.getContents());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	@Override
	public List<GuestBook> listAll() throws Exception {
		List<GuestBook> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT guestbook_id, \r\n" + 
				     "       user_id, \r\n" + 
				     "       contents, \r\n" + 
				     "       TO_CHAR(regdate, 'YYYY/MM/DD') regdate\r\n" + 
				     "FROM   guestBook \r\n" +
				     "ORDER BY guestbook_id DESC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<GuestBook>();
			while(rs.next()) {
				GuestBook guestBook = createGuestBook(rs);
				list.add(guestBook);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}
	
	private GuestBook createGuestBook(ResultSet rs) throws SQLException{
		GuestBook guestBook = new GuestBook();
		guestBook.setGuestbook_id(rs.getInt("guestbook_id"));
		guestBook.setUser_id(rs.getString("user_id"));
		guestBook.setContents(rs.getString("contents"));
		guestBook.setRegdate(rs.getString("regdate"));
		return guestBook;
	}
}
