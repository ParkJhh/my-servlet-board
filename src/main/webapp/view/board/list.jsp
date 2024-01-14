<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<%
  Pagination pagination =  (Pagination) request.getAttribute("pagination");
  String type = (String) request.getAttribute("type");
  String search = (String) request.getAttribute("search");
  String period = (String) request.getAttribute("period");

  String params = "";
  if (search != null) {
    params += "&type=" + type + "&search=" + search;
  } else {
    search = "";
  }
  if(period != null){
    params += "&period=" + period;
  } else {
    period = "";
  }
%>

<jsp:include page="/view/common/head.jsp">
  <jsp:param name="title" value="게시글 목록" />
</jsp:include>

<jsp:include page="/view/common/head.jsp">
  <jsp:param name="type" value="<%=type%>"/>
  <jsp:param name="search" value="<%=search%>"/>
  <jsp:param name="period" value="<%=period%>"/>
</jsp:include>
<body>
<jsp:include page="/view/common/header.jsp"/>

  <div>
    <h2 style="text-align: center; margin-top: 100px;"><b>게시글 목록</b></h2>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일자</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <% ArrayList<Board> boards = (ArrayList<Board>) request.getAttribute("boards");
            for (int i = 0; i < boards.size(); i++) { %>

          <tr>
            <th scope="row"><%= boards.get(i).getId() %></th>
            <td><a href="/board/detail?id=<%= boards.get(i).getId() %>"><%= boards.get(i).getTitle() %></a></td>
            <td><%= boards.get(i).getWriter() %></td>
            <td><%= boards.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-dd:HH")) %></td>
            <td><%= boards.get(i).getViewCount() %></td>
            <td><%= boards.get(i).getCommentCount() %></td>
          </tr>
          <%
            }
          %>

        </tbody>
      </table>
      <div>
        <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
      </div>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <%
            if(pagination.isHasPrev()){
          %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getViewStartPage() - 1%><%=params%>">Previous</a>
          </li>
          <%} else {%>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getViewStartPage() - 1%><%=params%>">Previous</a>
          </li>
          <%}%>

          <%
            for(int i = pagination.getViewStartPage(); i <= pagination.getViewEndPage(); i++){
              if(pagination.getPage() == i){
          %>
          <li class="page-item active"><a class="page-link" href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%} else {%>
          <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%><%=params%>"><%=i%></a></li>
          <%}}%>

          <%
            if(pagination.isHasNext()){
          %>
          <li class="page-item">
            <a class="page-link" href="/board/list?page=<%=pagination.getViewEndPage() + 1%><%=params%>">Next</a>
          </li>
          <%} else {%>
          <li class="page-item disabled">
            <a class="page-link" href="/board/list?page=<%=pagination.getViewEndPage() + 1%><%=params%>">Next</a>
          </li>
          <%}%>
        </ul>
      </nav>
    </div>
    </div>
  </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</html>