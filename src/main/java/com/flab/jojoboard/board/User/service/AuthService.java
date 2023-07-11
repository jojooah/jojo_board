package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PostMapper postMapper;

    //private final BoardMapper boardMapper;
    private final LoginService loginService;


    /**
     * 게시물에대한 수정/삭제 권한 확인
     * @param post
     * @return
     * @throws ResultCodeException
     */
    public boolean checkPostAuth(Post post) throws ResultCodeException {
        if(post == null) throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);

        Post findPost = postMapper.selectPostByPostId(post.getId());
        if(findPost.isWrittenByMem() && loginService.isNotLogin()) throw new ResultCodeException(ResultCode.PLEASE_LOGIN);
        if(findPost.isWrittenByMem() && !loginService.getLoginUserInfo().getUserId().equals(findPost.getRegId())) throw new ResultCodeException(ResultCode.NOT_WITTER);
        if(findPost.isNotWrittenByMem() && !findPost.getNonMemPwd().equals(post.getNonMemPwd())) throw new ResultCodeException(ResultCode.WRONG_POST_PWD);

        return true;
    }


    public boolean isAccessPossibleBoardByBoardId(Integer boardId) throws ResultCodeException {

        if(boardId == null) throw new ResultCodeException(ResultCode.NOT_EXIST_BOARD_ID);


        //BoardType boardType = boardMapper.findBoardTypeByBoardId(boardId);

        //PreconditionUtils.invalidCondition(boardType.needToLogin() && loginService.isNotLogin(), "로그인 해주세요.");
        return true;

    }


    public boolean isAccessPossibleBoardByPostId(int postId) {

      /*  PreconditionUtils.invalidCondition(NumberUtils.isNullOrZero(postId), ERROR_CODE.NOT_EXIST_POST_ID.getMessage());

        BoardType boardType = boardMapper.findBoardTypeByPostId(postId);

        PreconditionUtils.invalidCondition(boardType.needToLogin() && loginService.isNotLogin(), "로그인 해주세요.");
        */return true;
    }


    public boolean isReplyPossibleBoardByPostId(int postId) {

        /*PreconditionUtils.invalidCondition(NumberUtils.isNullOrZero(postId), ERROR_CODE.NOT_EXIST_POST_ID.getMessage());

        BoardType boardType = boardMapper.findBoardTypeByPostId(postId);
        return boardType.isReply();
        */
        return true;
    }

    /* public boolean checkReplyAuth(Reply reply) {

       PreconditionUtils.invalidCondition(NumberUtils.isNullOrZero(reply.getId()), ERROR_CODE.NOT_EXIST_REPLY_ID.getMessage());

        Reply findReply = boardMapper.findReplyById(reply.getId());

        PreconditionUtils.invalidCondition(findReply.isWrittenByMem() && loginService.isNotLogin(), "로그인 해주세요.");
        PreconditionUtils.invalidCondition(findReply.isWrittenByMem() && !loginService.getLoginUser().getUserId().equals(findReply.getRegId()), "댓글 작성자만 댓글을 수정할 수 있습니다.");
        PreconditionUtils.invalidCondition(findReply.isNotWrittenByMem() && !reply.getNonMemPw().equals(findReply.getNonMemPw()), "비밀번호가 틀렸습니다.");

        return true;
    }*/
}
