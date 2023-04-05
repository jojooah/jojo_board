package com.flab.jojoboard.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResultCode {

    SUCCESS(1000, "성공"),

    //1000번대 유저에러
    NEED_USER_ID(1001,"아이디를 입력하세요."),
    NEED_PWD(1002,"비밀번호를 입력하세요"),
    USER_ID_NOT_EXIST(1002,"가입되지 않은 아이디입니다."),
    WRONG_PWD(1003,"비밀번호가 일치하지 않습니다."),

    //2000번대 게시글 관련 에러
    POST_NOT_EXISTS(2000, "해당 글이 존재하지 않습니다."),
    POSTLIST_NOT_EXISTS(2001, "해당 게시판에 작성된 글이 없습니다."),
    FAIL_INSERT_POST(2003,"게시글 등록에 실패했습니다."),
    NEED_POST_TITLE(2004,"게시글 제목을 입력해 주세요."),
    NEED_POST_CONTENT(2005,"게시글 내용을 입력해 주세요."),
    POST_ALREADY_DELETED(2006, "이미 삭제된 게시글입니다."),

    NEED_REPLY_CONTENT(3001,"댓글 내용을 입력해 주세요"),


    ERROR_ETC(9999, "기타 오류");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
