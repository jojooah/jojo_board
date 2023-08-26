package com.flab.jojoboard.board.User.web;

import com.flab.jojoboard.board.User.domain.dto.MailAuthDTO;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.board.User.service.UserService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user") //회원가입
    public ResponseEntity<ResponseBase<ResultCode>> insertUser(@RequestBody UserDTO userDTO) {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();

        userService.insertUser(userDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PostMapping("/user/id")
    public ResponseEntity<ResponseBase<ResultCode>> checkUserId(@RequestBody Map<String, String> userId)  {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();

        userService.isExistUserByUserId(userId.get("userId"));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PostMapping("/user/nickname")
    public ResponseEntity<ResponseBase<ResultCode>> checkNickName(@RequestBody Map<String, String> nickName)  {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();

        userService.isExistUserByNickName(nickName.get("nickName"));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PatchMapping("/user/pwd")
    public ResponseEntity<ResponseBase<ResultCode>> changePwd(@RequestBody UserDTO userDTO)  {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();

        userService.changePwd(userDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @GetMapping("/user/mailAuth")
    public ResponseEntity<ResponseBase<ResultCode>> mailAuth(@RequestParam("email") String email, @RequestParam("emailKey") String emailKey) {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();

        MailAuthDTO mailAuthDTO = new MailAuthDTO();
        mailAuthDTO.setEmail(email);
        mailAuthDTO.setEmailKey(emailKey);
        userService.updateEmailAuth(mailAuthDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }


}
