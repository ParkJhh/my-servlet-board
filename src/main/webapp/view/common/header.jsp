<%--
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
    <header>
        <a class="logo" href="/view/board/list.jsp"><span class="material-symbols-outlined">
run_circle
</span></a>
        <nav>
            <ul class="nav-items">
                <li><a href="/board/list">게시글목록</a></li>
                <li><a href="/view/member/join.jsp">회원가입</a></li>
                <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
                <li><a href="/view/member/login.jsp">로그인</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>
