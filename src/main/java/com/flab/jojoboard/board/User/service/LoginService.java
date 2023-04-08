package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.User.dao.UserMapper;
import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.board.User.domain.dto.LoginUser;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.common.domain.Constants;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.EditorKit;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {


    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    public void checkIdAndPwd(UserDTO userDTO) throws ResultCodeException {
        if (Objects.isNull(userDTO.getUserId())) throw new ResultCodeException(ResultCode.NEED_USER_ID);
        if (Objects.isNull(userDTO.getPwd())) throw new ResultCodeException(ResultCode.NEED_PWD);

        User findUser = userMapper.selectUserByUserId(userDTO.getUserId());
        if (Objects.isNull(findUser)) throw new ResultCodeException(ResultCode.USER_ID_NOT_EXIST);
        if (!findUser.getPwd().equals(userDTO.getPwd())) throw new ResultCodeException(ResultCode.WRONG_PWD);

    }

    /**
     * 로그인
     * 토큰을 만들어 쿠키에 넣고, 브라우저에 보내준다
     */
    public boolean login(UserDTO userDTO, HttpServletResponse response) throws ResultCodeException {
        checkIdAndPwd(userDTO);

        Cookie cookie = new Cookie(Constants.COOKIE_NAME_ACCESS_TOKEN, jwtService.getAccessToken(userDTO.getUserId()));
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return true;
    }

    /** 현재 로그인한 유저정보 가져오기 */
    public LoginUser getLoginUserInfo(){
        Cookie[] cookies = request.getCookies();
        String userId;

        if (Objects.isNull(cookies)) return null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (Constants.COOKIE_NAME_ACCESS_TOKEN.equals(cookieName)) {
                    userId = jwtService.getLoginUserId(cookie.getValue()); //쿠키에 있는 유저 아이디 가져오기
                    return userMapper.selectLoginUserByUserId(userId); //유저 아이디로 유저정보 가져오기
                }
            }
        }

        return null;
    }

    /** 현재 로그인한 상태인지 판별 */
    public boolean isLogin() {
        return !Objects.isNull(getLoginUserInfo());
    }

    /** 현재 로그인한 상태가 아닌지 판별*/
    public boolean isNotLogin() { return !isLogin(); }
}
