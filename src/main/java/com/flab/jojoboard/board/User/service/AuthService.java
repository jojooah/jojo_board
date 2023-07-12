package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.Board.dao.BoardMapper;
import com.flab.jojoboard.board.Board.domain.BoardType;
import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.board.Reply.dao.ReplyMapper;
import com.flab.jojoboard.board.Reply.domain.Reply;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PostMapper postMapper;
    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;
    private final LoginService loginService;


    /**
     * 게시물에대한 수정/삭제 권한 확인
     * @param post
     * @return
     * @throws ResultCodeException
     */
    public boolean checkPostAuth(PostDTO post) throws ResultCodeException {
        if(post == null) throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);

        Post findPost = postMapper.selectPostByPostId(post.getId());
        if(findPost.isWrittenByMem() && loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);
        if(findPost.isWrittenByMem() && !loginService.getLoginUserInfo().getUserId().equals(findPost.getRegId())) throw new ResultCodeException(ResultCode.NOT_WITTER);
        if(findPost.isNotWrittenByMem() && !findPost.getNonMemPwd().equals(post.getNonMemPwd())) throw new ResultCodeException(ResultCode.WRONG_POST_PWD);

        return true;
    }

    /***
     * 접근 가능한 게시판인지 체크한다
     * @param boardId
     * @return
     * @throws ResultCodeException
     */
    public boolean isAccessPossibleBoardByBoardId(Integer boardId) throws ResultCodeException {
        if(boardId == null) throw new ResultCodeException(ResultCode.NOT_EXIST_BOARD_ID);

        BoardType boardType = boardMapper.selectBoardTypeByBoardId(boardId);
        if(boardType.needToLogin() && loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        return true;
    }

    /**
     * 접근 가능한 게시판인지 체크한다
     * @param postId
     * @return
     */
    public boolean isAccessPossibleBoardByPostId(Integer postId) {
        if(postId == null) throw new ResultCodeException(ResultCode.NOT_EXIST_POST_ID);

        BoardType boardType = boardMapper.selectBoardTypeByPostId(postId);
        if(boardType.needToLogin() && loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);

        return true;
    }

    /**
     * 댓글에대한 수정/삭제권한 확인
     * @param reply
     * @return
     * @throws ResultCodeException
     */
     public boolean checkReplyAuth(Reply reply) throws ResultCodeException {
        if(reply == null) throw new ResultCodeException(ResultCode.NOT_EXIST_REPLY);

        Reply findReply = replyMapper.selectReplyById(reply.getId());

        if(findReply.isWrittenByMem() && loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);
        if(findReply.isWrittenByMem() && !loginService.getLoginUserInfo().getUserId().equals(findReply.getRegId())) throw new ResultCodeException(ResultCode.NOT_REPLY_WRITTER);
        if(findReply.isNotWrittenByMem() && !reply.getNonMemPw().equals(findReply.getNonMemPw())) throw new ResultCodeException(ResultCode.WRONG_REPLY_PWD);

        return true;
    }
}
