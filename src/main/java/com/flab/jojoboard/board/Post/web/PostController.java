package com.flab.jojoboard.board.Post.web;

import com.flab.jojoboard.board.Post.domain.Post;
import com.flab.jojoboard.board.Post.domain.dto.PostDTO;
import com.flab.jojoboard.board.Post.service.PostService;
import com.flab.jojoboard.board.User.service.AuthService;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final AuthService authService;

    private final PostService postService;

    @GetMapping("/post/list") //글목록
    public ResponseEntity<ResponseBase<List<Post>>> getPostList(@RequestParam("boardId") int boardId) {
        List<Post> postList = null;
        ResponseBase<List<Post>> responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByBoardId(boardId);
        postList = postService.getPosts(boardId);
        responseBase.setData(postList);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @GetMapping("/post/{postId}") //게시글 가져오기
    public ResponseEntity<ResponseBase<Post>> getPost(@PathVariable("postId") int postId) {
        ResponseBase<Post> responseBase = new ResponseBase<>();

        authService.isAccessPossibleBoardByPostId(postId);
        responseBase.setData(postService.getPost(postId));
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PostMapping("/post")//글쓰기
    public ResponseEntity<ResponseBase<Integer>> insertPost(@RequestBody PostDTO postDTO) {
        ResponseBase<Integer> responseBase = new ResponseBase<>();
        authService.isAccessPossibleBoardByBoardId(postDTO.getBoardId());

        responseBase.setResultCode(ResultCode.SUCCESS);
        responseBase.setData(postService.insertPost(postDTO));

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);

    }

    @DeleteMapping("/post/{post_id}") //게시글 삭제
    public  ResponseEntity<ResponseBase<ResultCode>> deletePost(@RequestBody PostDTO postDTO) {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();
        authService.isAccessPossibleBoardByPostId(postDTO.getId());
        authService.checkPostAuth(postDTO);
        postService.deletePost(postDTO); //권한 체크를 위해 객체를 넣음
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);
    }

    @PatchMapping("/post/{post_id}") //글수정
    public  ResponseEntity<ResponseBase<ResultCode>> updatePost(@RequestBody PostDTO postDTO) {
        ResponseBase<ResultCode> responseBase = new ResponseBase<>();
        authService.isAccessPossibleBoardByPostId(postDTO.getId());
        authService.checkPostAuth(postDTO);
        postService.updatePost(postDTO);//권한 체크를 위해 객체를 넣음
        responseBase.setResultCode(ResultCode.SUCCESS);

        return ResponseEntity.status(responseBase.getResultCode().getHttpStatus()).body(responseBase);

    }

}