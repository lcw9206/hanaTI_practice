<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/pInclude/head.jsp"></jsp:include>
  </head>
  <body>
    <%
    // 아이디 중복 검사를 위한 변수
    System.out.println("addUser : " + request.getAttribute("userId"));
    System.out.println("exist : " + request.getAttribute("exist"));
    %>
    <!-- Navigator -->
    <jsp:include page="/pInclude/navigator.jsp"></jsp:include>
    <!-- Main Content -->
    <article class="main">
      <div class="container">
        <div class="row">
          <div class="col-lg-5 col-md-10 mx-auto">
            <h3 class="addUser-inform-text">회원가입</h3>
            <button class="id-inspection" id="confirmIdButton">중복검사</button>
            <form action="/pUser/addUserAction.jsp" id="addUserForm" method="post">
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>아이디</label>
                  <span class="inform-text">아이디</span>
                  <span class="error_comment" id="idError">에러문구</span>
                  <input type="text" class="form-control" placeholder="영어로 시작하며 6~10자의 영/숫자로 적어주세요 ." id="userId" name="id" 
                   <%
                   if(request.getAttribute("userId") != null) {
                   %> 
                   value="<%=request.getAttribute("userId") %>" 
                   <%
                   }
                   %> 
                   required>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>이름</label>
                  <span class="inform-text">이름</span>
                  <span class="error_comment" id="nameError">에러문구</span>
                  <input type="text" class="form-control" placeholder="2~6자 한글 입력이 가능합니다." id="userName" name="name" 
                   <%
                   if(request.getAttribute("userName") != null) {
                   %> 
                   value="<%=request.getAttribute("userName") %>" 
                   <%
                   }
                   %> 
                  required>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>비밀번호</label>
                  <span class="inform-text">비밀번호</span>
                  <span class="error_comment" id="passwdError">에러문구</span>
                  <input type="password" class="form-control" placeholder="6~10자 영/숫자 입력이 가능합니다." id="userPasswd" name="passwd" 
                  <%
                  if(request.getAttribute("userPasswd") != null) {
                  %> 
                  value="<%=request.getAttribute("userPasswd") %>" 
                  <%
                  }
                  %> 
                  required>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>비밀번호 확인</label>
                  <span class="inform-text">비밀번호 확인</span>
                  <span class="error_comment" id="cPasswdError">에러문구</span>
                  <input type="password" class="form-control" placeholder="비밀번호를 다시 한 번 입력해주세요." id="confirmPasswd" required>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>이메일</label>
                  <span class="inform-text">이메일</span>
                  <span class="error_comment" id="emailError">에러문구</span>
                  <input type="text" class="form-control" placeholder="이메일 양식에 맞춰 입력해주세요." id="email" name="email" 
                  <%
                  if(request.getAttribute("userEmail") != null) {
                  %> 
                  value="<%=request.getAttribute("userEmail") %>" 
                  <%
                  }
                  %> 
                  required>
                </div>
              </div>
              <br>
              <div class="form-group">
                <button type="button" class="btn btn-primary" id="sendUserInform" disabled>가입 신청</button>
                <span id="addUserSubText">아이디 중복 검사는 필수입니다.</span>
              </div>
            </form>
            
          </div>
        </div>
      </div>
    </article>
    <hr>
    <jsp:include page="/pInclude/footer.jsp"></jsp:include>
    <script src="/js/validation.js"></script>
    <script src="/js/addUser.js"></script>
    <%
      if(request.getAttribute("exist") != null) {
  	    if(request.getAttribute("exist").equals("0")) {
      	  out.println("<script>confirmClear()</script>");
        } else if(request.getAttribute("exist").equals("1")){
          out.println("<script>confirmIdFalse()</script>"); 
        } else {
          out.println("<script>confirmClear()</script>");
          out.println("<script>confirmEmailFalse()</script>");
        }
      }
    %>
  </body>
</html>
