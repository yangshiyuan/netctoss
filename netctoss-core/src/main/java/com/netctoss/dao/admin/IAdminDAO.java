package com.netctoss.dao.admin;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.vo.AdminVO;

public interface IAdminDAO {

    /**
     * 根据条件分页查询管理员
     *
     * @param roleId      角色ID
     * @param privilegeId 权限ID
     * @param page        页码
     * @param pageSize    页容量
     * @return
     * @throws DAOException
     */
    List<AdminVO> findByCondition(
            Integer roleId, Integer privilegeId,
            int page, int pageSize)
            throws DAOException;

    /**
     * 查询总页数
     *
     * @param roleId
     * @param privilegeId
     * @param pageSize
     * @return
     * @throws DAOException
     */
    int findTotalPage(
            Integer roleId,
            Integer privilegeId,
            int pageSize)
            throws DAOException;

    /**
     * 重置密码，重置为123456
     *
     * @param ids 一组ID
     * @throws DAOException
     */
    void resetPassword(List<Integer> ids)
            throws DAOException;

}
