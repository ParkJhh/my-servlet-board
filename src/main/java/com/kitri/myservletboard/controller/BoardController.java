package com.kitri.myservletboard.controller;
import com.kitri.myservletboard.data.Acomment;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.service.BoardService;
import com.kitri.myservletboard.service.CommentService;
import com.kitri.myservletboard.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    BoardService boardService = BoardService.getInstance();
    MemberService memberService = MemberService.getInstance();
    CommentService commentService = CommentService.getInstance();

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

        if (command.equals("/board/list")) {
            //페이지 관련 세팅
            String page = req.getParameter("page");
            //검색 값
            String value = req.getParameter("value");
            String search = req.getParameter("search");
            //검색 기간조회 초기 null, 전체조회 ""
            String period = req.getParameter("period");
            //최신순, 조회순, 몇개씩 볼것인가
            String orderBy = req.getParameter("orderBy");
            String maxRecordsPerPage = req.getParameter("maxRecordsPerPage");

            if (page == null) page = "1";
            if (period == null) period = "36500";
            if (value == null) value = "title";
            if (search == null) search = "";
            if (orderBy == null) orderBy = "createdAtDESC";
            if (maxRecordsPerPage == null) maxRecordsPerPage = "10";

            Pagination pagination = new Pagination(Integer.parseInt(page), Integer.parseInt(maxRecordsPerPage));

            if (period == null) {
                ArrayList<Board> boards = boardService.getBoards(value, search, pagination);
                req.setAttribute("boards", boards);
            } else if(orderBy != null && period != null) {
                ArrayList<Board> boards = boardService.getBoardsOrderBy(value, search, period, orderBy,pagination);
                req.setAttribute("boards", boards);
            } else if (period.equals("36500")) {
                //전체조회
                ArrayList<Board> boards = boardService.getBoardsOrderBy(value, search, period, orderBy,pagination);
                req.setAttribute("boards", boards);
            }

            req.setAttribute("pagination",pagination);
            req.setAttribute("value",value);
            req.setAttribute("search",search);
            req.setAttribute("period",period);
            req.setAttribute("orderBy",orderBy);
            req.setAttribute("maxRecordsPerPage",maxRecordsPerPage);
            view += "list.jsp";

        } else if (command.equals("/board/createForm")) {
            view += "createForm.jsp";
        } else if (command.equals("/board/create")) {
            //데이터 읽고 저장
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String writer = req.getParameter("writer");
            Long memberId = Long.parseLong(req.getParameter("memberId"));

            Board board = new Board(null, title, content, writer, LocalDateTime.now(), 0,0,memberId);
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
            //게시판 번호에 맞는 글 삭제
            Long id = Long.parseLong(req.getParameter("id"));
            Board board = new Board(id, null, null, null, null, 0,0);

            boardService.deleteBoard(board);
            resp.sendRedirect("/board/list");
            return;

        } else if (command.contains("/board/detail")) {
            Long id = Long.parseLong(req.getParameter("id"));
            //조회수 증가 > 게시글 불러오기 전에 미리 증가시킴
            boardService.viewCountUp(id);
            //게시글 불러오기
            Board board = boardService.getBoard(id);
            //전체 댓글 불러오기
            ArrayList<Acomment> comments = commentService.getComment(id);

            req.setAttribute("comments",comments);
            req.setAttribute("board",board);
            view += "detail.jsp";

        }

        //페이지 응답하는 방법
        //리다이렉트 >> 클라이언트에게 새로운 URL을 응답
        //포워드
        //포워드 >> 요청에 따른 처리결과를 담아 응답
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }
}