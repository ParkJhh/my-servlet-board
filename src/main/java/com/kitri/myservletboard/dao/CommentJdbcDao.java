package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Acomment;
import com.kitri.myservletboard.data.Board;

import java.lang.reflect.AccessibleObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentJdbcDao implements CommentDao{

    private static final CommentJdbcDao instance = new CommentJdbcDao();
    public static CommentJdbcDao getInstance(){
        return instance;
    }
    private CommentJdbcDao(){}

    public Connection connectDB(){
        //커넥션 생성
        Connection conn = null;
        try{
            //for name을 통해 드라이버를 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            //연결 주소, 유저 ,비번 설정
            String url ="jdbc:mysql://localhost:3306/my_servlet_board";
            String user ="root";
            String pwd ="zaqxsw123";
            //커넥션 설정
            conn = DriverManager.getConnection(url, user, pwd);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ArrayList<Acomment> getComment(Long boardId){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Acomment> comments = new ArrayList<>();

        try{
            connection = connectDB();
            String sql = "SELECT c.id, b.id, c.content, m.name, c.created_at, m.id from board b, comment c, member m where b.id = c.board_id and m.id = c.member_id and b.id=?";
//                        select b.id, c.content, b.writer, c.created_at
//                          from board b, comment c
//                          where b.id = c.board_id
//                          and b.id = '125';
            ps = connection.prepareStatement(sql);
            ps.setLong(1,boardId);
            rs = ps.executeQuery();

            while(rs.next()){
                Long cid = rs.getLong("c.id");
                Long id = rs.getLong("b.id");
                String content = rs.getString("c.content");
                String writer = rs.getString("m.name");
//                Long board_Id = rs.getLong("board_id");
//                Long memberId = rs.getLong("member_id");
                LocalDateTime createAt = rs.getTimestamp("c.created_at").toLocalDateTime();
                Long memberId = rs.getLong("m.id");

                comments.add(new Acomment(cid,id,content,writer,createAt,memberId));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return comments;
    }
    @Override
    public void saveComment(Acomment comment) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "INSERT INTO comment (board_id,member_id,content) VALUES(?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,comment.getBoard_id());
            ps.setLong(2,comment.getMember_id());
            ps.setString(3,comment.getContent());
            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "DELETE FROM comment where id=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,commentId);
            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
