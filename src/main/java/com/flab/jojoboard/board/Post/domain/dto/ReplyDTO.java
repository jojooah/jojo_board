package com.flab.jojoboard.board.Post.domain.dto;

import com.flab.jojoboard.common.domain.DTO;

public class ReplyDTO extends DTO {
    private int id;
    private int postId;
    private String content;
    private String nonMemNick;
    private String nonMemPwd;

}
