package com.netctoss.dao.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Role;
import com.netctoss.vo.RoleVO;

public interface IRoleDAO {

    /**
     * 分页查询角色数据
     *
     * @param page
     * @param pageSize
     * @return
     * @throws DAOException
     */
    List<Role> findByPage(int page,
                          int pageSize)
            throws DAOException;

    //	查询总的页数
    int findTotalPage(int pageSize);

    /**
     * 新增角色
     *
     * @param role
     * @throws DAOException
     */
    void saveRole(Role role) throws DAOException;

    /**
     * 根据ID查询角色
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Role findById(int id) throws DAOException;

    /**
     * 修改角色
     *
     * @param role
     * @throws DAOException
     */
    void updateRole(Role role) throws DAOException;

    //	删除角色
    void deleteRole(int id);

    //删除角色的权限
    void deleteByRoleId(int roleId);
}
