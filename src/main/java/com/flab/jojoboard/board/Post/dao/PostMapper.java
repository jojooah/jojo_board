package com.flab.jojoboard.board.Post.dao;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
   /***
    * 게시글 조회
    * @param postId
    * @return
    */
   Post selectPostByPostId(@Param("postId") int postId);

   /**
    * 게시판의 게시글 모두 조회
    * @param boardId
    * @return
    */
   List<Post> selectPostsByBoardId(@Param("boardId") int boardId);

   /**
    * 게시글 작성
    * @param postDTO
    */
   int insertPost(@Param("postDTO") PostDTO postDTO);

   /**
    * 게시글 수정
    * @param postDTO
    */
   void updatePost(@Param("postDTO") PostDTO postDTO);

   /**
    * 게시글 삭제
    * @param postId
    */
   void deletePostByPostId(@Param("postId") int postId);

   /**
    * 게시물의 댓글 수
    *
    * @param postId
    * @return int
    */
   int countReplyTotalByPostId(int postId);

}

