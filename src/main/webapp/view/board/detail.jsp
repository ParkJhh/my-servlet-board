<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.data.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Acomment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<%
    //세션 체크
    Member member = (Member) session.getAttribute("member");
    Board board = (Board) request.getAttribute("board");
%>
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="게시글 상세" />
</jsp:include>
<body class="sb-nav-fixed">
<jsp:include page="/view/common/header.jsp"/>

<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">

        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시글 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getCommentCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>저자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getCreatedAt()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>
                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    &nbsp
                    &nbsp
                    <%
                        if(member != null) {
                        if(member.getId().equals(board.getMemberId())){
                            {%>
                    <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                    &nbsp
                    <a href="/board/updateForm?id=${board.getId()}" class="btn btn-secondary btn-sm"><small>수정하기</small></a>
                    &nbsp
                    <%      }
                        }
                    }%>
                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
            <%
                if(member != null) {
            %>
            <div class="container-fluid px-4 ">
                <div class="card mb-4 w-50 mx-auto">
                    <small>댓글란</small>
                    <div class="m-3 h-75">
                        <form action="/comment/add">
                            <input type="text" name="boardId" id="boardId" value="<%=board.getId()%>" hidden>
                            <input type="text" class="h-100 form-control bg-white" id="commentText" name="commentText" value="">
                            <button type="submit" class="btn btn-secondary btn-sm"><small>댓글 등록</small></button>
                        </form>
                    </div>
                    <div class="d-flex flex-row-reverse mb-3 mr-3">
                    </div>
                </div>
            </div>
            <%} else {%>
            <div class="container-fluid px-4 ">
                <div class="card mb-4 w-50 mx-auto">
                    <p>회원에 한해 작성하실수 있습니다.</p><br>
                    <p>회원가입 하시기 바랍니다.</p>
                </div>
            </div>
            <%}%>
            <hr>
            <div class="container class=d-flex justify-content-center">
                <div class="p-2 border-primary mb-3">
                    <table class="table align-middle table-hover">
                        <thead class="table-dark">
                        <tr>
                            <th scope="col">댓글번호</th>
                            <th scope="col">댓글내용</th>
                            <th scope="col">작성자</th>
                            <th scope="col">댓글작성일</th>
                            <th scope="col">삭제</th>
                        </tr>
                        </thead>
                        <tbody class="table-group-divider">
                        <%
                            ArrayList<Acomment> comment = (ArrayList<Acomment>) request.getAttribute("comments");
                            if(comment != null){
                            for (int i = 0; i < comment.size(); i++) {%>
                        <tr>
                            <th scope="row"><%= comment.get(i).getId() %></th>
                            <td><%= comment.get(i).getContent() %></td>
                            <td><%= comment.get(i).getWriter() %></td>
                            <td><%= comment.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")) %></td>
                        <% if(member != null){
                            if(member.getId().equals(comment.get(i).getMemberId())){
                        %>
                            <td>
                            <form action="/comment/delete"><button class="btn btn-secondary btn-sm" type="submit" onclick="return confirm('삭제하시겠습니까?')"><small>댓글삭제</small></button>
                                <input type="text" name="commentId" id="commentId" value="<%=comment.get(i).getId()%>" hidden>
                                <input type="text" name="boardChk" id="boardChk" value="<%=comment.get(i).getBoard_id()%>" hidden>
                            </form>
                            </td>
                            <%}}%>
                        </tr>
                        <%}
                            } else {
                        %>
                        <tr>
                            <th scope="row"></th>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <%}%>
                        <input type="text" name="currentPage" value="1" hidden>
                        </tbody>
                    </table>
                </div>
        </div>
    </div>
</main>
</body>
</html>
