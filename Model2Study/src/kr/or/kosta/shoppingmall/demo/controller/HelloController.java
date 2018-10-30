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
public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		// 모델영역의 비즈니스 메소드 호출 및 데이터 반환
		//String message = xxxService.bizMethod();
		String message = "모델2 기반 웹애플리케이션 개발";
		
		List<String> list = new ArrayList<String>();
		list.add("Doosan 타이거즈");
		list.add("LG 베어즈");
		list.add("Samsung 트윈즈");
		
		mav.addObject("message", message);
		mav.addObject("list", list);
		
//		// request 컨텍스트 객체에 View에서 필요로 하는 결과정보 저장
//		request.setAttribute("message", message);
//		request.setAttribute("list", list);
		
		mav.setView("/demo/hello.jsp");
		
//		// RequestDispatcher를 사용하여 뷰(JSP)로 디스패치
//		request.getRequestDispatcher("/demo/hello.jsp").forward(request, response);
		return mav;
	}

}
