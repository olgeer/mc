package com.ucsmy.mc.module.message.entity;

import java.util.Date;

/**
 * Created by jack on 2016/11/29.
 */
public class MessReceive {
    private String id;//
    private String title;//
    private String detail;//
    private String send_user_id;//
    private Date send_time;//
    private String relation_domain;//
    private String relation_domain_id;//
    private String sts;
    private String rev_user_id;
    private Integer mstip;


    public String getRev_user_id() {
        return rev_user_id;
    }

    public void setRev_user_id(String rev_user_id) {
        this.rev_user_id = rev_user_id;
    }

    public Integer getMstip() {
        return mstip;
    }

    public void setMstip(Integer mstip) {
        this.mstip = mstip;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(String send_user_id) {
        this.send_user_id = send_user_id;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public String getRelation_domain() {
        return relation_domain;
    }

    public void setRelation_domain(String relation_domain) {
        this.relation_domain = relation_domain;
    }

    public String getRelation_domain_id() {
        return relation_domain_id;
    }

    public void setRelation_domain_id(String relation_domain_id) {
        this.relation_domain_id = relation_domain_id;
    }
}
