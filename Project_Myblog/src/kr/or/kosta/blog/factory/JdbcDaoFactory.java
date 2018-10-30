package kr.or.kosta.blog.factory;

import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.dao.ArticleDao;
import kr.or.kosta.blog.board.dao.BoardDao;
import kr.or.kosta.blog.guestbook.dao.GuestBookDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {
	private Hashtable<String, Object> daos;
	
	private String[] daoNames = {"kr.or.kosta.blog.user.dao.JdbcUserDao", "kr.or.kosta.blog.guestbook.dao.JdbcGuestBookDao", 
								 "kr.or.kosta.blog.board.dao.JdbcBoardDao", "kr.or.kosta.blog.article.dao.JdbcArticleDao"};
	
	public JdbcDaoFactory() {
		daos = new Hashtable<String, Object>();
		
		for (String className : daoNames) {
			try {
				Object dao = Class.forName(className).newInstance();
				addDataSource(dao);
				daos.put(className, dao);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public UserDao getUserDao() {
		return (UserDao)daos.get("kr.or.kosta.blog.user.dao.JdbcUserDao");
	}
	
	@Override
	public GuestBookDao getGuestBookDao() {
		return (GuestBookDao)daos.get("kr.or.kosta.blog.guestbook.dao.JdbcGuestBookDao");
	}

	@Override
	public BoardDao getBoardDao() {
		return (BoardDao)daos.get("kr.or.kosta.blog.board.dao.JdbcBoardDao");
	}
	
	@Override
	public ArticleDao getArticleDao() {
		return (ArticleDao)daos.get("kr.or.kosta.blog.article.dao.JdbcArticleDao");
	}
	
	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
