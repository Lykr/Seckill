package com.learning.seckill.exception;

import com.learning.seckill.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class SecKillExceptionHandler {
    @ExceptionHandler(SecKillException.class)
    public Result<String> handler(SecKillException e) {
        return Result.error(e.getCodeMsg());
    }
}
