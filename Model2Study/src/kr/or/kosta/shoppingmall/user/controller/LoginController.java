package kr.or.kosta.shoppingmall.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
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
public class LoginController implements Controller{

	private UserService userService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		// 리스너에서 등록된 팩토리를 얻어오는 방법
		ServiceFactory factory = (ServiceFactory) request.getServletContext(). getAttribute("serviceFactory");
		// 생성되어있는 UserServiceImpl을 불러와 사용
		userService = (UserService) factory.getService(UserServiceImpl.class);

		User user = null;
		try {
			user = userService.login(request.getParameter("userid"), request.getParameter("userpw"));
		} catch (Exception e) {
			// 항상 이렇게 구체적으로 디버깅을 하자.
			throw new ServletException("UserService.certify 예외 발생", e);
		}
		Cookie cookie = new Cookie("loginId", request.getParameter("userid"));
		response.addCookie(cookie);
		mav.addObject("userId", request.getParameter("userid"));
		mav.setView("/index.jsp");
		
		return mav;
	}

}
