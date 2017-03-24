package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;

public class StartAccountAction {
    //	输入的参数：id
    private int id;
    //	输出的值： ok
    private boolean ok; //是否暂停成功


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean isOk() {
        return ok;
    }


    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private IAccountDAO accountDAO;


    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String startAccount() {
        try {
//			1 根据id找到对应Account对象
            Account account = accountDAO.findById(id);
//			2 修改Account对象的值
//			1）status = 0
//			2）暂停时间为null
            account.setStatus("0");
            account.setPauseDate(null);
//			3  update
            accountDAO.updateAccount(account);
//         4 设置ok的值
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return "success";

    }
}
