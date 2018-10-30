<%@page import="kr.or.kosta.blog.guestbook.domain.GuestBook"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestBookDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<jsp:useBean id="guestBook" class="kr.or.kosta.blog.guestbook.domain.GuestBook" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="guestBook"/>

<!-- 유효성 검사 -->
<%
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
GuestBookDao dao = factory.getGuestBookDao();
guestBook.setContents(guestBook.getContents().replace("\r\n","<br>"));
dao.create(guestBook);
response.sendRedirect("/pGuestBook/guestBook.jsp");
%>
