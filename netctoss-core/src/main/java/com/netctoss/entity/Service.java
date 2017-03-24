package com.netctoss.entity;


import java.util.Date;


/**
 * Service entity. @author MyEclipse Persistence Tools
 */

public class Service implements java.io.Serializable {
    // Fields
    private Integer id;
    private String unixHost;
    //用于关联相关的Cost记录
    private Cost cost;
    //    private Integer costId;
    //用于关联相关的Account记录
    private Account account;
    //    private Integer accountId;
    private String osUsername;
    private String loginPasswd;
    private String status;
    private Date createDate;
    private Date pauseDate;
    private Date closeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//    public Integer getCostId() {
//        return costId;
//    }
//    public void setCostId(Integer costId) {
//        this.costId = costId;
//    }


    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    //    public Integer getAccountId() {
//        return accountId;
//    }
//    public void setAccountId(Integer accountId) {
//        this.accountId = accountId;
//    }
    public String getOsUsername() {
        return osUsername;
    }

    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername;
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(Date pauseDate) {
        this.pauseDate = pauseDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

}