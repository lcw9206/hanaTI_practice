package kr.or.kosta.blog.article.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.common.Params;
import kr.or.kosta.blog.factory.DaoFactory;
import kr.or.kosta.blog.factory.JdbcDaoFactory;

public class ArticleDaoTest {

	public static void main(String[] args) {
		DaoFactory factory = new JdbcDaoFactory();
		ArticleDao dao = factory.getArticleDao();
		try {
//			게시글 생성, 리스트 출력 테스트
//			dao.createArticle(new Article(1, "jisung", "jisung의 신규글 제목입니다1.", "테스트다!", "192.168.0.1", "1111", 0, 0, 0));
//			System.out.println("**** 전체목록 테스트 ****");
//			for (Article article : dao.listAll()) {
//				System.out.println(article);				
//			}
//			System.out.println("--------------------");
//			dao.create(testBoard);
//			for (Board board : dao.listAll()) {
//				System.out.println(board);				
//			}
//			System.out.println("--------------------");
//			Board test = dao.read(1);
//			System.out.println(test);
				
//			System.out.println("**** 전체목록 테스트 ****");
//			List<Article> list =  dao.listByPage(3);
//			List<Article> list =  dao.listByPage(1, 15);
//			List<Article> list =  dao.listByPage(1, 15, null, null);
//			List<Article> list =  dao.listByPage(1, 15, "writer", "jisung");
//			List<Article> list =  dao.listByPage(new Params(1, 15, "writer", "jisung"));
//			for (Article article : list) {
//				System.out.println(article);				
//			}
			
//			int count = dao.countBySearch(null, null);
//			int count = dao.countBySearch("writer", "bangry");
//			System.out.println("검색수: " + count);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println();
			SQLException ex = (SQLException)e;
			System.out.println(ex.getErrorCode());
		}
		
		
		

	}

}
