package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;

public interface MemberDao {
    public void saveMember(Member member);

    public Member getMember(String userid);
}
