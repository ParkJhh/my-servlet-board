package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.service.BoardService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao{
    private static final BoardMemoryDao instance = new BoardMemoryDao();
    public static BoardMemoryDao getInstance(){
        return instance;
    }
    ArrayList<Board> memoryBoardDB = new ArrayList<>();
    public BoardMemoryDao() {
        memoryBoardDB.add(new Board(1L,"첫번째 글","반갑습니다","손흥민", LocalDateTime.now(),10,2));
        memoryBoardDB.add(new Board(2L,"두번째 글","반갑습니다","박지성", LocalDateTime.now(),4,0));
        memoryBoardDB.add(new Board(3L,"세번째","어","김민재", LocalDateTime.now(),23,0));
        memoryBoardDB.add(new Board(4L,"수집","실패","이정후", LocalDateTime.now(),7,1));
        memoryBoardDB.add(new Board(5L,"조롱이","도롱이","고우석", LocalDateTime.now(),50,4));
        memoryBoardDB.add(new Board(6L,"노잼","예스잼","마동석", LocalDateTime.now(),46,5));
        memoryBoardDB.add(new Board(7L,"일곱번째 글","일곱난장이","백설공주", LocalDateTime.now(),5,18));
        memoryBoardDB.add(new Board(8L,"형왔다","역시 브랜드신","이재원", LocalDateTime.now(),99,23));
        memoryBoardDB.add(new Board(9L,"말이되냐","오 말이되네","암말", LocalDateTime.now(),121,11));
        memoryBoardDB.add(new Board(10L,"아니","이니시열었다","숫말", LocalDateTime.now(),241,34));
    }
    @Override
    public ArrayList<Board> getAll() {
        return memoryBoardDB;
    }

    @Override
    public Board getById(Long id) {
        return
        memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }

    @Override
    public void save(Board board) {
        memoryBoardDB.add(board);
    }

    @Override
    public void update(Board board) {
        Board board_ = getById(board.getId());
        memoryBoardDB.remove(board_);
        memoryBoardDB.add(board);
    }

    @Override
    public void delete(Board board) {
        memoryBoardDB.remove(board);
    }
}
