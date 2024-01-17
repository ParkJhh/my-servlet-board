package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class MemberJdbcDao implements MemberDao{
    private static final MemberJdbcDao instance = new MemberJdbcDao();
    public static MemberJdbcDao getInstance(){
        return instance;
    }
    private MemberJdbcDao(){}

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
    public Member getMember(String userid) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Member member = new Member();

        try {
            connection = connectDB();
            String sql = "SELECT * FROM member where login_id = " + "'" + userid + "'";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String loginId = rs.getString("login_id");
                String pw = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");

                Member member1 = new Member(id, loginId, pw, name, email);
                return member1;
            }
        } catch (Exception e) {

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return member;
    }

    @Override
    public void saveMember(Member member) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = connectDB();
            String sql = "INSERT INTO member (login_id,password,name,email) VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,member.getLoginId());
            ps.setString(2,member.getPassword());
            ps.setString(3,member.getName());
            ps.setString(4,member.getEmail());
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
