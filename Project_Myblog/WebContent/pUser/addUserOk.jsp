<%@ page contentType="text/html; charset=utf-8" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>
  
  <body>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <!-- Main Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-5 col-md-10 mx-auto">
            <h3 class="ok-text">가입을 축하합니다!</h3>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>아이디</label>
                <span class="inform-text">아이디</span>
                <span class="inform-list"><%=request.getAttribute("userId") %></span>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>이름</label>
                <span class="inform-text">이름</span>
                <span class="inform-list"><%=request.getAttribute("userName") %></span>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>이메일</label>
                <span class="inform-text">이메일</span>
                <span class="inform-list"><%=request.getAttribute("userEmail") %></span>
              </div>
            </div>
            <br>
            <div id="success"></div>
            <div class="form-group">
              <button class="btn btn-primary" onclick='location="/index.jsp"'>홈으로</button>
            </div>
          </div>
        </div>
      </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>
</html>
