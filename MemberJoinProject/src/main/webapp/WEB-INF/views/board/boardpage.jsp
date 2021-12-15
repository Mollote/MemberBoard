<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- c태그cdn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- 부트스트랩cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	/* 로그아웃 */
	function logout(){
		location.href="logout";
	}
</script>
</head>
<body>
	<h2>페이징페이지</h2>
	
	<section id="page_mypage">
		<div>
		현재 로그인 아이디 : ${sessionScope.loginId} 
		<button onclick="logout()">로그아웃</button> 
			<form action="/member/mypage" method="post">
                 <input type="hidden" name="m_id" value="${sessionScope.loginId}">
                 <input type="submit" value="마이페이지">
           	</form>
		</div><!-- 로그아웃시 index로이동 -->
	</section>
	
	<section id="page_searcg">
			<form action="/board/search" method="get">
				<select name="searchtype">
					<option value ="b_title"> 제목 </option>
					<option value ="b_writer"> 작성자 </option>
				</select>
			<input type="text" name="keyword" placeholder ="검색어.." >
			<input type="submit" value="검색">
			</form>
	</section>
	
	<section id="page_paging">
		<h3>게시글 목록</h3>
		<!-- 페이징목록 출력 -->
			<table class="table table-dark table-hover text-center">
		<tr>
			<th>글 번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성시간</th>
			<th>조회수</th>
		</tr>
			<c:forEach items="${bList}" var="board">
			<tr>
				<td>${board.b_number}</td>
				<td>${board.b_writer}</td>
				<td><a href="/board/detail?b_number=${board.b_number}&page=${paging.page}">${board.b_title}</a></td>
				<!-- 내가 접속한 페이징 위치 -->
				<td>${board.b_date}</td>
				<td>${board.b_hits}</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 목록 -->
	<div>
		<c:choose>
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise>
			<!-- 현재 페이지에서 1감소한 페이지 요청하는 링크 -->
				<a href="paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
		
		<!-- for(int i=startPage; i<=endPage; i++) -->
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="/board/paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>
			
	</section>
	
	<section id="page_board">
		<!-- 게시글작성, 조회, 본인글만 수정,삭제가능 -->
		<div><a href="/board/write">글 작성하기</a></div>
	</section>
	
</body>
</html>