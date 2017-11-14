package com.fxs.platform.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 预约状态
     */
    private int status;

    /**
     * 当事人和律师预约电话联系的日期+时间
     */
    private Date researvationDatetime;

    /**
     * 当事人的联系方式
     */
    private String contactPhone;

    /**
     * 电话预约的用户
     */
    //private User reservationUser;

    /**
     * 预约律师
     */
    private Lawyer reservedLaywer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getResearvationDatetime() {
        return researvationDatetime;
    }

    public void setResearvationDatetime(Date researvationDatetime) {
        this.researvationDatetime = researvationDatetime;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

   /* public User getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(User reservationUser) {
        this.reservationUser = reservationUser;
    }*/
}

