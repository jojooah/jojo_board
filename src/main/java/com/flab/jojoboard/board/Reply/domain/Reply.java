package com.flab.jojoboard.board.Reply.domain;

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
@Alias(value = "Reply")
public class Reply extends Common {
    private int id;
    private String content;
    private String nonMemNick;
    private String nonMemPw;
    private  String nickName;
    private int postId;
    private int parentReplyId;
    private boolean writtenByMem;

    public boolean isNotWrittenByMem(){
        return !writtenByMem;
    }
}
