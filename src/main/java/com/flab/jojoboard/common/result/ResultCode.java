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
    PLEASE_LOGIN(1004,"로그인 해주세요."),
    USER_ID_ALREADY_EXIST(1005,"이미 가입된 아이디입니다"),
    NEED_NICK_NAME(1006,"닉네임을 입력하세요."),
    NICK_NAME_ALREADY_EXIST(1005,"이미 존재하는 닉네임입니다"),
    NEED_NEW_PWD(1006,"변경할 비밀번호를 입력하세요."),
    NEED_NEW_PWD_FOR_CHECK(1007,"비밀번호 체크를 위해 한번 더 입력해 주세요."),
    NEED_OLD_PWD(1008,"기존 비밀번호를 입력하세요."),
    NEW_PWD_IS_DIFFERENT_FROM_NEW_PWD_FOR_CHECK(1009,"변경할 비밀번호가 서로 일치하지 않습니다."),
    NOT_CORRECT_OLD_PWD(1010,"기존 비밀번호가 틀렸습니다."),
    ALREADY_USED_PWD(1011,"기존 비밀번호는 사용하실 수 없습니다."),
    PWD_IS_DIFFERENT_FROM_PWD_FOR_CHECK(1012,"비밀번호가 서로 일치하지 않습니다."),
    NOT_EMAIL_AUTH(1013,"이메일 인증을 진행해주세요."),
    NOT_CORRECT_EMAIL_KEY(1014,"올바르지 않은 인증키입니다."),
    NEED_EMAIL(1015,"이메일을 입력하세요"),
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
