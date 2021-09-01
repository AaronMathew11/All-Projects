package com.adgvit.papervit;

public class Error {
    String msg;
    String param;
    String location;

    public Error(String msg, String param, String location) {
        this.msg = msg;
        this.param = param;
        this.location = location;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
