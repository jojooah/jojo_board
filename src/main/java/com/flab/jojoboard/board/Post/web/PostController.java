package com.flab.jojoboard.board.Post.web;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.board.User.service.AuthService;
import com.flab.jojoboard.board.Post.service.PostService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final AuthService authService;

    private final PostService postService;


    @GetMapping("/post/list") //글목록
    public ResponseBase<List<Post>> getPostList(@RequestParam("boardId") int boardId) {
        List<Post> postList = null;
        ResponseBase responseBase = new ResponseBase<>();

        try {
            postList = postService.getPosts(boardId);
            responseBase.setData(postList);
            responseBase.setResultCode(ResultCode.SUCCESS);
        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
            log.error(e.getMessage());
        }
        return responseBase;

    }

    @GetMapping("/post/{postId}") //게시글 가져오기
    public ResponseBase getPost(@PathVariable("postId") int postId) {
        Post post = null;
        ResponseBase responseBase = new ResponseBase<>();

        try {
            post = postService.getPost(postId);
            responseBase.setData(post);
            responseBase.setResultCode(ResultCode.SUCCESS);
        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
        }
        return responseBase;
    }

    @PostMapping("/post")//글쓰기
    public ResponseBase insertPost(@RequestBody PostDTO postDTO) {
        ResponseBase responseBase = new ResponseBase<>();

        try {
            postService.insertPost(postDTO);
            responseBase.setResultCode(ResultCode.SUCCESS);

        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
            log.error(e.getMessage());
        }
        return responseBase;
    }

    @DeleteMapping("/post/{post_id}")
    public ResponseBase deletePost(@RequestBody PostDTO postDTO) {
        ResponseBase responseBase = new ResponseBase<>();
        try {
            postService.deletePost(postDTO); //권한 체크를 위해 객체를 넣음
            responseBase.setResultCode(ResultCode.SUCCESS);

        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
            log.error(e.getMessage());
        }
        return responseBase;
    }

    @PatchMapping("/post/{post_id}")
    public ResponseBase updatePost(@RequestBody PostDTO postDTO) {
        ResponseBase responseBase = new ResponseBase<>();
        try {
            postService.updatePost(postDTO);//권한 체크를 위해 객체를 넣음
            responseBase.setResultCode(ResultCode.SUCCESS);

        } catch (ResultCodeException e) {
            responseBase.setResultCode(e.getResultCode());
            responseBase.setMessage(e.getResultCode().getMsg());

        } catch (Exception e) {
            responseBase.setMessage("의도하지 못한 에러");
            log.error(e.getMessage());
        }
        return responseBase;
    }

}