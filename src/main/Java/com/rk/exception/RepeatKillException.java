package com.rk.exception;

/**
 * 重复秒杀异常
 */
public class RepeatKillException extends SecKillExecption {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
