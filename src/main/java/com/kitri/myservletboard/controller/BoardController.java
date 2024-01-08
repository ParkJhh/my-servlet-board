package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    BoardService boardService = BoardService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>요청 테스트</h1>");

        //URL을 파싱해서 어떤 요청인지 파악
        out.println(req.getRequestURI());

        req.setCharacterEncoding("UTF-8");
        String reqURI = req.getRequestURI(); //   /board/list
        String contextPath = req.getContextPath();
        String command = reqURI.substring(contextPath.length());

        String view = "/view/board/";

        if (command.equals("/board/list")) {
            //게시글 리스트 조회
            //게시판 페이지 응답
//            resp.sendRedirect("/view/board/list.html");
            //jsp에게 동적으로 넘겨주어야 한다.
            ArrayList<Board> boards = boardService.getBoards();
            req.setAttribute("boards",boards);

            view += "list.jsp";
        } else if (command.equals("/board/createForm")) {

            view += "createForm.jsp";
        } else if (command.equals("/board/create")) {
            //데이터를 읽으니 문자 깨짐이 발생
            //데이터 읽고 저장
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String writer = req.getParameter("writer");

            Board board = new Board(null, title, content, writer, LocalDateTime.now(), 0,0);
            boardService.addBoard(board);

            resp.sendRedirect("/board/list");
            return;

        } else if (command.equals("/board/updateForm")) {
            //게시글 수정
            Long id = Long.parseLong(req.getParameter("id"));
            Board board = boardService.getBoard(id);

            req.setAttribute("board",board);
            view += "updateForm.jsp";

        } else if (command.equals("/board/update")) {
            //게시판 번호에 맞는 글 수정
            Long id = Long.parseLong(req.getParameter("id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            Board board = new Board(id, title, content, null, LocalDateTime.now(), 0,0);
            boardService.updateBoard(board);

            resp.sendRedirect("/board/list");
            return;

        } else if (command.equals("/board/delete")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Board board = new Board(id, null, null, null, null, 0,0);

            boardService.deleteBoard(board);
            resp.sendRedirect("/board/list");
            return;

        } else if (command.contains("/board/detail")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Board board = boardService.getBoard(id);

            req.setAttribute("board",board);
            view += "detail.jsp";
        }

        //페이지 응답하는 방법
            //리다이렉트 >> 클라이언트에게 새로운 URL을 응답
            //포워드 >> 요청에 따른 처리결과를 담아 응답
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }
}
