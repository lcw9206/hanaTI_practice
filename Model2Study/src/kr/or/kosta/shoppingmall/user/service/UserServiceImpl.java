package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.common.dao.JdbcDaoFactory;
import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class UserServiceImpl implements UserService {
	// 현재는 팩토리가 어플리케이션에 종속되어 있다.
	// request가 없는데 어떻게 서블릿 컨텍스트에 접근할 수 있을까?
	// 접근이 불가능하므로 임시방편으로 여기서 선언
	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User search(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() throws Exception {
		
		System.out.println("dao : " + userDao);
		return userDao.listAll();
	}

	@Override
	public User login(String id, String passwd) throws Exception {
		
		System.out.println(id + " : " + passwd);
		return userDao.certify(id, passwd);
		
	}

}
