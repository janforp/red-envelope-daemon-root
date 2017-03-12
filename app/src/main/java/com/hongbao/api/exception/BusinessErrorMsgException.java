package com.hongbao.api.exception;

/**
 * Created by wuqiang on 15-8-7.
 * <p/>
 * 业务错误消息异常，当抛出此异常时，会把此异常的message返回给客户端
 *
 * @author wuqiang
 */
public class BusinessErrorMsgException extends RuntimeException {
    private String message;

    public BusinessErrorMsgException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
