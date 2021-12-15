<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

	<h2>회원목록</h2>

	<table class="table table-dark table-hover text-center">
		<tr>
			<th>번호</th>
			<th>ID</th>
			<th>이름</th>
			<th>이메일</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${mList}" var="m">
			<tr>
				<td>${m.m_number}</td>
				<td>${m.m_id}</td>
				<td>${m.m_name}</td>
				<td>${m.m_email}</td>
				<td><a href="/member/delete?m_number=${m.m_number}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/member/adminpage">관리자페이지</a>
	
</body>
</html>