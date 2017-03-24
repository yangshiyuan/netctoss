package com.netctoss.dao.account;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Account;

/**
 * 账务账号DAO
 */
public interface IAccountDAO {

    /**
     * 根据条件查询账务账号
     *
     * @param idcardNo  身份证
     * @param realName  姓名
     * @param loginName 登陆名
     * @param status    状态
     * @param page      页码
     * @param pageSize  页容量
     * @return
     * @throws DAOException
     */
    List<Account> findByCondition(
            String idcardNo,
            String realName,
            String loginName,
            String status,
            int page, int pageSize)
            throws DAOException;

    /**
     * 查询总页数
     *
     * @param idcardNo  身份证
     * @param realName  姓名
     * @param loginName 登陆名
     * @param status    状态
     * @param pageSize  页容量
     * @return
     * @throws DAOException
     */
    int findTotalPage(
            String idcardNo,
            String realName,
            String loginName,
            String status,
            int pageSize)
            throws DAOException;


    /**
     * 根据身份证查询账务账号
     *
     * @param idcardNo 身份证
     * @return
     * @throws DAOException
     */
    Account findByIdcardNo(String idcardNo)
            throws DAOException;

    /**
     * 根据ID查询账务账号
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Account findById(int id)
            throws DAOException;

    /**
     * 根据业务账号ID查询账务账号
     *
     * @param serviceId
     * @return
     * @throws DAOException
     */
    Account findByService(int serviceId)
            throws DAOException;

    //修改Account
    void updateAccount(Account account);

}
