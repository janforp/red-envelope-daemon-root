package com.hongbao.api.exception;

/**
 * Created by wuqiang on 15-8-7.
 * <p/>
 * 无法加密响应内容<br>
 * 拦截到这个异常则需要RestFilter做出明文响应
 *
 * @author wuqiang
 */
public class UnableEncryptResponseException extends RuntimeException {
    public UnableEncryptResponseException() {
    }

    public UnableEncryptResponseException(String message) {
        super(message);
    }

    public UnableEncryptResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableEncryptResponseException(Throwable cause) {
        super(cause);
    }

    public UnableEncryptResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
