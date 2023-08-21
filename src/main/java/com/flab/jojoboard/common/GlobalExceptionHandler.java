package com.flab.jojoboard.common;

import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResultCodeException.class})
    protected ResponseEntity<Object> handleResultCodeException(ResultCodeException re) {
        ResponseBase responseBase = new ResponseBase();

        responseBase.setResultCode(re.getResultCode());
        responseBase.setMessage(re.getResultCode().getMsg());
        log.error(re.getResultCode().getMsg());

        return ResponseEntity.status(re.getResultCode().getHttpStatus()).body(responseBase);
    }



    @ExceptionHandler({Exception.class}) //Ambiguous @ExceptionHandler method mapped for [class org.springframework.web.bind.MissingPathVariableException] 에러발생.
    protected ResponseEntity<Object> handleException(Exception ex) {
        ResponseBase responseBase = new ResponseBase();

        responseBase.setMessage("의도하지 못한 에러");
        responseBase.setResultCode(ResultCode.ERROR_ETC);
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
    }

}
