package com.flab.jojoboard.board.Reply.web;

import com.flab.jojoboard.board.Reply.domain.Reply;
import com.flab.jojoboard.board.Reply.service.ReplyService;
import com.flab.jojoboard.board.User.service.AuthService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final AuthService authService;
    private final ReplyService replyService;

    @GetMapping("/board/{board_id}/post/{post_id}/reply") //댓글목록
    public ResponseEntity<ResponseBase<List<Reply>>> getReplyList(@PathVariable("board_id") int boardId,
                                                                  @PathVariable("post_id") int postId) {
        ResponseBase<List<Reply>> responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByBoardId(boardId);
        responseBase.setData(replyService.getReplyList(postId));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PostMapping("/board/{board_id}/post/{post_id}/reply") //댓글쓰기
    public  ResponseEntity<ResponseBase> insertReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        replyService.insertReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PatchMapping("/board/{board_id}/post/{post_id}/reply") //댓글수정
    public ResponseEntity<ResponseBase> updateReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        authService.checkReplyAuth(reply);
        replyService.updateReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @DeleteMapping("/board/{board_id}/post/{post_id}/reply") //댓글삭제
    public ResponseEntity<ResponseBase> deleteReply(@RequestBody Reply reply) {
        ResponseBase responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(reply.getPostId());
        authService.checkReplyAuth(reply);
        replyService.deleteReply(reply);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }


}
