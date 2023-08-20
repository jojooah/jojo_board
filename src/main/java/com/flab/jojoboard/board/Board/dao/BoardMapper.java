package com.flab.jojoboard.board.Board.dao;

import com.flab.jojoboard.board.Board.domain.Board;
import com.flab.jojoboard.board.Board.domain.BoardType;
import com.flab.jojoboard.board.Post.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시판 리스트
     *
     * @return List<Board>
     */
    List<Board> selectBoardList();

    /**
     * 게시판 별 게시물 목록
     *
     * @param boardId
     * @return List<Post>
     */
    List<Post> findPostListByBoardId(@Param("boardId") int boardId);

    /**
     * 게시판 타입 가져오기
     *
     * @param boardId
     * @return
     */
    BoardType selectBoardTypeByBoardId(@Param("boardId") int boardId);

    /**
     * 게시판 게시물 수
     *
     * @param boardId
     * @return int
     */
    int countPostTotalByBoardId(int boardId);

    /**
     * 게시판 타입 가져오기
     * @param postId
     * @return BoardType
     */
    BoardType selectBoardTypeByPostId(int postId);


}
