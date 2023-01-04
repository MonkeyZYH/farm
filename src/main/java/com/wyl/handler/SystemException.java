package com.wyl.handler;

public class SystemException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String code;

    public String getCode() {
        return code;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

}
