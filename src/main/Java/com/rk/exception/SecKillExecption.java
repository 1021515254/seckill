package com.rk.exception;

/**
 * 秒杀业务异常
 */
public class SecKillExecption extends RuntimeException {

    public SecKillExecption(String message) {
        super(message);
    }

    public SecKillExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
