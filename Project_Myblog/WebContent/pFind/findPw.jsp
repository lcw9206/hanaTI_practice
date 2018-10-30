<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/include/head.jsp"></jsp:include>
  </head>
  
  <body>
    <!-- Navigator -->
    <jsp:include page="/include/navigator.jsp"></jsp:include>
    <!-- Main Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <p><a href="findId.jsp" id="findId">아이디 찾기 </a>/ <a href="findPw.jsp" id="findPw">비밀번호 찾기</a></p>
            <form name="sentMessage" id="contactForm" novalidate>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>Name</label>
                  <input type="text" class="form-control" placeholder="비밀번호를 입력해주세요." id="name" required data-validation-required-message="Please enter your name.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </article>
    <hr>
    <jsp:include page="/include/footer.jsp"></jsp:include>
  </body>
</html>
