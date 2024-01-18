package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.dao.MemberDao;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

    MemberService memberService = MemberService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>요청 테스트</h1>");

        //URL을 파싱해서 어떤 요청인지 파악
        out.println(req.getRequestURI());
        String reqURI = req.getRequestURI(); //   /board/list
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());
        String view = "/view/member/";

        if (command.equals("/member/join")) {
            String username = req.getParameter("username");
            String userid = req.getParameter("userid");
            String pw = req.getParameter("pw");
            String email = req.getParameter("email");

            Member member = new Member(userid, pw, username, email);
            Member membercheck = memberService.getMember(userid);

            if (membercheck.getLoginId() != null) {
                //아이디 중복
                resp.sendRedirect("/view/member/joinFail.jsp");
                return;
            } else if (membercheck.getLoginId() == null) {
                //아이디 중복하지 않는 경우
                memberService.addMemeber(member);
                resp.sendRedirect("/view/member/joinOk.jsp");
                return;
            }

        } else if(command.equals("/member/regi")) {
            String username = req.getParameter("username");
            String userid = req.getParameter("userid");
            String pw = req.getParameter("pw");
            String email = req.getParameter("email");

            Member member = new Member(userid, pw, username, email);
            memberService.updateMember(member);

            resp.sendRedirect("/view/member/regiOk.jsp");
            return;
        } else if (command.equals("/member/login")) {
            String userid = req.getParameter("userid");
            String pw = req.getParameter("pw");
            //로그인 체크용
            //false로 끝나야먄 성공,
            boolean isLoginFailed = false;

            Member member = memberService.getMember(userid);

            if (userid.isEmpty() || member.getLoginId() == null) {
                //아이디 빈값인가 체크 혹은 아이디가 DB에 존재하지 않는 경우
                isLoginFailed = true;
            } else {
                if (!member.getPassword().equals(pw)) {
                    //아이디는 있으나 비밀번호가 맞지 않는 경우
                    isLoginFailed = true;
                }
            }
            if(isLoginFailed) {
                req.setAttribute("loginFailed",isLoginFailed);
                view += "loginFail.jsp";
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("member",member);
                view = "/board/list";
            }

        } else if (command.equals("/member/logout")) {
            HttpSession session = req.getSession();
            session.invalidate();

            resp.sendRedirect("/board/list");
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req,resp);
    }
}
