package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Acomment;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.service.BoardService;
import com.kitri.myservletboard.service.CommentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {

    CommentService commentService = CommentService.getInstance();
    BoardService boardService = BoardService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>요청 테스트</h1>");

        //URL을 파싱해서 어떤 요청인지 파악
        out.println(req.getRequestURI());
        String reqURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());
        String view = "/view/board/";

        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");


        if(command.equals("/comment/add")){

            Long boardId = Long.parseLong(req.getParameter("boardId"));
            Long memberId = member.getId();
            String commentText = req.getParameter("commentText");

            Acomment comment = new Acomment(boardId,memberId,commentText);
            //댓글 추가
            commentService.addComment(comment);
            //댓글 작성 이후 코멘트수를 보드DB에 반영
            boardService.commentCountUp(boardId);

            resp.sendRedirect("/board/detail?id=" + boardId);
            return;
        } else if(command.equals("/comment/delete")){
            Long commentId = Long.parseLong(req.getParameter("commentId"));
            Long boardId = Long.parseLong(req.getParameter("boardChk"));

            //댓글 삭제 처리를 받으면 보드DB에서 댓글수를 -1 한다
            boardService.commentCountDown(boardId);
            //댓글 삭제
            commentService.deleteComment(commentId);

            resp.sendRedirect("/board/detail?id=" + boardId);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req,resp);
    }
}
