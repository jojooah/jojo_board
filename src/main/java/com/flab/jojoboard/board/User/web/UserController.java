package com.flab.jojoboard.board.User.web;

import com.flab.jojoboard.board.User.domain.dto.MailAuthDTO;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.board.User.service.UserService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseBase insertUser(@RequestBody UserDTO userDTO) throws Exception {
        ResponseBase responseBase = new ResponseBase<>();

        userService.insertUser(userDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @PostMapping("/user/id")
    public ResponseBase checkUserId(@RequestBody Map<String, String> userId) throws Exception {
        ResponseBase responseBase = new ResponseBase<>();

        userService.isExistUserByUserId(userId.get("userId"));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @PostMapping("/user/nickname")
    public ResponseBase checkNickName(@RequestBody Map<String, String> nickName) throws Exception {
        ResponseBase responseBase = new ResponseBase<>();

        userService.isExistUserByNickName(nickName.get("nickName"));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @PatchMapping("/user/pwd")
    public ResponseBase changePwd(@RequestBody UserDTO userDTO) throws Exception {
        ResponseBase responseBase = new ResponseBase<>();

        userService.changePwd(userDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @GetMapping("/user/mailAuth")
    public ResponseBase mailAuth(@RequestParam("email") String email, @RequestParam("emailKey") String emailKey) throws Exception {
        ResponseBase responseBase = new ResponseBase<>();

        MailAuthDTO mailAuthDTO = new MailAuthDTO();
        mailAuthDTO.setEmail(email);
        mailAuthDTO.setEmailKey(emailKey);
        userService.updateEmailAuth(mailAuthDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }


}
