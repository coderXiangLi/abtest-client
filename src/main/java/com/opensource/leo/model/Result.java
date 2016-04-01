package com.opensource.leo.model;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3928402875296079225L;
    protected boolean success = true;
    protected int status;
    protected String message;
    private T module;

    public Result() {
        super();
        setStatus(StatusCode.SUCCESS);
    }

    public void setStatus(Status status) {
        this.setSuccess(status.isSuccess());
        this.setStatus(status.getStatus());
        this.setMessage(status.getMessage());
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}