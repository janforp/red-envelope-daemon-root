package com.hongbao.api.exception;

/**
 * Created by wuqiang on 15-8-7.
 * <p/>
 * 非法请求异常
 *
 * @author wuqiang
 */
public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException() {
    }

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }

    public IllegalRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
