package com.flab.jojoboard.board.Post.domain.dto;

import com.flab.jojoboard.common.domain.Common;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO extends Common {
    private int id;
    private int boardId;
    private String title;
    private String content;
    private String nonMemNick;
    private String nonMemPwd;
    private boolean writtenByMem;

}
