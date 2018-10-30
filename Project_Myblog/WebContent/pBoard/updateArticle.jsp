<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@page import="java.net.InetAddress"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
String article_id = request.getParameter("article_id");
Article article = dao.read(Integer.parseInt(article_id));
System.out.println(article);
article.setContent(article.getContent().replace("<br>","\r\n"));
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
              <form action="updateArticleAction.jsp" method="post">
                <table class="table-striped table-striped post-table">
                  <thead>
                    <tr>
                      <td>
                        <input type="hidden" name="ip" value="<%=article.getIp() %>">
                        <input type="hidden" name="article_id" value="<%=article.getArticle_id() %>">
                        <input type="hidden" name="level_no" value="0">
                        <input type="hidden" name="order_no" value="0">
                      </td>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="article-text">글제목</td>
                      <td colspan="5">
                        <input type="text" class="article-input" name="subject" value="<%=article.getSubject() %>" autocomplete=”off”>
                      </td>
                    </tr>
                    <tr>
                      <td class="article-text">작성자</td>
                      <td colspan="2">
                        <input type="text" class="article-input" name="writer" value="<%=article.getWriter() %>" readonly>
                      </td>
                      <td class="article-text">비밀번호</td>
                      <td colspan="2">
                          <input type="password" class="article-input" name="passwd" value="<%=article.getPasswd() %>">
                      </td>
                    </tr>
                    <tr>
                      <td colspan="6">
                        <textarea rows="5" cols="99" name="content"><%=article.getContent() %></textarea>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <input type="submit" class="add-article" value="수정하기">
              </form>
            </div>  
          </div>
        </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>

</html>
