<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<%
  String id = request.getParameter("userId");
  String passwd = request.getParameter("userPw");
  String checkId = request.getParameter("idSave");
 
  if(id == null || passwd == null) {
    return; 
  }
  
  // userDao를 이용한 회원 가입 여부 체크
  DaoFactory factory = (DaoFactory) application.getAttribute("factory");
  UserDao dao = factory.getUserDao();
  User user = dao.certify(id, passwd);
  
  if(user != null) {
    Cookie cookie = new Cookie("loginId", user.getId());
    cookie.setPath("/");
    response.addCookie(cookie);
    
    if(checkId != null) {
      cookie = new Cookie("saveId", user.getId());
      cookie.setPath("/");
      response.addCookie(cookie);
    } else {
  	 Cookie[] cookies = request.getCookies();
  	 for(Cookie list : cookies){
  	    if(list.getName().equals("saveId")) {
  	  	  list.setMaxAge(0);
  	  	  list.setPath("/");
  	      response.addCookie(list);
  	      break;
  	    }
  	  } 
    }
  	response.sendRedirect("/index.jsp");
  } else {
	System.out.println("로그인 실패");
  }
%>