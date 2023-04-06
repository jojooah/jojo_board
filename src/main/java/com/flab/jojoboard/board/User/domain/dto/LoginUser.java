package com.flab.jojoboard.board.User.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
    private int id;
    private String userId;
    private String nickName;
    private String email;
}
