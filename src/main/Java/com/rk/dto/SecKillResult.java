package com.rk.dto;

/**
 * 前台接收json结果，封装类
 * @param <T>
 */
public class SecKillResult<T> {

    private boolean success;//返回信息 true / false

    private T data; //正确返回数据

    private String error;//错误返回，错误信息

    public SecKillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SecKillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
