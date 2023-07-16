package com.flab.jojoboard.common.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {

    SUCCESS(1000, "성공", HttpStatus.OK),

    //1000번대 유저에러
    NEED_USER_ID(1001, "아이디를 입력하세요.", HttpStatus.BAD_REQUEST),
    NEED_PWD(1002, "비밀번호를 입력하세요", HttpStatus.BAD_REQUEST),
    USER_ID_NOT_EXIST(1003, "가입되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST),
    WRONG_PWD(1004, "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    PLEASE_LOGIN(1005, "로그인 해주세요.", HttpStatus.BAD_REQUEST),
    USER_ID_ALREADY_EXIST(1006, "이미 가입된 아이디입니다", HttpStatus.CONFLICT),
    NEED_NICK_NAME(1007, "닉네임을 입력하세요.", HttpStatus.BAD_REQUEST),
    NICK_NAME_ALREADY_EXIST(1008, "이미 존재하는 닉네임입니다", HttpStatus.CONFLICT),
    NEED_NEW_PWD(1009, "변경할 비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST),
    NEED_NEW_PWD_FOR_CHECK(1010, "비밀번호 체크를 위해 한번 더 입력해 주세요.", HttpStatus.BAD_REQUEST),
    NEED_OLD_PWD(1011, "기존 비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST),
    NEW_PWD_IS_DIFFERENT_FROM_NEW_PWD_FOR_CHECK(1012, "변경할 비밀번호가 서로 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_CORRECT_OLD_PWD(1013, "기존 비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST),
    ALREADY_USED_PWD(1014, "기존 비밀번호는 사용하실 수 없습니다.", HttpStatus.BAD_REQUEST),
    PWD_IS_DIFFERENT_FROM_PWD_FOR_CHECK(1015, "비밀번호가 서로 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_EMAIL_AUTH(1016, "이메일 인증을 진행해주세요.", HttpStatus.BAD_REQUEST),
    NOT_CORRECT_EMAIL_KEY(1017, "올바르지 않은 인증키입니다.", HttpStatus.BAD_REQUEST),
    NEED_EMAIL(1018, "이메일을 입력하세요", HttpStatus.BAD_REQUEST),
    WRONg_URL(1019, "올바르지 않은 url입니다", HttpStatus.BAD_REQUEST),
    NO_PATHVARIABLE(1020, "path variable이 없습니다", HttpStatus.BAD_REQUEST),
    NOT_WITTER(1020,"게시물 작성자가 아닙니다.",HttpStatus.BAD_REQUEST),

    //2000번대 게시글 관련 에러
    POST_NOT_EXISTS(2000, "해당 글이 존재하지 않습니다.", HttpStatus.NO_CONTENT),
    POSTLIST_NOT_EXISTS(2001, "해당 게시판에 작성된 글이 없습니다.", HttpStatus.NO_CONTENT),
    FAIL_INSERT_POST(2003, "게시글 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NEED_POST_TITLE(2004, "게시글 제목을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    NEED_POST_CONTENT(2005, "게시글 내용을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    POST_ALREADY_DELETED(2006, "이미 삭제된 게시글입니다.", HttpStatus.NO_CONTENT),
    WRONG_POST_PWD(2007,"비밀번호가 틀렸습니다.",HttpStatus.BAD_REQUEST),
    NOT_EXIST_BOARD_ID(2008,"게시판 ID가 없습니다.",HttpStatus.BAD_REQUEST),
    NOT_EXIST_POST_ID(2009,"게시글 ID가 없습니다",HttpStatus.BAD_REQUEST),

    //3000번대 댓글 관련 에러
    NEED_REPLY_CONTENT(3001, "댓글 내용을 입력해 주세요", HttpStatus.BAD_REQUEST),
    NOT_EXIST_REPLY(3002,"댓글 객체가 없습니다.",HttpStatus.BAD_REQUEST),
    NOT_REPLY_WRITTER(3003,"댓글 작성자만 댓글을 수정/삭제할 수 있습니다",HttpStatus.BAD_REQUEST),
    WRONG_REPLY_PWD(3004,"비밀번호가 틀렸습니다.",HttpStatus.BAD_REQUEST),
    NOT_EXSIT_REPLY_ID (3005,"댓글 ID가 없습니다",HttpStatus.BAD_REQUEST),
    NOT_EXIST_REPLY_CONTENT(3006,"댓글 내용을 입력해주세요",HttpStatus.BAD_REQUEST),
    ERROR_ETC(9999, "기타 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String msg;
    private HttpStatus httpStatus;

    ResultCode(int code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
