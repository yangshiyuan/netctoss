package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;

public class UpdateAccountAction {
    //	输入的参数： account，新密码，旧密码，modiPwd
    private Account account;
    private String modiPwd;//modiPwd是“1”的话，可以修改
    private String oldPassword;
    private String newPassword;

    //	注入DAO
    private IAccountDAO accountDAO;

    public String getModiPwd() {
        return modiPwd;
    }


    public void setModiPwd(String modiPwd) {
        this.modiPwd = modiPwd;
    }


    public String getOldPassword() {
        return oldPassword;
    }


    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public String getNewPassword() {
        return newPassword;
    }


    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public IAccountDAO getAccountDAO() {
        return accountDAO;
    }


    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    public void setAccount(Account account) {
        this.account = account;
    }


    public String updateAccount() throws DAOException {
        try {
//			1  根据id，查询要修改的Account对象
            Account oldAccount =
                    accountDAO.findById(account.getId());

            account.setLoginPasswd(oldAccount.getLoginPasswd());
//			2  判断是否修改密码
            if ("1".equals(modiPwd)) {
//				3  判断旧的密码是否正确
                if (oldAccount.getLoginPasswd().equals(oldPassword)) {
//					4  修改密码（不要使用update）
                    account.setLoginPasswd(newPassword);
                }
            }
//			5  添加表单没有的字段
            account.setRecommenderId(oldAccount.getRecommenderId());
            account.setCloseDate(oldAccount.getCloseDate());
            account.setCreateDate(oldAccount.getCreateDate());
            account.setPauseDate(oldAccount.getPauseDate());
            account.setStatus(oldAccount.getStatus());
            account.setServices(oldAccount.getServices());

//			6  updateAccount
            accountDAO.updateAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }


    public Account getAccount() {
        return account;
    }
}
