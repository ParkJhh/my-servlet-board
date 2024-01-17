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
<%
    if(member != null) {
%>
    <div class="container">
        <div class="input-form-backgroud row">
            <div class="input-form col-md-12 mx-auto">
                <h4 class="mb-3"><b>회원 정보 수정</b></h4>
                <hr>
                <br>
                <form class="validation-form" action="/member/regi" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="username">이름</label>
                            <input type="text" class="form-control" name="username" id="username"  value="<%=member.getName()%>" required>
                            <div class="invalid-feedback">
                                이름은 공백일 수 없습니다.
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="userid">아이디</label>
                            <input type="text" class="form-control" name="userid" id="userid"  value="<%=member.getLoginId()%>" disabled>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="pw">비밀번호</label>
                            <input type="password" class="form-control" name="pw" id="pw" value="<%=member.getPassword()%>" required>
                            <div class="invalid-feedback">
                                비밀번호를 입력해주세요.
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" id="email" value="<%=member.getEmail()%>" required>
                        <div class="invalid-feedback">
                            이메일을 입력해주세요.
                        </div>
                    </div>

                    <hr class="mb-4">
                    <br>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit">회원 정보 수정</button>
                        </div>
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-secondary btn-block" type="submit">취소</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="p-2">
        <div class="footer">
            <footer>
                <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
            </footer>
        </div>
    </div>
    <script>
        window.addEventListener('load', () => {
            const forms = document.getElementsByClassName('validation-form');

            Array.prototype.filter.call(forms, (form) => {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    </script>
<%} else {%>
    <div>
        <h1>회원만 정보를 수정 할 수 있습니다</h1>
        <p>  회원가입을 부탁 드립니다.</p>
    </div>
<%}%>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>