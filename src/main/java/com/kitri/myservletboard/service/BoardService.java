package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;

public class BoardService {

//    BoardDao boardDao = BoardMemoryDao.getInstance();
    BoardDao boardDao = BoardJdbcDao.getInstance();

    //싱글톤으로 하나만 생성
    private BoardService(){};
    private static final BoardService instance = new BoardService();

    public static BoardService getInstance(){
        return instance;
    }
    //게시글 하나 보여주기
    public Board getBoard(Long id) {
       return boardDao.getById(id);
    }
    //게시판 리스트 가져오기
    public ArrayList<Board> getBoards() {
        return boardDao.getAll();
    }
    public ArrayList<Board> getBoards(Pagination pagination) {
        pagination.setTotlaRow(((BoardJdbcDao)boardDao).count());
        pagination.calcPagination();
        return boardDao.getAll(pagination);
    }
    public ArrayList<Board> getBoards(String type, String search, Pagination pagination) {
        pagination.setTotlaRow(((BoardJdbcDao)boardDao).count(type,search));
        pagination.calcPagination();
        return boardDao.getAll(type, search, pagination);
    }

    public ArrayList<Board> getBoards(String type, String search, String period, Pagination pagination) {
        pagination.setTotlaRow(((BoardJdbcDao)boardDao).count(type,search,period));
        pagination.calcPagination();
        return boardDao.getAll(type, search, period,pagination);
    }

    public void addBoard(Board board){
        boardDao.save(board);
    }
    public void updateBoard(Board board){
        boardDao.update(board);
    }
    public void deleteBoard(Board board){
        boardDao.delete(board);
    }


}
