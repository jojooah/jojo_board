package com.flab.jojoboard.board.Post.service;

import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.board.User.service.LoginService;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final LoginService loginService;

    /** 게시글 가져오기*/
    public Post getPost(int postId)  {
        Post post = postMapper.selectPostByPostId(postId);
        if (Objects.isNull(post)) throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);

        return post;
    }

    /**게시글 목록 가져오기*/
    public List<Post> getPosts(Integer boardId) {
        if( Objects.isNull(boardId)) throw new ResultCodeException(ResultCode.NOT_EXIST_BOARD_ID);
        List<Post> postList = postMapper.selectPostsByBoardId(boardId);

        return postList;
    }

    /**게시글 작성*/
    @Transactional
    public int insertPost(PostDTO postDTO) {
        if (ObjectUtils.isEmpty(postDTO.getTitle())) throw new ResultCodeException(ResultCode.NEED_POST_TITLE);
        if (ObjectUtils.isEmpty(postDTO.getContent())) throw new ResultCodeException(ResultCode.NEED_REPLY_CONTENT);

        boolean isLogin=loginService.isLogin();

        if(!isLogin){ //로그인이 되어있지않은 상태
            if(postDTO.getNonMemNick().isEmpty()) throw new ResultCodeException(ResultCode.NOT_EXIST_NICKNAME);
            if(postDTO.getNonMemPwd().isEmpty()) throw new ResultCodeException(ResultCode.NEED_PWD);
            postDTO.setWrittenByMem(false);
        }else{
            postDTO.setRegId(loginService.getLoginUserInfo().getUserId());
            postDTO.setWrittenByMem(true);
        }

        postMapper.insertPost(postDTO);
        return postDTO.getId();
    }

    /**게시글 삭제*/
    @Transactional
    public void deletePost(PostDTO postDTO) {
        Post post = postMapper.selectPostByPostId(postDTO.getId());
        if (Objects.isNull(post)) throw new ResultCodeException(ResultCode.POST_ALREADY_DELETED);

        postMapper.deletePostByPostId(postDTO.getId());

    }

    /**게시글 수정*/
    @Transactional
    public void updatePost(PostDTO postDTO) {
        if (Objects.isNull(postMapper.selectPostByPostId(postDTO.getId())))
            throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);
        if (ObjectUtils.isEmpty(postDTO.getTitle())) throw new ResultCodeException(ResultCode.NEED_POST_TITLE);
        if (ObjectUtils.isEmpty(postDTO.getContent())) throw new ResultCodeException(ResultCode.NEED_REPLY_CONTENT);

        postMapper.updatePost(postDTO);
    }

    /**
     * 조회수 증가
     * @param postId
     * @throws Exception
     */
//    @Transactional
//    public void postCount(int postId) throws Exception{
//        boardMapper.updateCountByPostId(postId);
//    }
}
