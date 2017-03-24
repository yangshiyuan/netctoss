package com.netctoss.action.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Role;

public class RoleListAction {
    private int page = 1;
    private int pageSize = 3;

    //    在role_list.jsp输出的参数：totalPage  ， roles
    private int totalPage;
    private List<Role> roles;


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private IRoleDAO roleDAO;


    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    public String findRoles() throws DAOException {
        roles = roleDAO.findByPage(page, pageSize);
        totalPage = roleDAO.findTotalPage(pageSize);
        return "success";
    }
}
