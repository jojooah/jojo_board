package com.flab.jojoboard.board.Post.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;


@Data
@Alias(value = "Post")
public class Post{
    int id;                             //시퀀스
    private String title;               //제목
    private String content;             //내용
    private int boardId;                //게시판 아이디
    private boolean writtenByMem;       //회원작성여부
    private String nonMemNick;          //비회원 닉네임
    private String nonMemPwd;           //비회원 비밀번호
    private String boardType;           //게시판 타입
    private String regId;               //작성자
    private String regDate;             //작성일
    private String updDate;             //수정일
}
