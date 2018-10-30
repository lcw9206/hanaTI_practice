<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
boolean flag = false;
String saveId = "";
String loginId = "";
Cookie[] cookies = request.getCookies();
if(cookies.length > 1) {
  for(Cookie cookie : cookies){
	if(cookie.getName().equals("loginId")) {
	  loginId = cookie.getValue();
	  flag = true;
    } else if(cookie.getName().equals("saveId")) {
      saveId = cookie.getValue();
    }
  }  
}
%>    
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
  <div class="container">
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a href="/index.jsp"><i class="fas fa-home fa-2x home-icon"></i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pIntro/intro.jsp">소개</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pGuestBook/guestBook.jsp">방명록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pBoard/board.jsp">게시판</a>
        </li>
        <%
        if(flag) {  
        %>
        <li class="nav-item" id="logout-form" style="color: red">
          <span id="userName" style="color:red"><%=loginId %> 님 환영합니다!</span>
          <input type="button" class="nav-logout" value="로그아웃" onclick='location="/pLogin/logoutAction.jsp;"'>
        </li>  
        <%  
        } else {
        %>
        <li class="nav-item" id="join-site">
          <a class="nav-link" href="/pUser/addUser.jsp">회원가입</a>
        </li> 
        <li class="nav-item" id="login-form">
          <form name="loginForm" action="/pLogin/loginAction.jsp;" method="post">
            <input type="checkbox" name="idSave" value="1">
            <input type="text" class="nav-input" name="userId" id="loginId" placeholder="ID" 
            <%
            if (saveId != "") {
            %>
            value="<%=saveId %>" 
            <% 
            } 
            %>
            >
            <input type="password" class="nav-input" name="userPw" id="loginPw" placeholder="Password">
            <input type="submit" class="nav-input" id="loginBt" value="로그인">
          </form>
        </li>
        <%
        }
        %>
      </ul>
    </div>
  </div>
</nav>