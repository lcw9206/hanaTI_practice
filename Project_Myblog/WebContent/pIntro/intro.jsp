<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>

  <body>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <p>개 정보 공유 블로그</p>
            <a href="#">
              <img class="img-fluid" src="../img/dog1.jpg" alt="">
            </a>
            <span class="caption text-muted">포메라니안</span>
            <p>강아지를 만지는 것보다 보는 것을 좋아합니다.</p>
            <p>보다 많은 종류에 대해 알아보고싶어 만든 정보공유 블로그입니다.</p>
          </div>
        </div>
      </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
  </body>
</html>
