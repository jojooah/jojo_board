package com.flab.jojoboard.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultCodeException extends Exception{
    private ResultCode resultCode;

    private Object response;

    public  ResultCodeException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
}
