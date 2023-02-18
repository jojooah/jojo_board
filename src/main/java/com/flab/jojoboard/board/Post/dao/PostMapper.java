package com.flab.jojoboard.board.Post.dao;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
   Post selectPostByPostId(@Param("postId") int postId);
   List<Post> selectPostsByBoardId(@Param("boardId") int boardId);
   void insertPost(@Param("postDTO") PostDTO postDTO);
   void updatePost(@Param("postDTO") PostDTO postDTO);
   void deletePostByPostId(@Param("postId") int postId);
}

