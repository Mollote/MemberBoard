<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>수정하기</h2>
	<form action="update" method="post" name="update_form">
			<input type="hidden" name="page" value="${page}"><!-- page값 저장하기  -->
		 	글 번호 : <input type="text" name="b_number" value="${board.b_number}" readonly>
			제목 : <input type="text" name="b_title" value="${board.b_title}" > 
			작성시간 : <input type="text"name="b_date" value="${board.b_date}" readonly>
			내용 : <textarea name="b_contents" rows="5">${board.b_contents}</textarea>
			<input type="submit" value="수정하기">
	</form>

	
</body>
</html>