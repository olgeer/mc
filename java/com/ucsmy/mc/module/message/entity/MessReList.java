package com.ucsmy.mc.module.message.entity;

import java.util.Date;

/**
 * Created by jack on 2016/11/29.
 */
public class MessReList {

    private String id;//
    private String rec_id;//
    private String readstate;//
    private Date readtime;//
    private String receipt;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getReadstate() {
        return readstate;
    }

    public void setReadstate(String readstate) {
        this.readstate = readstate;
    }

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
