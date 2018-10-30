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
            <p><a href="/findId.jsp" id="findId">아이디 찾기 </a>/ <a href="/findPw.jsp" id="findPw">비밀번호 찾기</a></p>
            <form name="sentMessage" id="contactForm" novalidate>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>Name</label>
                  <input type="text" class="form-control" placeholder="Name" id="name" required data-validation-required-message="Please enter your name.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>Email Address</label>
                  <input type="email" class="form-control" placeholder="Email Address" id="email" required data-validation-required-message="Please enter your email address.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group col-xs-12 floating-label-form-group controls">
                  <label>Phone Number</label>
                  <input type="tel" class="form-control" placeholder="Phone Number" id="phone" required data-validation-required-message="Please enter your phone number.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls">
                  <label>Message</label>
                  <textarea rows="5" class="form-control" placeholder="Message" id="message" required data-validation-required-message="Please enter a message."></textarea>
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <br>
              <div id="success"></div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary" id="sendMessageButton">Send</button>
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
