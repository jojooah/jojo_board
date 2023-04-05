package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.User.dao.UserMapper;
import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;
    private final JwtService jwtService;

    public void checkIdAndPwd(UserDTO userDTO) throws ResultCodeException {
        if (Objects.isNull(userDTO.getUserId())) throw new ResultCodeException(ResultCode.NEED_USER_ID);
        if (Objects.isNull(userDTO.getPwd())) throw new ResultCodeException(ResultCode.NEED_PWD);

        User findUser = userMapper.selectUserByUserId(userDTO.getUserId());
        if (Objects.isNull(findUser)) throw new ResultCodeException(ResultCode.USER_ID_NOT_EXIST);
        if (!findUser.getPwd().equals(userDTO.getPwd())) throw new ResultCodeException(ResultCode.WRONG_PWD);

    }

    public boolean login(UserDTO userDTO, HttpServletResponse response) throws ResultCodeException {
        checkIdAndPwd(userDTO);

        Cookie cookie = new Cookie("token", jwtService.getAccessToken(userDTO.getUserId()));
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        return true;
    }
}
