package com.flab.jojoboard.common;

import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {
/*    @RequestMapping
    public ResponseEntity<ResponseBase> handleError(HttpServletRequest request) {
        ResponseBase response = new ResponseBase();
        response.setMessage(ResultCode.WRONg_URL.getMsg());
        response.setResultCode(ResultCode.WRONg_URL);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }*/

}
