<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="rightcolumn">
    <div class="card" id="login">
      <div>
            
              <form action="<%=application.getContextPath()%>/login.mall" method="get">
                <span>${user }님 하이!</span>
                <input type="submit" value="logout">
              </form>
            
            <%-- <c:if test="${userId }" >
              <form action="<%=application.getContextPath()%>/login.mall" method="get">
                <span>${userId } 님 로그인!</span>
                <input type="submit" value="logout">
                ${requestScope.user.id }
              </form>
            </c:if> --%>
      </div>
    </div>        
    <div class="card">
      <h3>Popular Post</h3>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
    </div>
    <div class="card">
      <h3>Follow Me</h3>
      <p>Some text..</p>
    </div>
  </div>