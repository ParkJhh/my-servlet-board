package ex.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/ex/login")
public class LoginServlet extends HttpServlet {
    static HashMap<String, String> members = new HashMap<>();
    static {
        members.put("abc123", "12345");
        members.put("captain91", "12345");
        members.put("ilovecoding", "12345");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/ex/login/login.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인 처리
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        //정상이라면 false, 실패라면 true가 되게끔 한다
        boolean isLoginFailed = false;
        //정보 확인용
        if (id.isEmpty() || pw.isEmpty()) {
            isLoginFailed = true;
        }

        if (members.get(id) == null) {
            //아이디 빈값인가 체크
            isLoginFailed = true;
        } else {
            if (!members.get(id).equals(pw)) {
                //아이디는 있으나 비밀번호가 맞지 않는 경우
                isLoginFailed = true;
            }
        }

        if(isLoginFailed) {
            req.setAttribute("loginFailed",isLoginFailed);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("id",id);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/ex/login/login.jsp");
        dispatcher.forward(req,resp);
    }
}
