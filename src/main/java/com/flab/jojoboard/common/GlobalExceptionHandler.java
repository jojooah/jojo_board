package com.flab.jojoboard.common;

import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import com.flab.jojoboard.common.result.ResultCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResultCodeException.class})
    protected ResponseEntity<Object> handleResultCodeException(ResultCodeException re) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setResultCode(re.getResultCode());
        responseBase.setMessage(re.getResultCode().getMsg());

        return ResponseEntity.status(re.getResultCode().getHttpStatus()).body(responseBase);
    }

/*
    @Override // 핸들러 못찾을때 발생하는 익셉션을 커스텀했으나, ErrorController를 상속받은 클래스에서 잡는다
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setMessage(ResultCode.WRONg_URL.getMsg());
        responseBase.setResultCode(ResultCode.WRONg_URL);
        log.error(ex.getMessage());
        return ResponseEntity.status(ResultCode.WRONg_URL.getHttpStatus()).body(responseBase);
    }
*/

    @ExceptionHandler({MissingPathVariableException.class}) // 해당 익셉션을 테스트로 작성했습니다.
    protected ResponseEntity<Object> handleMissingPathVariableException(MissingPathVariableException ex) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setMessage(ResultCode.NO_PATHVARIABLE.getMsg());
        responseBase.setResultCode(ResultCode.NO_PATHVARIABLE);
        log.error(ex.getMessage());
        return ResponseEntity.status(ResultCode.NO_PATHVARIABLE.getHttpStatus()).body(responseBase);
    }


 /*   @ExceptionHandler({Exception.class}) //Ambiguous @ExceptionHandler method mapped for [class org.springframework.web.bind.MissingPathVariableException] 에러발생.
    protected ResponseEntity<Object> handleException(Exception ex) {
        ResponseBase responseBase = new ResponseBase();

        responseBase.setMessage("의도하지 못한 에러");
        responseBase.setResultCode(ResultCode.ERROR_ETC);
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
    }*/



}
