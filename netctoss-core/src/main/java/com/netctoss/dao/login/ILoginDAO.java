package com.netctoss.dao.login;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Admin;

public interface ILoginDAO {

    /**
     * 根据账号查询管理员
     *
     * @param adminCode 账号
     * @return
     * @throws DAOException
     */
    Admin findByCode(
            String adminCode)
            throws DAOException;

}
