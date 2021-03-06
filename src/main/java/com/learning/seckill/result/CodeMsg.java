package com.learning.seckill.result;

public class CodeMsg {
    private int code;
    private String msg;

    // 响应成功
    public static CodeMsg SUCCESS = new CodeMsg(200100, "success");

    // 通用的错误码
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg LIMITER_TIP = new CodeMsg(500102, "服务器正忙，请稍后尝试访问");

    // 登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500201, "Session 不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500202, "登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500203, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500204, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500205, "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500206, "密码错误");
    public static CodeMsg LOGIN_ALREADY = new CodeMsg(500207, "已经登陆");

    // 商品模块 5003XX

    // 订单模块 5004XX

    // 秒杀模块 5005XX
    public static CodeMsg SECKILL_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_SECKILL = new CodeMsg(500501, "不能重复秒杀");

    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
