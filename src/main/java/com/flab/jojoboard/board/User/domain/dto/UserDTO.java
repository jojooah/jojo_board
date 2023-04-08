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
    private String emailKey;

    private String newNickName; //[닉네임 변경] 변경할 닉네임
    private String newPwd; // [비밀번호 변경] 변경할 비밀번호
    private String checkPwd; //[비밀번호 변경] 변경할 비밀번호 한번 더 확인
    private String oldPwd; //[비밀번호 변경]이전 비밀번호

}
