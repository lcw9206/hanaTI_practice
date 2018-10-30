<%@page import="kr.or.kosta.blog.guestbook.domain.GuestBook"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestBookDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
GuestBookDao dao = factory.getGuestBookDao();
List<GuestBook> list = dao.listAll();
String user = "";
%>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>

  <body>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <!-- GuestBook Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <h3 class="guestbook-title-text">방명록</h3>
            <span class="guestBook-inform-text sub-text">하고싶은 말을 자유롭게 등록할 수 있는 방명록</span>
              <%  
              Cookie[] cookies = request.getCookies();  
              for(Cookie cookie : cookies){
                if(cookie.getName().equals("loginId")) {
              %>
                  <form action="/pGuestBook/addGuestBookAction.jsp" method="post">
                    <input type="text" name="user_id" value=<%= cookie.getValue() %> style='display:none'>      
                    <textarea rows="3" cols="56" id="post-box" name="contents"></textarea>
                    <button type="submit" id="add-post">등록</button>
           	      </form>
              <%
                  user = cookie.getValue();
                  break;
                  }
                }
              %>
            <hr>
            <div>
              <table class="table-striped table-striped post-table">
              <thead>
                <tr>
                  <td>작성자</td>
                  <td>내용</td>
                  <td>등록일시</td>
                  <td></td>
                </tr>
              </thead>
              <tbody>
              <%
              for(GuestBook guest: list) {
              %> 
                <tr>
                  <td width=20%><%=guest.getUser_id() %></td>
                  <td width=50%><%=guest.getContents() %></td>
                  <td width=20%><%=guest.getRegdate() %></td>
                  <%
                  if (guest.getUser_id().equals(user)) {
                  %>
                    <td class="delete-button"><button id="post-delete">삭제</button></td>
                  <%
                  } else { 
                  %>
                  <td></td>
                  <% 
                  }
                  %>
                </tr>
              <%
              }
              %>
              </tbody>
              </table>
            </div>  
          </div>
        </div>
      </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>

</html>
