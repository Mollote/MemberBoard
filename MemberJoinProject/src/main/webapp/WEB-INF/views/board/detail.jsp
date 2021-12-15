<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- c태그cdn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>

	<h3>글 내용 조회하기</h3>
	
	<section>
	<div>글 제목 : ${board.b_title}</div>
	<div>
		글 작성자 : ${sessionScope.loginId}
		<!-- 이 글의 작성자와 현재 로그인한 아이디가 같을경우-->
		<c:if test="${sessionScope.loginId eq board.b_writer}">
		       <td><a href="update?b_number=${board.b_number}&page=${page}">수정</a></td>
               <td><a href="delete?b_number=${board.b_number}">삭제</a></td> 
		</c:if> 
	</div>
	<div>글 작성시간 :${board.b_title}</div>
	<div>글 내용 : ${board.b_contents}</div>
	<div>조회수 : ${board.b_hits }</div>
		파일첨부 : <img alt="" src="/resources/board_upload/${board.b_fileName}">
	</section>
	
	<!-- 댓글작성  -->
	<div id="comment-write">
			<input type="text" id="c_writer" value="${sessionScope.loginId}" readonly> 
			<input type="text" id="c_contents" placeholder="댓글내용">
			<button id="comment-write-btn">댓글등록</button>
	</div>
	<!-- 댓글 목록출력 -->
	<div id="comment-list">
		<table class="table">
			<tr>
				<th>댓글번호</th>
				<th>작성자 </th>
				<th>내용 </th>
				<th>작성시간 </th>
			</tr>
			<c:forEach items="${commentList }" var="comment">
				<tr>
					<td>${comment.c_number }</td>
					<td>${comment.c_writer }</td>
					<td>${comment.c_contents }</td>
					<td>${comment.c_date }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
		
		<a href="/board/paging">돌아가기</a>
</body>
<script type="text/javascript">

	 $("#comment-write-btn").click(function(){
			const commentWriter = $("#c_writer").val();
			const commentContents = $("#c_contents").val();
			const boardNumber = '${board.b_number}';
			console.log(commentWriter, commentContents, boardNumber);
	 		
			$.ajax({
				type:'post',
				url: '/comment/save',
				data: {
					'c_writer' : commentWriter,
					'c_contents' : commentContents,
					'b_number' : boardNumber
				},
				dataType: 'json',
				success: function(result){
					
					let output = "<table class='table'>";
					output += "<tr><th>댓글번호</th>";
					output += "<th>작성자</th>";
					output += "<th>내용</th>";
					output += "<th>작성시간</th></tr>";
					for(let i in result){
						output += "<tr>";
						output += "<td>"+result[i].c_number+"</td>";
						output += "<td>"+result[i].c_writer+"</td>";
						output += "<td>"+result[i].c_contents+"</td>";
						output += "<td>"+result[i].c_date+"</td>";
						output += "</tr>";
					}
					output += "</table>";
					// id가 comment-list인 div에 출력
					document.getElementById('comment-list').innerHTML = output;
					// 댓글 입력창을 비움. 
					document.getElementById('c_writer').value='';
					document.getElementById('c_contents').value='';
					
				},
				error: function(){
					console.log("오류 다시")	
				}
			});
	 });
</script>




</html>