package kr.or.kosta.blog.guestbook.dao;

import java.util.List;

import kr.or.kosta.blog.guestbook.domain.GuestBook;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 김기정
 *
 */
public interface GuestBookDao {
	
	public void create(GuestBook guestBook) throws Exception;
	
	public List<GuestBook> listAll() throws Exception;
}
