package com.fxs.platform.domain;

/**
 * 当事人领域类
 */
public class Party extends User {
    /**
     * 当事人微信，等后期集成微信平台，类型会参照微信平台的API规范
     */
    private String wechat;

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}
