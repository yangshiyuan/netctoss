package com.netctoss.dao;

import com.netctoss.dao.account.AccountDAOImpl;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.dao.admin.AdminDAOImpl;
import com.netctoss.dao.admin.IAdminDAO;
import com.netctoss.dao.cost.CostDAOImpl;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.dao.login.ILoginDAO;
import com.netctoss.dao.login.LoginDAOImpl;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.dao.role.RoleDAOImpl;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.dao.service.ServiceDAOImpl;

public class DAOFactory {

    private static ICostDAO costdao =
            new CostDAOImpl();

    private static ILoginDAO logindao =
            new LoginDAOImpl();

    private static IAccountDAO accdao =
            new AccountDAOImpl();

    private static IServiceDAO serdao =
            new ServiceDAOImpl();

    private static IRoleDAO roledao =
            new RoleDAOImpl();

    private static IAdminDAO admindao =
            new AdminDAOImpl();

    /**
     * 返回资费DAO的接口实例
     */
    public static ICostDAO getCostDAO() {
        return costdao;
    }

    /**
     * 返回登陆DAO的接口实例
     */
    public static ILoginDAO getLoginDAO() {
        return logindao;
    }

    /**
     * 返回账务账号DAO的接口实例
     */
    public static IAccountDAO getAccountDAO() {
        return accdao;
    }

    /**
     * 返回业务账号DAO的接口实例
     */
    public static IServiceDAO getServiceDAO() {
        return serdao;
    }

    /**
     * 返回角色DAO的接口实例
     */
    public static IRoleDAO getRoleDAO() {
        return roledao;
    }

    /**
     * 返回管理员DAO的接口实例
     */
    public static IAdminDAO getAdminDAO() {
        return admindao;
    }

}
