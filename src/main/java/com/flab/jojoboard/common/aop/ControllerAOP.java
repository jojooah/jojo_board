package com.flab.jojoboard.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jojoboard.common.annotation.ReturnAOP;
import com.flab.jojoboard.common.annotation.ReturnDataAOP;
import com.flab.jojoboard.common.domain.ResponseBase;
import com.flab.jojoboard.common.result.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class ControllerAOP {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Around("@annotation(returnAOP)")
    public ResponseBase CommonController(ProceedingJoinPoint pjp, ReturnAOP returnAOP) throws Throwable {

        ResponseBase responseBase = new ResponseBase();
        pjp.proceed();
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }


    @Around("@annotation(returnDataAOP)")
    public Object handleResponse(ProceedingJoinPoint joinPoint, ReturnDataAOP returnDataAOP) throws Throwable {
        ResponseBase<Object> responseBase = new ResponseBase<>();
        Object result = joinPoint.proceed();
        responseBase.setData(result);
        responseBase.setResultCode(ResultCode.SUCCESS);

        return responseBase;
    }
}
