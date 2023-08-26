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
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {


    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public void checkIdAndPwd(UserDTO userDTO) {
        if (Objects.isNull(userDTO.getUserId()) || userDTO.getUserId() == "") throw new ResultCodeException(ResultCode.NEED_USER_ID);
        if (Objects.isNull(userDTO.getPwd()) || userDTO.getPwd() == "") throw new ResultCodeException(ResultCode.NEED_PWD);

        User findUser = userMapper.selectUserByUserId(userDTO.getUserId());
        if (Objects.isNull(findUser)) throw new ResultCodeException(ResultCode.USER_ID_NOT_EXIST);
        if (!findUser.getPwd().equals(userDTO.getPwd())) throw new ResultCodeException(ResultCode.WRONG_PWD);

    }

    /**
     * 로그인
     * 액세스 토큰을 만들어 헤더에 넣고, 브라우저에 보내준다
     * 레프레쉬토큰을 만들어 쿠키에 넣고, 브라우저에 보내준다
     */
    public HttpHeaders login(UserDTO userDTO) {
        checkIdAndPwd(userDTO);

        issueRefreshToken(userDTO.getUserId()); //리프레쉬 토큰 발급
        return issueAccessToken(userDTO.getUserId()); //액세스 토큰 발급

    }

    HttpHeaders issueAccessToken(String userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtService.createAccessToken(userId)); // acceses token authorization헤더에 담는다
        return headers;
    }

    @Transactional
    void issueRefreshToken(String userId){

        String uuid = UUID.randomUUID().toString();
        userMapper.updateRefreshToken(userId,uuid);

        Cookie cookie = new Cookie(Constants.COOKIE_NAME_REFRESH_TOKEN, jwtService.createRefreshToken(userId+" "+uuid)); //  refresh token은 쿠키에 담는다
        cookie.setMaxAge( 7 * 24 * 60 * 60); //일주일로 설정
        response.addCookie(cookie);

    }

    /** 현재 로그인한 유저정보 가져오기 */
    public LoginUser getLoginUserInfo(){

        String authorizationHeader = request.getHeader("Authorization"); //authorization헤더에서 가져옴

        //access토큰 없으면 리프레쉬토큰 확인
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            String LoginId = checkValidRefreshTokenAndGetUserId();

           if (!Objects.isNull(LoginId)) {
               HttpHeaders headers=issueAccessToken(LoginId);  //리프레쉬 토큰이 유효하면 액세스토큰 발급
               response.setHeader("Authorization",headers.getFirst("Authorization"));
               return userMapper.selectLoginUserByUserId(LoginId); //유저 아이디로 유저정보 가져오기
           }
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring("Bearer ".length());
            return userMapper.selectLoginUserByUserId(jwtService.getLoginUserId(accessToken));
        }
        return null;
    }

    /** 리프레쉬토큰 확인 */
    public String checkValidRefreshTokenAndGetUserId() {
        Cookie[] cookies = request.getCookies();
        String userId = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (Constants.COOKIE_NAME_REFRESH_TOKEN.equals(cookieName)) {
                    String refreshToken = jwtService.getRefreshToken(cookie.getValue()); //쿠키에 있는 리프레쉬 토큰 가져오기
                    String[] tokens = refreshToken.split(" ");
                    if (tokens.length == 2)
                        userId = userMapper.selectUserByRefreshToken(tokens[0], tokens[1]); //리프레쉬 토큰이 디비에 있는지 검사
                }
            }
        }
        return userId;
    }

    /** 현재 로그인한 상태인지 판별 */
    public boolean isLogin() {
        return !Objects.isNull(getLoginUserInfo());
    }

    /** 현재 로그인한 상태가 아닌지 판별*/
    public boolean isNotLogin() { return !isLogin(); }
}
