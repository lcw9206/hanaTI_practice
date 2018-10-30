<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>

  <body>
    <!-- Navigator & Header -->
  	<jsp:include page="/pInclude/navigator.jsp"></jsp:include>
  	<jsp:include page="/pInclude/header.jsp"></jsp:include>
    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-preview">
            <!-- db값 받아와 출력 후, 동적으로 a 태그 걸기 -->
            <!-- for문으로 최신 5개 정도? -->
            <a href="post.jsp">
              <h2 class="post-title">
                Man must explore, and this is exploration at its greatest
              </h2>
              <h3 class="post-subtitle">
                Problems look mighty small from 150 miles up
              </h3>
            </a>
            <p class="post-meta">Posted by
              <a href="#">Start Bootstrap</a>
              on September 24, 2018</p>
          </div>

          <hr>
          <!-- Pager -->
          <div class="clearfix">
            <a class="btn btn-primary float-right" href="/pBoard/board.jsp">Older Posts &rarr;</a>
          </div>
        </div>
      </div>
    </div>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>

</html>
