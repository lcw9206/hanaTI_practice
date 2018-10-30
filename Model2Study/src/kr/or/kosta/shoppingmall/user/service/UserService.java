package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.user.domain.User;

/**
 * 고객의 요구사항을 반영한 도메인(개발하고자 하는 업무 영역) 비지니스 메소드 선언 
 * 복잡한 트랜잭션 처리, 예외처리 
 * @author 이철우
 *
 */
public interface UserService {
//	회원 검색
	public User search(String id) throws Exception;
	
//	회원 목록
	public List<User> list() throws Exception;
	
//	로그인
	public User login(String id, String passwd) throws Exception;
}
