<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>

<head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
</head>
  
<%    
  String id = request.getParameter("id");
  User inUser = null;
  String dbId = "";
  String confirmId = "";
  // userDao를 이용한 회원 가입 여부 체크
  DaoFactory factory = new JdbcDaoFactory();
  UserDao dao = factory.getUserDao();
  inUser = dao.read(id);
  if(inUser != null) {
  %>
	  <center>중복되는 아이디가 있습니다.</center>
      <button onclick="window.close();">닫기</button>
  <% 
  } else {
  %>  
	  <center>아이디 사용이 가능합니다.</center>
      <button onclick="window.close();">닫기</button>
  <% 
  }
  %>
  <%-- <jsp:forward page="../addUser.jsp"></jsp:forward> --%>
  
  
