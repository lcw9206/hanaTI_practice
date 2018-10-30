package kr.or.kosta.blog.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.board.domain.Board;

public class JdbcBoardDao implements BoardDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Board board) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board \r\n" + 
				     "VALUES      (?, \r\n" + 
				     "            ?, \r\n" +  
				     "            ?, \r\n" +  
				     "            ?)";  
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_id());
			pstmt.setInt(2, board.getCategory());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getDescription());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	@Override
	public Board read(int board_id) throws Exception {
		Board board = null;
		
		Connection con =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT board_id, \r\n" + 
				     "       category, \r\n" + 
				     "       title, \r\n" + 
				     "       description \r\n" + 
				     "FROM   board \r\n" + 
				     "WHERE  board_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = createBoard(rs);
			}
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return board;
	}
	
	@Override
	public List<Board> listAll() throws Exception {
		List<Board> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT board_id, \r\n" + 
				     "       category, \r\n" + 
				     "       title, \r\n" + 
				     "       description \r\n" +  
				     "FROM   board \r\n" +
				     "ORDER BY board_id DESC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board board = createBoard(rs);
				list.add(board);
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
	
	private Board createBoard(ResultSet rs) throws SQLException{
		Board board = new Board();
		board.setBoard_id(rs.getInt("board_id"));
		board.setCategory(rs.getInt("category"));
		board.setTitle(rs.getString("title"));
		board.setDescription(rs.getString("description"));
		return board;
	}
}
