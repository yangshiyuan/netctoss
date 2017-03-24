package com.netctoss.dao.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Service;
import com.netctoss.vo.ServiceVO;

public interface IServiceDAO {

    /**
     * 查询业务账号
     *
     * @param osUserName os用户名
     * @param unixHost   服务器IP
     * @param idcardNo   身份证
     * @param status     状态
     * @param page       页码
     * @param pageSize   页容量
     * @return
     * @throws DAOException
     */
    List<ServiceVO> findByCondition(
            String osUserName,
            String unixHost,
            String idcardNo,
            String status,
            int page, int pageSize)
            throws DAOException;

    //	查询总的页数
    int findTotalPage(String osUserName,
                      String unixHost,
                      String idcardNo,
                      String status,
                      int page, int pageSize);


    /**
     * 根据ID查询业务账号
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Service findById(int id) throws DAOException;

    /**
     * 修改业务账号，保存到备份表中
     *
     * @param service
     * @throws DAOException
     */
    void updateService(Service service)
            throws DAOException;

}
