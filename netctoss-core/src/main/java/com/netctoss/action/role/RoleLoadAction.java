package com.netctoss.action.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Privilege;
import com.netctoss.entity.Role;
import com.netctoss.util.PrivilegeReader;

public class RoleLoadAction {
    private int id;

    private Role role;

    private List<Privilege> privileges;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    public List<Privilege> getPrivileges() {
        return privileges;
    }


    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    private IRoleDAO roleDAO;


    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    public String findRole() throws DAOException {
        role = roleDAO.findById(id);
        privileges = PrivilegeReader.getPrivileges();
//	显示当前要修改的角色的权限
//  	role -----> set<RolePrivileger>---->id(联合主键).privilegeId
//		   ----->PrivilegeReader.getPrivilegeNameById

        return "success";
    }
}
