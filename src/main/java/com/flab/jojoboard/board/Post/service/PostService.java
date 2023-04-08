package com.flab.jojoboard.board.Post.service;

import com.flab.jojoboard.board.Post.dao.PostMapper;
import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;


    /** 게시글 가져오기*/
    public Post getPost(int postId) throws ResultCodeException {
        Post post = postMapper.selectPostByPostId(postId);
        if (Objects.isNull(post)) throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);

        return post;
    }

    /**게시글 목록 가져오기*/
    public List<Post> getPosts(int boardId) throws ResultCodeException {
        List<Post> postList = postMapper.selectPostsByBoardId(boardId);
        if (postList.size() == 0) throw new ResultCodeException(ResultCode.POSTLIST_NOT_EXISTS);

        return postList;
    }

    /**게시글 작성*/
    @Transactional
    public void insertPost(PostDTO postDTO) throws ResultCodeException {
        if (ObjectUtils.isEmpty(postDTO.getTitle())) throw new ResultCodeException(ResultCode.NEED_POST_TITLE);
        if (ObjectUtils.isEmpty(postDTO.getContent())) throw new ResultCodeException(ResultCode.NEED_REPLY_CONTENT);

        postMapper.insertPost(postDTO);
    }

    /**게시글 삭제*/
    @Transactional
    public void deletePost(PostDTO postDTO) throws ResultCodeException {
        Post post = postMapper.selectPostByPostId(postDTO.getId());
        if (Objects.isNull(post)) throw new ResultCodeException(ResultCode.POST_ALREADY_DELETED);

        postMapper.deletePostByPostId(postDTO.getId());

    }

    /**게시글 수정*/
    @Transactional
    public void updatePost(PostDTO postDTO) throws ResultCodeException {
        if (Objects.isNull(postMapper.selectPostByPostId(postDTO.getId())))
            throw new ResultCodeException(ResultCode.POST_NOT_EXISTS);
        if (ObjectUtils.isEmpty(postDTO.getTitle())) throw new ResultCodeException(ResultCode.NEED_POST_TITLE);
        if (ObjectUtils.isEmpty(postDTO.getContent())) throw new ResultCodeException(ResultCode.NEED_REPLY_CONTENT);

        postMapper.updatePost(postDTO);
    }
}
