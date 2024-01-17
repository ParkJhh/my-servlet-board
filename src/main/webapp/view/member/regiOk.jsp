<%@ page import="com.kitri.myservletboard.data.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<%
    Member member = (Member) session.getAttribute("member");
%>

<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="회원 정보 수정" />
</jsp:include>

<body>
<jsp:include page="/view/common/header.jsp"/>

<div>
    <h1>회원 정보 수정이 완료 되었습니다.</h1>
    <p>  로그아웃 후 다시 로그인하시기 바랍니다.</p>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>