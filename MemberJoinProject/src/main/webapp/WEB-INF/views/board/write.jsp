<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input,textarea{
		display:block;
	}
	
</style>
</head>
<body>
	<h2>글 작성하기</h2>
	<form action="/board/writeboard" method="post" enctype="multipart/form-data">
	<!-- 		<input type="hidden" name="b_number"> -->
		제목 : <input type="text" name="b_title">
		작성자 : <input type="text" name="b_writer" value="${sessionScope.loginId}" placeholder="${sessionScope.loginId}" readonly>
		내용 : <textarea rows="5" name="b_contents"></textarea>
		첨부파일 : <input type="file" name="b_file">
		<input type="submit" value="글 작성">
	</form>
</body>
</html>