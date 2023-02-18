package com.flab.jojoboard.common.domain;

public enum BoardType {
    FREE("자유게시판"),
    ANONYMOUSE("익명게시판"),
    MEMBERONLY("회원게시판");

    private String boardName;

    BoardType(String boardName) {
        this.boardName = boardName;
    }
}
