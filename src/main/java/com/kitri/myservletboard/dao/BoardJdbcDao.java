package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import javax.swing.text.TableView;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardJdbcDao implements BoardDao{
    //JDBC연결
    private static final BoardJdbcDao instance = new BoardJdbcDao();
    public static BoardJdbcDao getInstance(){
        return instance;
    }
    private BoardJdbcDao(){}

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

    @Override
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while(rs.next()){
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("view_count");

                boards.add(new Board(id,title,content,writer,createAt,viewCount,commentCount));
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
        return boards;
    }

    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,(pagination.getPage() -1) * pagination.getRowPerPage());
            ps.setInt(2,pagination.getRowPerPage());
            rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("view_count");

                boards.add(new Board(id,title,content,writer,createAt,viewCount,commentCount));
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
        return boards;
    }

    @Override
    public ArrayList<Board> getAll(String type, String search, Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();
        if (type == null){
            type="title";
        }
        if (search == null) {
            search="";
        }

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board where " + type + " like " + "'%" + search + "%'" + " LIMIT ?,?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,(pagination.getPage() -1) * pagination.getRowPerPage());
            ps.setInt(2,pagination.getRowPerPage());
            rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("view_count");

                boards.add(new Board(id,title,content,writer,createAt,viewCount,commentCount));
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
        return boards;
    }

    @Override
    public ArrayList<Board> getAll(String type, String search, String period, Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Board> boards = new ArrayList<>();
        if (type == null){
            type="title";
        }
        if (search == null) {
            search="";
        }

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board where " + type + " like " + "'%" + search + "%'" + " and created_at between date_sub(now(), interval " + period + " day) and now() " + " limit ?,?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,(pagination.getPage() -1) * pagination.getRowPerPage());
            ps.setInt(2,pagination.getLinePerPage());
            rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("view_count");

                boards.add(new Board(id,title,content,writer,createAt,viewCount,commentCount));
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
        return boards;
    }

    @Override
    public Board getById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Board board = new Board();

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("view_count");

                Board board_ =new Board(id,title,content,writer,createAt,viewCount,commentCount);
                return board_;
            }
        } catch(Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return board;
    }

    @Override
    public void save(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "INSERT INTO board (title,content,writer) VALUES(?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setString(3,board.getWriter());
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
    public void update(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "UPDATE board set title=?,content=? where id=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setLong(3,board.getId());
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
    public void delete(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "DELETE FROM board where id=?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,board.getId());
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

    public int count(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        try{
            connection = connectDB();
            String sql = "SELECT COUNT(*) FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            rs.next();

            count = rs.getInt("count(*)");

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
        return count;
    }
    public int count(String type, String search){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;

        if (type == null){
            type="title";
        }
        if (search == null) {
            search="";
        }

        try{
            connection = connectDB();
            String sql = "SELECT COUNT(*) FROM board where " + type + " like " + "'%" + search + "%'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            rs.next();
            count = rs.getInt("count(*)");

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
        return count;
    }
    public int count(String type, String search, String period) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (search == null) {
            return count();
        } else {
            int count = 0;

            try {
                connection = connectDB();
                String sql = "SELECT COUNT(*) FROM board where " + type + " like " + "'%" + search + "%'" + " and created_at between date_sub(now(), interval " + period + " day) and now() ";
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();

                rs.next();
                count = rs.getInt("count(*)");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return count;
        }
    }
}
