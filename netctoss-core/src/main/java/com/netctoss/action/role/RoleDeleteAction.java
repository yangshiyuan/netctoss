package com.netctoss.action.role;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Role;

public class RoleDeleteAction {
    //    输入的参数:id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private IRoleDAO roleDAO;

    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public String deleteRole() throws DAOException {
//        根据id找到要删除role对象
//        Role role = roleDAO.findById(id);

//        role.getPrivileges().clear();
//        执行删除操作
//        先根据角色的id，删除权限
        roleDAO.deleteByRoleId(id);
//        再删除对应的角色
        roleDAO.deleteRole(id);
        return "success";
    }
}
