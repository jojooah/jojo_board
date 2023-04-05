package com.flab.jojoboard.board.Post.domain;

import com.flab.jojoboard.common.domain.Common;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Alias(value = "Post")
public class Post extends Common {
    int id;                             //시퀀스
    private String title;               //제목
    private String content;             //내용
    private int boardId;                //게시판 아이디
    private boolean writtenByMem;       //회원작성여부
    private String nonMemNick;          //비회원 닉네임
    private String nonMemPwd;           //비회원 비밀번호
    private String boardType;           //게시판 타입

}
