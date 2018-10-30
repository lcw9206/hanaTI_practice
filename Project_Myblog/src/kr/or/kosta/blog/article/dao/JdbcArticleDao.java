package kr.or.kosta.blog.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.common.Params;
import kr.or.kosta.blog.user.domain.User;

public class JdbcArticleDao implements ArticleDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void createArticle(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		Article resultArticle = null;
		String sql = "INSERT INTO article \r\n" +
					 "VALUES      (ARTICLE_ID_SEQ.NEXTVAL, \r\n" +	
					 "             1, \r\n" +  
					 "             ?, \r\n" +  
					 "             ?, \r\n" +  
					 "             ?, \r\n" +  
					 "             SYSDATE, \r\n" +  
					 "             0, \r\n" +  
					 "             ?, \r\n" +  
					 "             ?, \r\n";
					 // 신규
					 if(article.getArticle_id() == 0) {
						 sql += "             ARTICLE_ID_SEQ.CURRVAL, \r\n" +  
							    "             0, \r\n" +  
							    "             0)";
					 // 답글, 답답글
					 } else {
						 resultArticle = read(article.getArticle_id());
						 // 댓글
						 if(resultArticle.getLevel_no() == 0) {
							 sql += "             ?, \r\n" +
									"             ? + 1, \r\n" +
									"             (SELECT MAX(order_no) + 1 \r\n" +   
								    "              	     FROM article \r\n" + 
								    "             WHERE group_no = ?))"; 
					     // 대댓글
						 } else {
							 sql += "             ?, \r\n" +
									"             ? + 1, \r\n" +
									"             ? + 1)";
							 updateOrderNo(article.getArticle_id());
						 }
					 }
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getIp());
			pstmt.setString(5, article.getPasswd());
			if(article.getArticle_id() != 0) {
				pstmt.setInt(6, resultArticle.getGroup_no());
				pstmt.setInt(7, resultArticle.getLevel_no());
				// 답글
				if(resultArticle.getLevel_no() == 0) {
					pstmt.setInt(8, resultArticle.getGroup_no());
				// 대댓글	
				} else {
					pstmt.setInt(8, resultArticle.getOrder_no());
				}
			} 
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	@Override
	public Article read(int article_id) throws Exception {
		Article article = null;
		
		Connection con =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT article_id, \r\n" + 
			     	 "       writer, \r\n" + 
			     	 "       subject, \r\n" +  
			     	 "       content, \r\n" +  
			     	 "       TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\"') regdate, \r\n" +  
				     "       hitcount, \r\n" +  
				     "       ip, \r\n" +  
				     "       passwd, \r\n" +  
				     "       group_no, \r\n" +  
				     "       level_no, \r\n" +  
				     "       order_no \r\n" +  
				     "FROM   article \r\n" +
				     "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = createArticleRs(rs);
			}
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return article;
	}
	
	@Override
	public void updateArticle(Article article) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + 
				     "SET    subject = ?, \r\n" + 
				     "       passwd = ?, \r\n" + 
				     "       content = ? \r\n" + 
				     "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getPasswd());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, article.getArticle_id());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}

	@Override
	public void updateOrderNo(int article_id) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		Article article = read(article_id);
		System.out.println("update : " + article);
		String sql = "UPDATE article \r\n" + 
				     "SET    order_no = order_no + 1 \r\n" + 
				     "WHERE  group_no = ?\r\n" +
				     "	 AND order_no > (SELECT order_no \r\n" +
				     "	 FROM article \r\n" +
				     "	 WHERE article_id = ?)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getGroup_no());
			pstmt.setInt(2, article_id);
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}
	
	@Override
	public void delete(int articleId) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM article \r\n" + 
				     	  "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		
	}
	
	@Override
	public List<Article> listAll() throws Exception {
		List<Article> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT article_id, \r\n" + 
				     "       board_id, \r\n" + 
				     "       writer, \r\n" + 
				     "       subject, \r\n" +  
				     "       content, \r\n" +  
				     "       TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\"') regdate, \r\n" +
				     "       hitcount, \r\n" +  
				     "       ip, \r\n" +  
				     "       passwd, \r\n" +  
				     "       group_no, \r\n" +  
				     "       level_no, \r\n" +  
				     "       order_no \r\n" +  
				     "FROM   article \r\n" +
				     "ORDER  BY article_id DESC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while(rs.next()) {
				Article article = createArticleRs(rs);
				list.add(article);
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

	private Article createArticleRs(ResultSet rs) throws SQLException{
		Article article = new Article();
		article.setArticle_id(rs.getInt("article_id"));
		article.setWriter(rs.getString("writer"));
		article.setSubject(rs.getString("subject"));
		article.setContent(rs.getString("content"));
		article.setRegdate(rs.getString("regdate"));
		article.setIp(rs.getString("ip"));
		article.setPasswd(rs.getString("passwd"));
		article.setGroup_no(rs.getInt("group_no"));
		article.setLevel_no(rs.getInt("level_no"));
		article.setOrder_no(rs.getInt("order_no"));
		return article;
	}
	
	private Article createByPageRsOne(ResultSet rs) throws SQLException{
		Article article = new Article();
		article.setArticle_id(rs.getInt("article_id"));
		article.setWriter(rs.getString("writer"));
		article.setSubject(rs.getString("subject"));
		article.setRegdate(rs.getString("regdate"));
		article.setIp(rs.getString("ip"));
		return article;
	}

	@Override
	public List<Article> listByPage(int page) throws Exception {
		List<Article> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT article_id, \r\n" + 
					"       writer, \r\n" + 
					"       subject, \r\n" + 
					"       regdate, \r\n" + 
					"       ip, \r\n" + 
					"FROM   (SELECT CeiL(ROWNUM / 10) request_page, \r\n" + 
					"              	article_id, \r\n" + 
					"               writer, \r\n" + 
					"               subject, \r\n" + 
					"               regdate, \r\n" + 
					"               ip \r\n" + 
					"            	FROM   (SELECT article_id, \r\n" + 
					"                              writer, \r\n" + 
					"                              subject," +
					"						 	   TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while(rs.next()) {
				Article article = createByPageRsOne(rs);
				list.add(article);
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

	@Override
	public List<Article> listByPage(int page, int listSize) throws Exception {
		List<Article> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT article_id, \r\n" + 
				"     	    writer, \r\n" + 
				"       	subject, \r\n" + 
				"       	regdate, \r\n" + 
				"       	ip \r\n" + 
				"	 FROM   (SELECT Ceil(ROWNUM / ?) request_page, \r\n" + 
				"               	article_id, \r\n" + 
				"               	writer, \r\n" + 
				"               	subject, \r\n" + 
				"               	regdate, \r\n" + 
				"               	ip \r\n" + 
				"        	 FROM   (SELECT article_id, \r\n" + 
				"                       	writer, \r\n" + 
				"                       	subject, \r\n" + 
				"                       	To_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\"') \r\n" + 
				"                       	regdate, \r\n" + 
				"                       	ip \r\n" + 
				"                			FROM   article \r\n" + 
				"                		 	ORDER  BY regdate DESC)) \r\n" + 
				"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, listSize);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while(rs.next()) {
				Article article = createByPageRsOne(rs);
				list.add(article);
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

	@Override
	public List<Article> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception {
		List<Article> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT article_id, \r\n" +
					"       writer, \r\n" +
					"       subject, \r\n" +
					"       regdate, \r\n" +
					"       ip \r\n" +
					"FROM   (SELECT CeiL(rownum / ?) request_page, \r\n" +
					"               article_id, \r\n" +
					"       		writer, \r\n" +
					"       		subject, \r\n" +
					"       		regdate, \r\n" +
					"       		ip \r\n" +
					"        FROM   (SELECT article_id, \r\n" +
					"       		        writer, \r\n" +
					"				        subject, \r\n" +
					"       				TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\"') regdate, \r\n" +
					"       				ip \r\n" +
					"               FROM    article \r\n";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null){
			switch (searchType) {
				case "title":  
					sql += "WHERE subject LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
				case "content":  
					sql += "WHERE content LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
				case "writer":
					sql += "WHERE writer = ? \r\n";
					break;
			}
		}
		sql += "                ORDER BY article_id DESC)) \r\n" +
		       "WHERE  request_page = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, listSize);
			
			if(searchType != null) {
				pstmt.setString(2, searchValue);
				pstmt.setInt(3, page);
			} else {
				pstmt.setInt(2, page);
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Article>();
			while(rs.next()) {
				Article article = createByPageRsOne(rs);
				list.add(article);
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

	@Override
	public List<Article> listByPage(Params params) throws Exception {
		return listByPage(params.getPage(), params.getListSize(),  params.getSearchType(), params.getSearchValue());
	}

	@Override
	public int countBySearch(String searchType, String searchValue) throws Exception {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT COUNT(article_id) count\r\n" + 
					"FROM   article\r\n";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null){
			switch (searchType) {
				case "title":  
					sql += " WHERE subject LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
				case "content":  
					sql += " WHERE content LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
				case "writer":
					sql += "WHERE  writer = ? \r\n";
					break;
				}
		}
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			// 전체검색이 아닌경우 경우
			if(searchType != null){
				pstmt.setString(1, searchValue);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return count;
	}

	

	@Override
	public int countBySearch(Params params) throws Exception {
		return countBySearch(params.getSearchType(), params.getSearchValue());
	}
}
