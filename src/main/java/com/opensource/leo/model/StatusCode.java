package com.opensource.leo.model;


public enum StatusCode implements Status {
    SUCCESS(0, "success"),
    PARAM_ERROR(1,"param error"),
    NO_EXPR(2,"no expr"),
    FAILED(-1, "System error"),;
    private int status;
    private String message;

    StatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return status >= 0;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
