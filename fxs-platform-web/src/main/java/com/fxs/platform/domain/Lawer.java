package com.fxs.platform.domain;

/**
 * 律师领域类
 */
public class Lawer extends User {
    private String gender;

    private String location = "";

    private String businessDomain = "";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusinessDomain() {
        return businessDomain;
    }

    public void setBusinessDomain(String businessDomain) {
        this.businessDomain = businessDomain;
    }
}
