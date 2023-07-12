package com.flab.jojoboard.board.Reply.service;

import com.flab.jojoboard.board.Reply.domain.Reply;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ReplyService {
    @Transactional
    public void insertReply(Reply reply) {
       // PreconditionUtils.invalidCondition(Objects.isNull(boardMapper.findPostByPostId(reply.getPostId())), ERROR_CODE.ALREADY_DELETE_POST.getMessage() );
       // PreconditionUtils.invalidCondition( StringUtils.isEmpty(reply.getContent()), ERROR_CODE.NOT_EXIST_CONTENT.getMessage());
       // PreconditionUtils.invalidCondition(loginService.isNotLogin() &&StringUtils.isEmpty(reply.getNonMemNick()), ERROR_CODE.NOT_EXIST_NICKNAME.getMessage());
       // PreconditionUtils.invalidCondition(loginService.isNotLogin() &&StringUtils.isEmpty(reply.getNonMemPw()), ERROR_CODE.NOT_EXIST_PWD.getMessage());

       // reply.setWrittenByMem(loginService.isLogin());
       // boardMapper.insertReply(reply);
    }


    @Transactional
    public void updateReply(Reply reply) {
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
