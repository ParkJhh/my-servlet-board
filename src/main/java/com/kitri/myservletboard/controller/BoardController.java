package com.kitri.myservletboard.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>요청 테스트</h1>");

        //URL을 파싱해서 어떤 요청인지 파악
        out.println(req.getRequestURI());

        String reqURI = req.getRequestURI(); //   /board/list
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());

        String view = "/view/board/";

        if (command.equals("/board/list")) {
            //게시글 리스트 조회
            //게시판 페이지 응답
//            resp.sendRedirect("/view/board/list.html");
            view += "list.jsp";
        } else if (command.equals("/board/createForm")) {

            resp.sendRedirect("/view/board/createForm.html");

        } else if (command.equals("/board/create")) {

            resp.sendRedirect("/view/member/registration.html");

        } else if (command.equals("/board/updateForm")) {

            resp.sendRedirect("/view/board/updateForm.html");

        } else if (command.equals("/board/update")) {
            //게시판 번호에 맞는 글 수정
            resp.sendRedirect("/view/member/join.html");
        } else if (command.equals("/board/delete")) {
            //게시판 번호에 맞는 글 삭제
            resp.sendRedirect("/view/member/login.html");
        }

        //페이지 응답하는 방법
            //리다이렉트 >> 클라이언트에게 새로운 URL을 응답
            //포워드
    }
}
