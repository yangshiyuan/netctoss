package com.netctoss.action.role;

import java.util.List;

import com.netctoss.entity.Privilege;
import com.netctoss.util.PrivilegeReader;

public class RoleIndexAction {
//    在role_add.jsp中显示所有的权限

    List<Privilege> privileges;


    public List<Privilege> getPrivileges() {
        return privileges;
    }


    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }


    public String findPrivileger() {
        privileges = PrivilegeReader.getPrivileges();
        return "success";
    }
}
