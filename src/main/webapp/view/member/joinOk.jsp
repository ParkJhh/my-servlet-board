<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="로그인" />
</jsp:include>

<body>
<jsp:include page="/view/common/header.jsp"/>
    <h1> 회원가입을 환영합니다.</h1>
    <p> 이제부터 글쓰기가 가능합니다.</p>

    <div class="footer">
        <footer>
            <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
        </footer>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</html>