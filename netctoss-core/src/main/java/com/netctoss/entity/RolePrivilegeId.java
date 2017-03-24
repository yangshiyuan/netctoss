package com.netctoss.entity;

import java.io.Serializable;

/**
 * 定义联合主键类,必须序列化
 *
 * @author Administrator
 */
public class RolePrivilegeId implements Serializable {
    private int roleId;
    private int privilegeId;

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
