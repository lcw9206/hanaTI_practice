<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
임시 회원 리스트 화면 입니다.


<table border="1">
    <tr>
      <th>번호</th>
      <th>아이디</th>
      <th>이름</th>
      <th>비밀번호</th>
      <th>이메일</th>
      <th>가입일자</th>
    </tr>
    <c:choose>
      <c:when test="${not empty list}">
        <c:forEach var="user" items="${list}" varStatus="x" > <!-- => 무조건 getter 메서드가 존재해야함 -->
          <tr>
            <td>${x.count }</td>
            <td>${user.id }</td>
            <td>${user.name }</td>
            <td>${user.passwd }</td>
            <td>${user.email }</td>
            <td>${user.regdate }</td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr>
          <td colspan="5">회원이 없다.</td>
        </tr>
      </c:otherwise>
    </c:choose>
  </table>

</body>
</html>