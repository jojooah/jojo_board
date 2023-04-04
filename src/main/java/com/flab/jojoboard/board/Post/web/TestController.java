package com.flab.jojoboard.board.Post.web;

import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


   @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/test/ex")
    public String errorTest() throws ResultCodeException {

        throw new ResultCodeException(
                ResultCode.ERROR_ETC
        );
    }
}
