package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.CommentDao;
import com.kitri.myservletboard.dao.CommentJdbcDao;
import com.kitri.myservletboard.data.Acomment;

import java.util.ArrayList;


public class CommentService {

    CommentDao commentDao = CommentJdbcDao.getInstance();
    //싱글톤으로 하나만 생성
    private CommentService(){};
    private static final CommentService instance = new CommentService();
    public static CommentService getInstance(){
        return instance;
    }

    public void addComment(Acomment comment){
        commentDao.saveComment(comment);
    }
    public void deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
    }
    public ArrayList<Acomment> getComment(Long boardId){
        return commentDao.getComment(boardId);
    }


}
