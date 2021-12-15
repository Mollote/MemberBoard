<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	function update(){
		
		const pw = document.getElementById('m_password').value;
		const pwDB= '${member.m_password}';
		if(pw==pwDB){
			mypage_form.submit();
		}else{
			alert('비밀번호 오류입니다.');
		}
	}
</script>

</head>
<body>
	<h2>마이페이지 정보수정</h2>
	<form action="/member/mypageud" method="post" name="mypage_form">
		 	<input type="hidden" name="m_number" value="${member.m_number}">
			아이디 : <input type="text" name="m_id" value="${member.m_id}" readonly> 
			비밀번호 : <input type="password" name="m_password" id="m_password"> 
			이름 : <input type="text" name="m_name" value="${member.m_name}" > 
			이메일 : <input type="text"name="m_email" value="${member.m_email}">
				<input type="button" value="수정하기" onclick="update()">
	</form>
	
</body>
</html>