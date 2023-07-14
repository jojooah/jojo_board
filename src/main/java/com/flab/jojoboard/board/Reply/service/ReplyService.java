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
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {

    private final BoardMapper boardMapper;
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
    public void updateReply(Reply reply) { //todo 프론트에서 넘어오는 객체들 id null인지 확인해야하는데 그러면 다 Integer타입으로 선언??
       // if(NumberUtils.)
       // PreconditionUtils.invalidCondition(NumberUtils.isNullOrZero(reply.getId()), ERROR_CODE.NOT_EXIST_REPLY_ID.getMessage());
       // PreconditionUtils.invalidCondition(StringUtils.isEmpty(reply.getContent()),  ERROR_CODE.NOT_EXIST_CONTENT.getMessage());
       // boardMapper.updateReply(reply);
    }

    public void deleteReply(Reply reply) {
        // PreconditionUtils.invalidCondition(NumberUtils.isNullOrZero(reply.getId()), ERROR_CODE.NOT_EXIST_REPLY_ID.getMessage());
        // PreconditionUtils.invalidCondition(StringUtils.isEmpty(reply.getContent()),  ERROR_CODE.NOT_EXIST_CONTENT.getMessage());
        // boardMapper.updateReply(reply);
    }
    //public List<Reply> getReplyList(int postId) {
        //int total = boardMapper.countReplyTotalByPostId(postId);
        //if (total == 0) {
        //    return new Paging<>();
        //}

        //List<Reply> replies = boardMapper.findReplyList(postId);

        //replies.forEach(parent ->
        //        replies.stream()
        //                .filter(child -> parent.getId() == child.getParentReplyId())
        //                .forEach(child -> parent.getReplyList().add(child))
        //);

       // List<Reply> result = replies.stream().filter(reply -> reply.getParentReplyId() == 0).collect(Collectors.toList());
       // return new Paging<>(total, result, pageable);
   // }
}
