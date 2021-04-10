package com.learning.seckill.exception;

import com.learning.seckill.result.CodeMsg;
import com.learning.seckill.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SecKillException.class)
    public Result<String> handler(SecKillException e) {
        e.printStackTrace();
        return Result.error(e.getCodeMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handler(Exception e) {
        e.printStackTrace();
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
