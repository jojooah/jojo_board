package com.flab.jojoboard.board.Reply.service;

import com.flab.jojoboard.board.Board.dao.BoardMapper;
import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.board.Reply.dao.ReplyMapper;
import com.flab.jojoboard.board.Reply.domain.Reply;
import com.flab.jojoboard.board.User.service.LoginService;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {

    private final PostMapper postMapper;
    private final LoginService loginService;
    private final ReplyMapper replyMapper;

    @Transactional
    public void insertReply(Reply reply) throws ResultCodeException {
        if(postMapper.selectPostByPostId(reply.getPostId())==null) throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);
        if(!StringUtils.hasText(reply.getContent())) throw new ResultCodeException(ResultCode.NEED_REPLY_CONTENT);
        if(loginService.isNotLogin() && !StringUtils.hasText(reply.getNonMemNick())) throw new ResultCodeException(ResultCode.NEED_NICK_NAME);
        if(loginService.isNotLogin() && !StringUtils.hasText(reply.getNonMemPw())) throw new ResultCodeException(ResultCode.NEED_PWD);

        reply.setWrittenByMem(loginService.isLogin());
        replyMapper.insertReply(reply);
    }


    @Transactional
    public void updateReply(Reply reply) throws ResultCodeException{ //todo 프론트에서 넘어오는 객체들 id null인지 확인해야하는데 그러면 다 Integer타입으로 선언??
       if(reply.getId() == null) throw new ResultCodeException(ResultCode.NOT_EXSIT_REPLY_ID);
       if(StringUtils.hasText(reply.getContent())) throw new ResultCodeException(ResultCode.NOT_EXIST_REPLY_CONTENT);
       replyMapper.updateReply(reply);
    }

    public void deleteReply(Reply reply) {
        if(reply.getId() == null) throw new ResultCodeException(ResultCode.NOT_EXSIT_REPLY_ID);
        replyMapper.updateReply(reply);
    }

    public List<Reply> getReplyList(Integer postId) {
        if(postId == null) throw new ResultCodeException(ResultCode.NOT_EXIST_POST_ID);
        return replyMapper.selectReplyListByPostId(postId);
    }
}
