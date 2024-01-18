<%@ page import="com.kitri.myservletboard.data.Member" %><%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-17
  Time: 오후 3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Member member = (Member) session.getAttribute("member");
%>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid px-4 ">

    <div class="card mb-4 w-50 mx-auto">
        <div>
            <h2 class="mt-3" style="text-align: center;"><b>댓글</b></h2>
            <hr class="mb-0">
        </div>
            <div class="m-3 h-75">
                <form action="/comment/add">
                    <textarea class="h-100 form-control bg-white" id="commentText" name="commentText"></textarea>
                    <button type="submit">댓글 등록</button>
                </form>
                <
            </div>
            <div class="d-flex flex-row-reverse mb-3 mr-3">
        </div>
    </div>
</div>
</body>
</html>
