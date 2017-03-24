package com.netctoss.dao.cost;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Cost;

public interface ICostDAO {


    /**
     * @param page     当前页
     * @param pageSize 页容量
     * @return
     * @throws DAOException
     */
    List<Cost> findByPage(int page, int pageSize)
            throws DAOException;

    /**
     * 查询总页数
     *
     * @param pageSize 页容量
     * @return
     * @throws DAOException
     */
    int findTotalPage(int pageSize)
            throws DAOException;

    /**
     * 删除一条资费数据
     *
     * @param id
     * @throws DAOException
     */
    void delete(int id) throws DAOException;

    /**
     * 新增资费数据
     *
     * @param cost
     * @throws DAOException
     */
    void saveCost(Cost cost) throws DAOException;


    /**
     * 根据ID查询资费数据
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Cost findById(int id)
            throws DAOException;

    /**
     * 修改资费数据
     *
     * @param cost
     * @throws DAOException
     */
    void updateCost(Cost cost)
            throws DAOException;

    //    查询所有的cost的信息
    List<Cost> findAll() throws DAOException;
}
