package com.learning.seckill.exception;

import com.learning.seckill.result.CodeMsg;

public class SecKillException extends RuntimeException {
    private CodeMsg cm;

    public SecKillException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCodeMsg() {
        return cm;
    }
}
