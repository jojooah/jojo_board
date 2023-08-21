package com.flab.jojoboard.board.User.dao;

import com.flab.jojoboard.board.User.domain.User;
import com.flab.jojoboard.board.User.domain.dto.LoginUser;
import com.flab.jojoboard.board.User.domain.dto.MailAuthDTO;
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
     * 회원가입
     * @param userDTO
     */
    void insertUser(@Param("userDTO") UserDTO userDTO);

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

    /**
     * 회원탈퇴
     * @param userId
     */
    void deleteUserByUserId(@Param("userId") String userId );

    /**
     * 이메일 인증
     * @param mailAuthDTO
     */
    void updateEmailAuth(@Param("mailAuthDTO") MailAuthDTO mailAuthDTO);

    /**
     * 이메일 인증여부 확인
     * @param userId
     * @return
     */
    Integer countByEmailAuth(@Param("userId") String userId);

    /**
     * 이메일 키 일치 여부 확인
     * @param mailAuthDTO
     * @return
     */
    String selectEmailKeyByUserEmailAndEmailKey(@Param("mailAuthDTO") MailAuthDTO mailAuthDTO);

    /**
     * 리프레쉬 토큰 유효한지 검증
     * @param userId
     * @param refreshToken
     * @return
     */
    String selectUserByRefreshToken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);

    /**
     * 리프레쉬토큰 저장
     * @param userId
     * @param refreshToken
     */
    void updateRefreshToken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);
}
