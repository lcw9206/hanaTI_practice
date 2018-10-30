<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<%    
  Cookie[] cookies = request.getCookies();

for(Cookie cookie : cookies){
    if(cookie.getName().equals("loginId")) {
  	    cookie.setMaxAge(0);
  	    cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/index.jsp");    
        break;
    }
  } 
%>


