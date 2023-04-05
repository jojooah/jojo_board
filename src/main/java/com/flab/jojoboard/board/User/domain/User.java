package com.flab.jojoboard.board.User.domain;

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
@Alias(value = "User")
public class User extends Common {
    private int id;
    private String userId;
    private String pwd;
    private String nickName;
    private String email;
}
