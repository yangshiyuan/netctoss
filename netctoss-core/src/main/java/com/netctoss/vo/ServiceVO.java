package com.netctoss.vo;

import com.netctoss.entity.Service;

/**
 * VO，用于封装关联查询的结果，
 * 避免直接使用实体类来封装，导致实体类
 * 代码混乱
 * <p>
 * View Object:主要用来显示信息
 */
public class ServiceVO {
    private int id;
    private int accountId;
    private String realName;
    private String idcardNo;
    private String osUserName;
    private String status;
    private String unixHost;
    private String costName;
    private String descr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getOsUserName() {
        return osUserName;
    }

    public void setOsUserName(String osUserName) {
        this.osUserName = osUserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnixHost() {
        return unixHost;
    }

    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


}
