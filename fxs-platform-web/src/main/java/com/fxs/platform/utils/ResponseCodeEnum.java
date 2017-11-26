package com.fxs.platform.utils;

public enum ResponseCodeEnum {

    SUCCESS(0),
    ERROR(-1);

    private Integer code;

    ResponseCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
