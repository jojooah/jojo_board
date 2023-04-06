package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.User.dao.UserMapper;
import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.board.User.domain.dto.LoginUser;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final LoginService loginService;

    @Transactional
    public void insertUser(User user) throws ResultCodeException{

    }

    /** 아이디 중복확인 */
    public void isExistUserByUserId(String userId) throws ResultCodeException {
        if (userId == null || userId.isEmpty()) throw new ResultCodeException(ResultCode.NEED_USER_ID);
        if (userMapper.countUserByuserId(userId) > 0 ) throw new ResultCodeException(ResultCode.USER_ID_ALREADY_EXIST);

    }

    /** 닉네임 중복확인 */
    public void isExistUserByNickName(String nickName) throws ResultCodeException {
        if (nickName == null || nickName.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NICK_NAME);
        if (userMapper.countUserByNickName(nickName) > 0) throw new ResultCodeException(ResultCode.NICK_NAME_ALREADY_EXIST);
    }

    /** 비밀번호 변경 */
    public void changePwd(UserDTO userDTO) throws ResultCodeException {
        LoginUser loginUser = loginService.getLoginUserInfo();
        if(Objects.isNull(loginUser)) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        String newPwd = userDTO.getNewPwd();
        String checkNewPwd = userDTO.getNewPwd();
        String oldPwd = userDTO.getOldPwd();
        String findPwd = userMapper.selectPwdByUserId(loginService.getLoginUserInfo().getUserId());

        if (newPwd == null || newPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NEW_PWD);
        if (checkNewPwd == null || checkNewPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NEW_PWD_FOR_CHECK);
        if (oldPwd == null || oldPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_OLD_PWD);
        if (!newPwd.equals(checkNewPwd)) throw new ResultCodeException(ResultCode.NEW_PWD_IS_DIFFERENT_FROM_NEW_PWD_FOR_CHECK);
        if(!oldPwd.equals(findPwd)) throw new ResultCodeException(ResultCode.NOT_CORRECT_OLD_PWD);

        userMapper.updatePwd(userDTO);
    }

    /** 닉네임 변경 */
    public void changeNickName(UserDTO userDTO) throws ResultCodeException {
        LoginUser loginUser = loginService.getLoginUserInfo();
        if(Objects.isNull(loginUser)) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        isExistUserByNickName(userDTO.getNewNickName());//닉네임 중복체크

        String oldPwd = userDTO.getOldPwd();
        String findPwd = userMapper.selectPwdByUserId(loginService.getLoginUserInfo().getUserId());

        if (oldPwd == null || oldPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_OLD_PWD);
        if(!oldPwd.equals(findPwd)) throw new ResultCodeException(ResultCode.NOT_CORRECT_OLD_PWD); //비밀번호 체크

        userMapper.updateNickName(userDTO);
    }


}
