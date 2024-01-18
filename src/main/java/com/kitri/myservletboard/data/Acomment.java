package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Acomment {
    private Long id;
    private Long board_id;
    private Long member_id;
    private String content;
    private LocalDateTime createdAt;
    //다른 테이블 확인시 필요정보
    private String writer;
    private Long memberId;

    public Acomment(){};

    public Acomment(Long board_id, Long member_id, String content) {
        this.board_id = board_id;
        this.member_id = member_id;
        this.content = content;
    }

    public Acomment(Long id, Long board_id, String content, String writer, LocalDateTime createdAt, Long memberId) {
        this.id = id;
        this.board_id = board_id;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.memberId = memberId;
    }

    public Acomment(Long id, Long board_id, Long member_id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.board_id = board_id;
        this.member_id = member_id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
