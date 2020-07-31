package com.starry.exception;

public class WebException extends RuntimeException {
    private static final long serialVersionUID = 1948689011678137776L;
    private String msg;
    private int code = 500;

    public WebException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public WebException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public WebException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public WebException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
