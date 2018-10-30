<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>

<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="user"/>
<%
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
RequestDispatcher rd = null;
UserDao dao = factory.getUserDao();

String exist = "0";

String userId = request.getParameter("userId");
String userName = request.getParameter("userName");
String userPasswd = request.getParameter("userPasswd");
String userEmail = request.getParameter("email");
request.setAttribute("userId", userId);

// 아이디 중복 검사 
if(userEmail == null) {
  User confirmUser = dao.read(userId);
  if(confirmUser != null) {
    exist = "1";
  }
  request.setAttribute("exist", exist);
  rd = request.getRequestDispatcher("/pUser/addUser.jsp");
  rd.forward(request,response);
  
// 이메일 중복 검사
} else {
  List<User> userList = new ArrayList<User>();
  userList = dao.listAll();
  
  for(User users : userList) {
    if(users.getEmail().equals(userEmail)) {
      exist = "2";
      
      request.setAttribute("userName", userName);
      request.setAttribute("userPasswd", userPasswd);
      request.setAttribute("userEmail", userEmail);
      request.setAttribute("exist", exist);
      
      rd = request.getRequestDispatcher("/pUser/addUser.jsp");
   	  rd.forward(request,response);
      return;
    }
  }
  dao.create(new User(userId, userName, userPasswd, userEmail));
  
  request.setAttribute("userName", userName);
  request.setAttribute("userEmail", userEmail);
  
  rd = request.getRequestDispatcher("/pUser/addUserOk.jsp");
  rd.forward(request,response);
}
%>

