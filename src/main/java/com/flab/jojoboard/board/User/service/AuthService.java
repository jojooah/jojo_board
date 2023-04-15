package com.flab.jojoboard.board.User.service;

import com.flab.jojoboard.board.Post.dao.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PostMapper postMapper;

    public boolean isAccessable(int boardId) throws Exception {


        return false;
    }


    public boolean checkReplyAuth(int replyId) {
        return false;
    }


    public boolean checkPostAuth(int postId) {
        return false;
    }
}
