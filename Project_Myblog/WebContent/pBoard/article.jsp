<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
String article_id = request.getParameter("article_id");
Article article = dao.read(Integer.parseInt(article_id));

boolean flag = false;
String saveId = "";
String loginId = "";
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies){
  if(cookie.getName().equals("loginId")) {
    loginId = cookie.getValue();
    flag = true;
  } else if(cookie.getName().equals("saveId")) {
    saveId = cookie.getValue();
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
            <p><h3>게시글</h3></p>
              <table class="table-striped table-striped post-table">
              <tbody>
                <tr>
                  <td>글제목</td>
                  <td colspan="5"><%= article.getSubject() %></td>
                </tr>
                <tr>
                  <td>작성자</td>
                  <td colspan="2"><%= article.getWriter() %></td>
                  <td>작성일</td>
                  <td colspan="2"><%= article.getRegdate() %></td>
                </tr>
                <tr>
                  <td>아이피</td>
                  <td colspan="2"><%= article.getIp() %></td>
                  <td>조회수</td>
                  <td colspan="2"><%= article.getHitcount() %></td>
                </tr>
                <tr>
                  <td colspan="6">
                    <%= article.getContent() %>
                  </td>
                </tr>
              </tbody>
              </table>
              <div class="post-buttons">
                <button class="article-list" onclick='location="/pBoard/board.jsp"'>글목록</button>
                <% 
                if(loginId != "") {
                %>
                <button class="reply-article" onclick='location="/pBoard/createArticle.jsp?article_id=<%= article.getArticle_id() %>"'>답글쓰기</button>
                <button class="article-modify" onclick='location="/pBoard/updateArticle.jsp?article_id=<%= article.getArticle_id() %>"'>글수정</button>
                <%
                }
                %>
              </div>
            </div>  
          </div>
        </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>

</html>
