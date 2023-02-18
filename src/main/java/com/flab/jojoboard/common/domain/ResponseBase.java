package com.flab.jojoboard.common.domain;

import com.flab.jojoboard.common.result.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBase <T>{
    private T data;
    private String message;
    private ResultCode resultCode;
}
