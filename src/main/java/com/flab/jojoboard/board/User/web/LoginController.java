package com.flab.jojoboard.board.User.web;

import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.board.User.service.LoginService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseBase login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        ResponseBase responseBase = new ResponseBase<>();

        try {
            loginService.login(userDTO, response);
            responseBase.setResultCode(ResultCode.SUCCESS);

        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
            responseBase.setResultCode(ResultCode.ERROR_ETC);
            log.error(e.getMessage());
        }
        return responseBase;

    }

    @GetMapping("/login")
    public ResponseBase login() {
        ResponseBase responseBase = new ResponseBase<>();

        responseBase.setData(loginService.getLoginUserInfo());
        responseBase.setResultCode(ResultCode.SUCCESS);
        return responseBase;

    }
}
