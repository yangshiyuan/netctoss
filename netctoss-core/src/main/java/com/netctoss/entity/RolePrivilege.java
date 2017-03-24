package com.netctoss.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
    /**
     * 创建Role_Privilege表
     * roleId
     * privilegeId
     */
    private static final long serialVersionUID = 1L;
    private RolePrivilegeId id;//主属性，唯一

    public RolePrivilegeId getId() {
        return id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }
}
