# MemberBoard

- 프로젝트에 [README.md](http://readme.md/) 파일을 작성하여 어떤 기능을 썼는지 적고, 테이블 정의한 내용도 적어놓읍시다.

MemberBoard 프로젝트
- 회원제 게시판 만들어보기


<h2>기본 설정</h2>

Controller
- 컨트롤러, jsp를 연결 관리해주며 여러 메서드들을 요청 , 만들수 있다
  - memberController : 회원가입, 로그인, 로그아웃, 마이페이지, 관리자페이지, 회원삭제
  - commentController : 댓글 작성, 조회 요청
  - boardController : 게시글 작성, 조회, 삭제, 수정 요청

DTO (Data Transfer Object)
- 데이터들을 담을 공간으로 크게 회원용, 게시글용, 댓글용으로 구분
- MemberDTO, BoardDTO, CommentDTO

Service
- 요청값들을 처리하는 부분으로 interface를 사용하여 상속되게 정리하였다
- MemberService, BoardService, CommentService

Repository
- sql 언어로 변환하여 데이터베이스에 연결해준다

mapper
- sql의 대표적인 언어를 사용할수있으며 직접 CRUD 쿼리문으로 요청한다.
- mybatis를 통한 연결이 필수

DB
- table:
