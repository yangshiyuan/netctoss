package com.netctoss.action.role;

import java.util.HashSet;
import java.util.Set;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Role;
import com.netctoss.entity.RolePrivilege;
import com.netctoss.entity.RolePrivilegeId;

public class RoleAddAction {
    //    输入参数：
    private Role role;
    private String[] ids;

    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    public String[] getIds() {
        return ids;
    }


    public void setIds(String[] ids) {
        this.ids = ids;
    }


    private IRoleDAO roleDAO;

    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public String saveRole() throws DAOException {
//        找到当前保存的角色对应的权限
        Set<RolePrivilege> privileges = new HashSet<RolePrivilege>();
//    ids--->Privilege的id--->RolePrivilegeId--->RolePrivilege
        for (String id : ids) {
            RolePrivilegeId rolePrivilegeId = new RolePrivilegeId();
            rolePrivilegeId.setPrivilegeId(Integer.parseInt(id));

            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setId(rolePrivilegeId);

            privileges.add(rolePrivilege);
        }
//        role.setName(name) ---从页面中role.name
        role.setPrivileges(privileges);
        roleDAO.saveRole(role);
        return "success";
    }
}
