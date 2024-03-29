package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.util.ArrayList;

public interface BoardDao {
    public ArrayList<Board> getAll();
    public ArrayList<Board> getAll(String value, String search, String period, String orderBy, Pagination pagination);
    public ArrayList<Board> getAll(String value, String search, String period, Pagination pagination);

    public ArrayList<Board> getAll(String value, String search, Pagination pagination);

    public ArrayList<Board> getAll(Pagination pagination);
    public Board getById(Long id);

    public void save(Board board);

    public void update(Board board);

    public void delete(Board board);
    public void viewCountUp(Long id);
    public void commentCountUp(Long id);
    public void commentCountDown(Long id);
}
