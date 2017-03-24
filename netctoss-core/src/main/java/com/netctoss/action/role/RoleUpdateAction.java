package com.netctoss.action.role;

import java.util.HashSet;
import java.util.Set;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Role;
import com.netctoss.entity.RolePrivilege;
import com.netctoss.entity.RolePrivilegeId;

public class RoleUpdateAction {
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


    public String updateRole() throws DAOException {
//		清空所有的角色
        roleDAO.deleteByRoleId(role.getId());
        Role newRole = roleDAO.findById(role.getId());
        newRole.setName(role.getName());
        Set<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
        for (String id : ids) {
            RolePrivilegeId rolePrivilegeId = new RolePrivilegeId();
            rolePrivilegeId.setPrivilegeId(Integer.parseInt(id));

            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setId(rolePrivilegeId);
            rolePrivileges.add(rolePrivilege);
        }
        newRole.setPrivileges(rolePrivileges);
        roleDAO.updateRole(newRole);
        return "success";
    }
}
