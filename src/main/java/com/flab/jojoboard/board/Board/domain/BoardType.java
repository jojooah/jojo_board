package com.flab.jojoboard.board.Board.domain;

import com.flab.jojoboard.common.domain.Common;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias(value = "BoardType_")
public class BoardType extends Common {
    private int id;
    private boolean anonymouse;
    private boolean nonMem;
    private boolean reply;

    public boolean needToLogin(){
        return !nonMem;
    }

    public boolean notNeedToLogin(){
        return !needToLogin();
    }


}