package com.flab.jojoboard.board.Post.service;

import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.common.domain.BoardType;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PostMapper postMapper;

    public void isAccessable(int boardId) throws ResultCodeException {
       // String boardType = postMapper.selectBoardTypeByboardId(boardId);
       // if (BoardType.MEMBERONLY.name().equals(boardType) && loginService.isNotLogin()) throws ResultCodeException(ResultCode.NEED_TO_LOGIN);
    }


    public boolean checkReplyAuth(int replyId) {
        return false;
    }


    public boolean checkPostAuth(int postId) {
        return false;
    }
}
