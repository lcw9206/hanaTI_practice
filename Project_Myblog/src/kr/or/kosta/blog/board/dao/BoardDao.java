package kr.or.kosta.blog.board.dao;

import java.util.List;

import kr.or.kosta.blog.board.domain.Board;

/**
 * Dao 패턴 적용을 위한 인터페이스 선언
 * @author 김기정
 *
 */
public interface BoardDao {
	
	public void create(Board board) throws Exception;
	
	public Board read(int board_id) throws Exception;
	
	public List<Board> listAll() throws Exception;
}
