package com.netctoss.action.account;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;

public class FindAccountAction {
    //	输入的参数：
    private String idcardNo;
    private String realName;
    private String loginName;
    private String status;

    //分页：pageSize，page
    private int page = 1;
    private int pageSize = 3;
    //跳转到findAccount.jsp要显示信息：
//	当前分页的信息：
    private List<Account> accounts;
    //	总页数：
    private int totalPage;


    public String getIdcardNo() {
        return idcardNo;
    }


    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }


    public String getRealName() {
        return realName;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getLoginName() {
        return loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
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


    public List<Account> getAccounts() {
        return accounts;
    }


    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    //	注入accountDAO
    private IAccountDAO accountDAO;

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String findAcounts() throws DAOException {
        accounts =
                accountDAO.findByCondition(idcardNo, realName, loginName, status, page, pageSize);
        totalPage =
                accountDAO.findTotalPage(idcardNo, realName, loginName, status, pageSize);
        return "success";
    }
}
