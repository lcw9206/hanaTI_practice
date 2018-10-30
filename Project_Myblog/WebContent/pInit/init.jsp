<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
public void jspInit() {
  DaoFactory factory = new JdbcDaoFactory();
  getServletContext().setAttribute("factory", factory);
}
%>



