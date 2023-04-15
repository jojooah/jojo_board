package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.User.Mail.MailService;
import com.flab.jojoboard.board.User.dao.UserMapper;
import com.flab.jojoboard.board.User.domain.dto.LoginUser;
import com.flab.jojoboard.board.User.domain.dto.MailAuthDTO;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final LoginService loginService;
    private final MailService mailService;

    /** 회원가입 */
    @Transactional
    public void insertUser(UserDTO userDTO) {
        isExistUserByUserId(userDTO.getUserId());

        if (userDTO.getPwd() == null || userDTO.getPwd().isEmpty()) throw new ResultCodeException(ResultCode.NEED_PWD);
        if (userDTO.getCheckPwd() == null || userDTO.getCheckPwd().isEmpty()) throw new ResultCodeException(ResultCode.NEED_NEW_PWD_FOR_CHECK);
        if (!userDTO.getPwd().equals(userDTO.getCheckPwd())) throw new ResultCodeException(ResultCode.PWD_IS_DIFFERENT_FROM_PWD_FOR_CHECK);
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) throw new ResultCodeException(ResultCode.NEED_EMAIL);
        isExistUserByNickName(userDTO.getNickName());

        String emailKey = UUID.randomUUID().toString();
        userDTO.setEmailKey(emailKey);
        userMapper.insertUser(userDTO);

        String title = "회원가입 인증 메일입니다.";
        String content = "http://localhost:8080/user/mailAuth?email="+userDTO.getEmail()+"&emailKey="+emailKey +"\n인증 링크를 클릭하세요.";
        mailService.send(userDTO.getEmail(),title,content);
    }

    /** 아이디 중복확인 */
    @Transactional
    public void isExistUserByUserId(String userId)  {
        if (userId == null || userId.isEmpty()) throw new ResultCodeException(ResultCode.NEED_USER_ID);
        if (userMapper.countUserByuserId(userId) > 0 ) throw new ResultCodeException(ResultCode.USER_ID_ALREADY_EXIST);

    }

    /** 닉네임 중복확인 */
    @Transactional
    public void isExistUserByNickName(String nickName) {
        if (nickName == null || nickName.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NICK_NAME);
        if (userMapper.countUserByNickName(nickName) > 0) throw new ResultCodeException(ResultCode.NICK_NAME_ALREADY_EXIST);
    }

    /** 비밀번호 변경 */
    @Transactional
    public void changePwd(UserDTO userDTO) throws ResultCodeException {
        LoginUser loginUser = loginService.getLoginUserInfo();
        if(Objects.isNull(loginUser)) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        String newPwd = userDTO.getNewPwd();
        String checkPwd = userDTO.getCheckPwd();
        String oldPwd = userDTO.getOldPwd();
        String findPwd = userMapper.selectPwdByUserId(loginService.getLoginUserInfo().getUserId());

        if (newPwd == null || newPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NEW_PWD);
        if (checkPwd == null || checkPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_NEW_PWD_FOR_CHECK);
        if (oldPwd == null || oldPwd.isEmpty()) throw new ResultCodeException(ResultCode.NEED_OLD_PWD);
        if(!oldPwd.equals(findPwd)) throw new ResultCodeException(ResultCode.NOT_CORRECT_OLD_PWD);
        if (!newPwd.equals(checkPwd)) throw new ResultCodeException(ResultCode.NEW_PWD_IS_DIFFERENT_FROM_NEW_PWD_FOR_CHECK);


        userMapper.updatePwd(userDTO);
    }

    /** 닉네임 변경 */
    @Transactional
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

    /** 회원탈퇴 */
    @Transactional
    public void deleteUser(String pwd) {
        if(loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        String userId = loginService.getLoginUserInfo().getUserId();
        String findPwd = userMapper.selectPwdByUserId(userId);
        if (!findPwd.equals(pwd)) throw new ResultCodeException(ResultCode.WRONG_PWD);
        userMapper.deleteUserByUserId(userId);
    }

    /** 이메일 인증 */
    @Transactional
    public void updateEmailAuth(MailAuthDTO mailAuthDTO)  {
        String findKey=userMapper.selectEmailKeyByUserEmailAndEmailKey(mailAuthDTO);
        if (findKey == null || findKey == "") throw new ResultCodeException(ResultCode.NOT_CORRECT_EMAIL_KEY);

        userMapper.updateEmailAuth(mailAuthDTO);
    }

    /** 이메일 인증 여부 확인 */
    @Transactional
    public void checkEmailAuth(String userId) {
        if(userMapper.countByEmailAuth(userId) == 0) { throw new ResultCodeException(ResultCode.NOT_EMAIL_AUTH); }
    }
}
