package com.flab.jojoboard.board.User.domain.dto;

import com.flab.jojoboard.common.domain.Common;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends Common {
    private int id;
    private String userId;
    private String pwd;
    private String nickName;
    private String email;
}
