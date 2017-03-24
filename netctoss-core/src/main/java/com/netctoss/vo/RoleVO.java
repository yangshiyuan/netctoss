package com.netctoss.vo;

import com.netctoss.entity.Role;

public class RoleVO extends Role {

    //一组模块的名称字符串，用逗号分开
    private String modulesName;

    public String getModulesName() {
        return modulesName;
    }

    public void setModulesName(String modulesName) {
        this.modulesName = modulesName;
    }

}
