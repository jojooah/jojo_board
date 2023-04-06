package com.flab.jojoboard.board.User.web;

import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.board.User.service.UserService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user/id")
    public ResponseBase checkUserId(@RequestBody String userId) {
        ResponseBase responseBase = new ResponseBase<>();

        try {
            userService.isExistUserByUserId(userId);
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

    @PostMapping("/user/nickname")
    public ResponseBase checkNickName(@RequestBody String nickName) {
        ResponseBase responseBase = new ResponseBase<>();

        try {
            userService.isExistUserByNickName(nickName);
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

    @PatchMapping("/user/pwd")
    public ResponseBase changePwd(@RequestBody UserDTO userDTO) {
        ResponseBase responseBase = new ResponseBase<>();

        try {
            userService.changePwd(userDTO);
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

    @PatchMapping("user")
    public ResponseBase changeUserInfo(@RequestBody UserDTO userDTO) {

        ResponseBase responseBase = new ResponseBase<>();

        try {
            userService.changeNickName(userDTO);
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

}
