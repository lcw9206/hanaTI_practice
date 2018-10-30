<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<jsp:useBean id="article" class="kr.or.kosta.blog.article.domain.Article" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="article"/>

<%
System.out.println("article : " + article);
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
article.setContent(article.getContent().replace("\r\n","<br>"));
dao.createArticle(article);
response.sendRedirect("/pBoard/board.jsp");
%>
