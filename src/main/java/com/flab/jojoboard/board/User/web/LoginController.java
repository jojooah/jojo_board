package com.flab.jojoboard.board.User.web;

import com.flab.jojoboard.board.User.Mail.MailService;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.board.User.service.LoginService;
import com.flab.jojoboard.board.User.service.UserService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final MailService mailService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO)  {
        ResponseBase responseBase = new ResponseBase<>();
        HttpHeaders header;
        userService.checkEmailAuth(userDTO.getUserId());
        header=loginService.login(userDTO);

        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).headers(header).body(responseBase);

    }

}
