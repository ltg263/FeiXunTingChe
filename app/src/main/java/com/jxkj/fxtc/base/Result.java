package com.jxkj.fxtc.base;

public class Result<T> {

    /**
     * data :
     * status : 0
     */

    private T data;
    private int status;
    private String error;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
