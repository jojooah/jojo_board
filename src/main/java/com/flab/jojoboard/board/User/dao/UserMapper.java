package com.flab.jojoboard.board.User.dao;

import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.board.User.domain.dto.LoginUser;
import com.flab.jojoboard.board.User.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * 유저 정보
     * @param userId String
     * @return User
     */
    User selectUserByUserId(@Param("userId") String userId);

    /**
     * 현재 로그인한 유저 정보
     * @param userId String
     * @return LoginUser
     */
    LoginUser selectLoginUserByUserId(@Param("userId") String userId);

    /**
     * 유저 아이디 중복확인
     * @param userId
     * @return
     */
    Integer countUserByuserId(@Param("userId") String userId);

    /**
     * 유저 닉네임 중복확인
     * @param nickName String
     * @return Integer
     */
    Integer countUserByNickName(@Param("nickName") String nickName);

    /**
     * 유저 비밀번호 가져오기
     * @param userId
     * @return
     */
    String selectPwdByUserId(@Param("userId") String userId);

    /**
     * 비밀번호 변경
     * @param userDTO
     */
    void updatePwd(@Param("userDTO") UserDTO userDTO);

    /**
     * 닉네임 변경
     * @param userDTO
     */
    void updateNickName(@Param("userDTO") UserDTO userDTO);

}
