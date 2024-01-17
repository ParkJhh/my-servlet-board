package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.dao.MemberDao;
import com.kitri.myservletboard.dao.MemberJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;

public class MemberService {
    MemberDao MemberDao = MemberJdbcDao.getInstance();
    //싱글톤으로 하나만 생성
    private MemberService(){};
    private static final MemberService instance = new MemberService();
    public static MemberService getInstance(){
        return instance;
    }

    public void addMemeber(Member member){
        MemberDao.saveMember(member);
    }

    public Member getMember(String userid) {
        return  MemberDao.getMember(userid);
    }
}
