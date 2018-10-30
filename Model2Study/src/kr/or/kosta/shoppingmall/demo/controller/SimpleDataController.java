package kr.or.kosta.shoppingmall.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 * 
 * 심플한 데이터는 바로 출력한다.
 * @author 이철우
 *
 */
public class SimpleDataController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
		// 플레인텍스트, xml, json 데이터 바로 출력
		String message = "모델2 기반 웹애플리케이션 개발";

		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(message);
		} catch (IOException e) {
			// 메세지와 원인을 떠넘긴다.
			throw new ServletException(e.getMessage(), e);
		}
		
		return null;
	}

}
