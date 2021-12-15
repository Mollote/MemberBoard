	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
input {
	display: block;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- ajax용 jQuery 호출 -->
<script type="text/javascript">
	/* 아이디 입력을 하는동안에 idDuplicate() 함수를 호출하고 입력된 값을 콘솔에 출력*/
	function idDuplicate() {
		const id = document.getElementById('m_id').value;
		const checkResult = document.getElementById('id-dup-check');
		$.ajax({
			type : 'post',
			url : 'idDuplicate',// 요청주소 (컨트롤러로 요청하는 주소)
			data : {
				'm_id' : id
			},
			dataType : 'text',
			success : function(result) { //요청이 성공적으로 처리 됐을때 실행 할 함수
				if (result == "ok") {
					checkResult.style.color = 'green';
					checkResult.innerHTML = '멋진아이디네요';
				} else {
					checkResult.style.color = 'red';
					checkResult.innerHTML = '이미사용중인아이디입니다.'
				}
			},
			error : function() { // 요청이 실패했을 때 실행할 함수
				console.log('오타를 찾으십시오.')
			}
		});
	}
</script>
</head>
<body>
	<h2>회원가입 페이지입니다.</h2>
	<section>
		<!-- 아이디, 비밀번호, 이름, 이메일, 
	프로필사진을 입력받음 o , ajax로 중복검사(jQuery) o, 관리자용따로 x ,-->
		<form action="/member/save" method="post" enctype="multipart/form-data">
			아이디 : <input type="text" name="m_id" onkeyup="idDuplicate()" id="m_id"> 
			<span id="id-dup-check"></span> <br> 
			비밀번호 : <input type="password" name="m_password">
			이름 : <input type="text" name="m_name"> 
			이메일 : <input type="text" name="m_email"> 
			프로필 사진 : <input type="file" name="m_profile"> 
			<input type="submit" value="가입하기">
		</form>
	</section>

</body>
</html>