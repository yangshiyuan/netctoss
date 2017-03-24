package com.netctoss.vo;

import com.netctoss.entity.Admin;

public class AdminVO extends Admin {

    /**
     * 管理员对应的一组角色的名称字符串
     */
    private String rolesName;

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

}
