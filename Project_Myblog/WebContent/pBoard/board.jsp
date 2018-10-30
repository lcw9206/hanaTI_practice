<%@page import="kr.or.kosta.blog.common.PageBuilder"%>
<%@page import="kr.or.kosta.blog.common.Params"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.factory.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.factory.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
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

// 페이지당 보여지는 목록수 설정
int listSize = 5;
//페이지당 보여지는 페이지수 설정
int pageSize = 3;

// 선택페이지 수신
String requestPage = request.getParameter("page");
if(requestPage == null || requestPage.equals("")){
  requestPage = "1";
}

// 검색 요청일 경우 파라메터 수신
String searchType = request.getParameter("searchType");
String searchValue = request.getParameter("searchValue");
if(searchType == null || searchType.equals("")){
  searchType = null;
  searchValue = null;
}

// 요청파라메터 포장
Params params = new Params(Integer.parseInt(requestPage), listSize, pageSize, searchType, searchValue);
DaoFactory factory = new JdbcDaoFactory();
ArticleDao dao = factory.getArticleDao();
List<Article> list = dao.listByPage(params);

// 페이징 처리에 필요한 검색 개수 DB조회
int rowCount = dao.countBySearch(params);

// PageBuilder를 이용하여 페이징 계산
PageBuilder pageBuilder = new PageBuilder(params, rowCount);
pageBuilder.build();
%>

<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/css/basic.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" type="text/css" href="/css/pagination.css">
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>
  
  <body>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <!-- Main Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 col-md-10 mx-auto">
            <p><h3>게시판</h3></p><hr>
            <table class="table-striped table-hover board-table">
              <thead>
                <tr>
                  <td>번호</td>
                  <td>제목</td>
                  <td>작성자</td>
                  <td>작성일</td>
                  <td>IP</td>
                  <td>조회수</td>
                </tr>
              </thead>
              <tbody>
                <%
                for(int i=0; i<list.size(); i++){
                  Article article = list.get(i);
                %>
                   <tr class="<%= (i%2)== 0 ? "w3-white" : "" %>">
                    <%--<td><%=(i+1) %></td> --%>
                    <td><%=article.getArticle_id() %></td>
                    <td><a href="article.jsp?article_id=<%=article.getArticle_id()%>"><%=article.getSubject() %></a></td>
                    <td><%=article.getWriter() %></td>
                    <td><%=article.getRegdate() %></td>
                    <td><%=article.getIp() %></td>
                    <td><%=article.getHitcount() %></td>
                  </tr>
                <%
                }
                %>
              </tbody>
            </table>
            <%-- 페이징 처리 --%>
            <div class="pagination board-left">
              <%
              if(pageBuilder.isShowFirst()){
              %>
                <a href="<%=pageBuilder.getQueryString(1)%>">처음으로</a>      
              <%        
              }
              %>
              
              <%
              if(pageBuilder.isShowPrevious()){
              %>
                <a href="<%=pageBuilder.getQueryString(pageBuilder.getPreviousStartPage())%>">&laquo;</a>      
              <%        
              }
              %>
              
              <%
              for(int i=pageBuilder.getStartPage(); i<=pageBuilder.getEndPage(); i++){
                if(i == params.getPage()){
              %>
                  <a class="active"><%=i %></a>
              <%          
                }else{
              %>
                   <a href="<%=pageBuilder.getQueryString(i)%>"><%=i %></a>
              <%          
                }
              }
              %>
              
              <%
              if(pageBuilder.isShowNext()){
              %>
                <a href="<%=pageBuilder.getQueryString(pageBuilder.getNextStartPage())%>">&raquo;</a>      
              <%        
              }
              %>
              <%
              if(pageBuilder.isShowLast()){
              %>
                <a href="<%=pageBuilder.getQueryString(pageBuilder.getPageCount())%>">끝으로</a>      
              <%        
              }
              %>
            </div>
            <div class="board-right">
              <form>
                <select id="find-option" name="searchType">
                  <option value="">전체</option>
                  <option value="title">글제목</option>
                  <option value="content">글내용</option>
                  <option value="writer">작성자</option>
                </select>
                <input type="text" name="searchValue" name="input-find">
                <input type="submit" class="board-find" value="검색">
              </form>
              <%
              if (loginId != "") {  
              %>
              <button class="board-write" onclick='location="/pBoard/createArticle.jsp"'>글쓰기</button>
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
