서블릿 / JSP를 활용한 게시판 만들기

# 1. 기능 구성
JSP로부터 요청 > 컨트롤러 > 인터페이스 > 서비스 > dao 의 순으로 구성

- 게시판 컨트롤러 생성
  - [ ] 전체조회 / 작성 / 수정 / 삭제
  - [ ] 상세조회 / 댓글 수 증감 / 조회수 증감
  - [ ] 인터페이스 / dao의 CRUD쿼리 구현
     
- 멤버 컨트롤러 구성
  - [ ] 회원가입
  - [ ] 로그인 / 회원정보 전체불러오기
  - [ ] 인터페이스 / dao의 CRU쿼리 구현

- 댓글 컨트롤러 구성
  - [ ] 댓글 추가 / 댓글 삭제
     
- 데이터 구성
  - [ ] 게시판 / 멤버 / 댓글 / 페이지네이션

- 페이지네이션
  - [ ] 게시판 컨트롤러에 페이지네이션 기능 추가
  - [ ] 헤더 검색기능에 검색 조건 추가

- jsp 리팩토링
  - [ ] 공통 헤드 / 헤더 / 푸터 

# 2.기술 스택
 - 언어 : 자바
 - 프레임워크 : 스프링부트
 - 데이터베이스 : MySql
 - 통합개발도구 : 인텔리제이
 - 버전관리 : git, sourcetree, gitHub