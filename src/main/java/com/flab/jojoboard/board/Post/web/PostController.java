package com.flab.jojoboard.board.Post.web;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.board.User.service.AuthService;
import com.flab.jojoboard.board.Post.service.PostService;
import com.flab.jojoboard.common.annotation.ReturnAOP;
import com.flab.jojoboard.common.annotation.ReturnDataAOP;
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

    @ReturnDataAOP
    @GetMapping("/post/list") //글목록
    public ResponseBase<List<Post>> getPostList(@RequestParam("boardId") int boardId) {
        List<Post> postList = null;
        ResponseBase<List<Post>> responseBase = new ResponseBase<>();

        postList = postService.getPosts(boardId);
        responseBase.setData(postList);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;

    }

    @ReturnDataAOP
    @GetMapping("/post/{postId}") //게시글 가져오기
    public  ResponseBase<Post>  getPost(@PathVariable("postId") int postId)  {
        ResponseBase<Post> responseBase = new ResponseBase<>();
        responseBase.setData(postService.getPost(postId));
        responseBase.setResultCode(ResultCode.SUCCESS);
        return responseBase;
    }

    @PostMapping("/post")//글쓰기
    public ResponseBase insertPost(@RequestBody PostDTO postDTO)  {
        ResponseBase responseBase = new ResponseBase<>();

        postService.insertPost(postDTO);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @DeleteMapping("/post/{post_id}")
    public ResponseBase deletePost(@RequestBody PostDTO postDTO)  {
        ResponseBase responseBase = new ResponseBase<>();

        postService.deletePost(postDTO); //권한 체크를 위해 객체를 넣음
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

    @PatchMapping("/post/{post_id}")
    public ResponseBase updatePost(@RequestBody PostDTO postDTO)  {
        ResponseBase responseBase = new ResponseBase<>();

        postService.updatePost(postDTO);//권한 체크를 위해 객체를 넣음
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }

}