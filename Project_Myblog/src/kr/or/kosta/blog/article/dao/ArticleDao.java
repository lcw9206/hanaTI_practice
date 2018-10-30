package kr.or.kosta.blog.article.dao;

import java.util.List;

import kr.or.kosta.blog.article.domain.Article;
import kr.or.kosta.blog.common.Params;


/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 김기정
 *
 */
public interface ArticleDao {
	
	public void createArticle(Article article) throws Exception;

	public Article read(int articleId) throws Exception;
	
	public void updateArticle(Article article) throws Exception;
	
	public void updateOrderNo(int article_id) throws Exception;
	
	public void delete(int articleId) throws Exception;
	
	public List<Article> listAll() throws Exception;
	
	/** 선택페이지에 따른 사용자 목록 반환 */	
	public List<Article> listByPage(int page) throws Exception;
	
	/** 선택페이지, 조회 목록개수에 따른 사용자 목록 반환 */	
	public List<Article> listByPage(int page, int listSize) throws Exception;
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<Article> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception;
	
	/** 검색유형, 검색값에 따른 사용자 개수 반환 - 페이징 처리 시 필요 */	
	public int countBySearch(String searchType, String searchValue) throws Exception;
	
	public int countBySearch(Params params) throws Exception;
	
	/** 선택페이지, 조회 목록개수, 검색유형, 검색값에 따른 사용자 목록 반환 */	
	public List<Article> listByPage(Params params) throws Exception;
}
