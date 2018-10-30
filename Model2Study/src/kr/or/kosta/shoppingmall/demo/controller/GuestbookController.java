package kr.or.kosta.shoppingmall.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 * 
 * /hello.mall 요청에 대한 처리 클래스
 * @author 이철우
 *
 */
public class GuestbookController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		// 모델영역의 비즈니스 메소드 호출 및 데이터 반환
		//String message = xxxService.bizMethod();
		String message = "방명록 목록입니다.";
		
		
		mav.addObject("message", message);
		// redirect 시, 정보를 가져가기 위해 session 사용
		request.getSession().setAttribute("message", message);
		// forward
//		mav.setView("/demo/guestbook.jsp");
		//redirect 
		mav.setView("redirect:/model2/demo/guestbook.jsp");
		
//		// RequestDispatcher를 사용하여 뷰(JSP)로 디스패치
//		request.getRequestDispatcher("/demo/hello.jsp").forward(request, response);
		return mav;
	}

}
