package com.flab.jojoboard.board.User.dao;

import com.flab.jojoboard.board.User.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectUserByUserId(@Param("userId") String userId);
}
