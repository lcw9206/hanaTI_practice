<%@page import="java.net.InetAddress"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%

// 여기서 article id받을 변수 선언 후, 0일 경우에는 신규글. 아닐 경우에는 hidden으로 article 넘버 보내기
String user = "";
String ip = "";

String article_id = request.getParameter("article_id"); 
String[] ipSplit = InetAddress.getLocalHost().getHostAddress().split("\\.");

for(int i = 0; i < ipSplit.length - 1; i++) {
  ip += ipSplit[i] + ".";
}
ip += "xxx";
  
Cookie[] cookies = request.getCookies();  
for(Cookie cookie : cookies){
  if(cookie.getName().equals("loginId")) {
     user = cookie.getValue();
     break;
  }
}
%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>

  <body>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <!-- Post Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <p><h3>게시글 쓰기</h3></p>
              <form action="createArticleAction.jsp" method="post">
                <table class="table-striped table-striped post-table">
                  <thead>
                    <tr>
                      <td>
                      <% 
                      if(article_id != null) {
                      %>
                        <input type="hidden" name="article_id" value="<%=article_id %>">
                      <%
                      }
                      %>
                        <input type="hidden" name="ip" value="<%=ip %>">
                      </td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="article-text">글제목</td>
                      <td colspan="5">
                        <input type="text" class="article-input" name="subject" autocomplete=”off”>
                      </td>
                    </tr>
                    <tr>
                      <td class="article-text">작성자</td>
                      <td colspan="2">
                        <input type="text" class="article-input" name="writer" value="<%=user %>" readonly>
                      </td>
                      <td class="article-text">비밀번호</td>
                      <td colspan="2">
                          <input type="password" class="article-input" name="passwd">
                      </td>
                    </tr>
                    <tr>
                      <td colspan="6">
                        <textarea rows="5" cols="99" name="content"></textarea>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <input type="submit" class="add-article" value="등록하기">
              </form>
            </div>  
          </div>
        </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>

</html>
