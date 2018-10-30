package kr.or.kosta.shoppingmall.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class TodayController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		
		Calendar calendar = Calendar.getInstance();
		String today = String.format("%1$tF %1$tT", calendar);

		// name이 request의 key값으로 들어간다.
		mav.addObject("today", today);
		
		mav.setView("/demo/today.jsp");
		
		return mav;
	}

}
