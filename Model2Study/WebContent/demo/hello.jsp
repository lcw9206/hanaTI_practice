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
<h2>메세지 : ${ requestScope.message }</h2>

<h2>팀명</h2>
<ul>
  <c:forEach var="team" items="${list }">
  <li>${team }</li>
  </c:forEach>
</ul>
</body>
</html>