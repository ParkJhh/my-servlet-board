package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Acomment;

import java.util.ArrayList;

public interface CommentDao {
    public void saveComment(Acomment comment);
    public void deleteComment(Long commentId);
    public ArrayList<Acomment> getComment(Long boardId);
}
