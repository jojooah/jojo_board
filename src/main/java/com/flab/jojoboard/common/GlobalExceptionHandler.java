package com.flab.jojoboard.common;

import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ResultCodeException.class})
    protected ResponseBase handleResultCodeException(ResultCodeException re) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setResultCode(re.getResultCode());
        responseBase.setMessage(re.getResultCode().getMsg());

        return responseBase;
    }

    @ExceptionHandler({Exception.class})
    protected ResponseBase handleException(Exception e) {
        ResponseBase responseBase = new ResponseBase();

        responseBase.setMessage("의도하지 못한 에러");
        responseBase.setResultCode(ResultCode.ERROR_ETC);
        log.error(e.getMessage());

        return responseBase;
    }

}
