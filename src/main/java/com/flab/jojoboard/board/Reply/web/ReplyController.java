package com.flab.jojoboard.board.Reply.web;

import com.flab.jojoboard.board.Reply.domain.Reply;
import com.flab.jojoboard.board.Reply.service.ReplyService;
import com.flab.jojoboard.board.User.service.AuthService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final AuthService authService;
    private final ReplyService replyService;

    @PostMapping("/board/{board_id}/post/{post_id}/reply") //댓글쓰기
    public ResponseBase insertReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        replyService.insertReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);
        return responseBase;

    }

    @PatchMapping("/board/{board_id}/post/{post_id}/reply") //댓글수정R
    public ResponseBase updateReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        authService.checkReplyAuth(reply);
        replyService.updateReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @DeleteMapping("/board/{board_id}/post/{post_id}/reply") //댓글삭제
    public ResponseBase deleteReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        authService.checkReplyAuth(reply);
        replyService.deleteReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }


}
