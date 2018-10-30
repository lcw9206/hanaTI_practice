package kr.or.kosta.shoppingmall.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.common.service.ObjectFactory;
import kr.or.kosta.shoppingmall.common.service.ServiceFactory;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

/**
 * 
 * /hello.mall 요청에 대한 처리 클래스
 * @author 이철우
 *
 */
public class UserListController implements Controller{

	private UserService userService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		// 리스너에서 등록된 팩토리를 얻어오는 방법
		ObjectFactory factory = (ObjectFactory)request.getServletContext().getAttribute("objectFactory");
		System.out.println(factory);
		userService = (UserService)factory.getService(UserServiceImpl.class);
		
		List<User> list = null;
		try {
			list = userService.list();
		} catch (Exception e) {
			// 항상 이렇게 구체적으로 디버깅을 하자.
			throw new ServletException("UserService.list() 예외 발생", e);
		}
		
		mav.addObject("list", list);
		mav.setView("/user/list.jsp");
		
		return mav;
	}

}
