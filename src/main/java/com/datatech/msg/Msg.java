package com.datatech.msg;

public class Msg {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Msg() {
    }

    public Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
