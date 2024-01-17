<%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2024-01-16
  Time: 오전 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 폼</title>
</head>
<body>
<%
    //String isLoginFailed = (String) request.getAttribute("isLoginFailed");
    //아이디 읽기
    String id = (String) session.getAttribute("id");
    //아이디가 존재한다면
    if(id != null) {%>

    <h2>안녕하세요! <%=id%> 님 접속중입니다.</h2>
    <a href="/ex/logout"> 로그아웃 </a>

    <%} else {%>
    <form method="post" action="/ex/login">
        <label for="id">아이디:</label>
        <input type="text" name="id" id="id"><br>
        <label for="pw">비밀번호:</label>
        <input type="password" name="pw" id="pw"><br>
        <input type="submit" value="로그인">
    </form>
    <div>${requestScope.loginFailed ? "로그인이 실패하였습니다. 아이디 혹은 비밀번호를 입력해주세요" : ""}</div>
    <%}%>
</body>
<script>
    //2초동안 요소를 출력후 사라짐
    setTimeout(() => {
        document.querySelector(".notification".hidden = true);
    }, 2000)
</script>
</html>
