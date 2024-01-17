<%@ page import="com.kitri.myservletboard.data.Member" %><%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-09
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .material-symbols-outlined {
        font-variation-settings:
                'FILL' 0,
                'wght' 400,
                'GRAD' 0,
                'opsz' 24
    }
</style>
<head>
    <title>Common Header</title>
</head>
<body>
    <header class="">
        <a class="logo" href="/board/list"><span class="material-symbols-outlined">
run_circle
</span></a>
        <nav>
        <div class="d-flex justify-content-center">
            <div class="p-2"><a href="/board/list">게시글 목록</a></div>
            <div class="p-2"><a href="/view/member/join.jsp">회원 가입</a></div>
            <div class="p-2"><a href="/view/member/registration.jsp">회원정보 수정</a></div>
            <%
                Member member = (Member) session.getAttribute("member");
                //아이디가 존재한다면
                if(member != null) {%>
            <div class="p-2"><a href="/member/logout">로그아웃</a></div>
            <%} else {%>
            <div class="p-2"><a href="/view/member/login.jsp">로그인</a></div>
            <%}%>
            <div class="p-2">
                <form role="search" action="/board/list">
                    <select name="period" >
                        <option value = "36500" selected ${param.period == "" ? "selected" : ""}>전체기간</option>
                        <option value ="1" ${param.period == "1" ? "selected" : ""}>1일전</option>
                        <option value ="7" ${param.period == "7" ? "selected" : ""}>1주일전</option>
                        <option value ="30" ${param.period == "30" ? "selected" : ""}>1개월전</option>
                        <option value ="180" ${param.period == "180" ? "selected" : ""}>6개월전</option>
                    </select>
                    <select name="value">
                        <option value="title" ${param.value == "title" ? "selected" : ""}>제목</option>
                        <option value="writer" ${param.value == "writer" ? "selected" : ""}>작성자</option>
                    </select>
                    &nbsp;
                    <input name="search" type="search" value="${param.search}">
                    <button class="btn btn-outline-dark" type="submit">Search</button>

                    <select name="orderBy" id="orderBy" onchange="this.form.submit()">
                        <option value="createdAtDESC" ${param.orderBy == "createdAtDESC" ? "selected" : ""}>최신순</option>
                        <option value="viewCountDesc" ${param.orderBy == "viewCountDesc" ? "selected" : ""}>조회순</option>
                        <option value="accuracy" ${param.orderBy == "accuracy" ? "selected" : ""}>정확도순(미구현)</option>
                    </select>
                    <select name="maxRecordsPerPage" id="maxRecordsPerPage" onchange="this.form.submit()">
                        <option value="5" ${param.maxRecordsPerPage == "5" ? "selected" : ""}>5개씩 보기</option>
                        <option value="10" ${param.maxRecordsPerPage == "10" ? "selected" : ""}>10개씩 보기</option>
                        <option value="15" ${param.maxRecordsPerPage == "15" ? "selected" : ""}>15개씩 보기</option>
                        <option value="20" ${param.maxRecordsPerPage == "20" ? "selected" : ""}>20개씩 보기</option>
                        <option value="30" ${param.maxRecordsPerPage == "30" ? "selected" : ""}>30개씩 보기</option>
                        <option value="40" ${param.maxRecordsPerPage == "40" ? "selected" : ""}>40개씩 보기</option>
                        <option value="50" ${param.maxRecordsPerPage == "50" ? "selected" : ""}>50개씩 보기</option>
                    </select>
                </form>
            </div>
        </div>
        </nav>
    </header>
</body>
</html>
