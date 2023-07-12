package com.flab.jojoboard.board.Reply.dao;

import com.flab.jojoboard.board.Reply.domain.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    /**
     * 댓글 생성
     *
     * @param reply
     */
    void insertReply(@Param("reply") Reply reply);

    /**
     * 댓글 가져오기
     *
     * @param replyId
     * @return
     */
    Reply findReplyById(int replyId);

    /**
     * 댓글 수정
     *
     * @param reply
     */
    void updateReply(@Param("reply") Reply reply);

    /**
     * 댓글 삭제
     *
     * @param replyId
     */
    void deleteReply(int replyId);

    /**
     * 게시물 댓글 리스트 가져오기
     * @param postId
     * @return List<Reply>
     */
    List<Reply> selectReplyListByPostId(int postId);

    Reply selectReplyById(@Param("id") int replyId);
}
