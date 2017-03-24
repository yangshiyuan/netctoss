package com.netctoss.action.account;

import java.util.Date;
import java.util.Set;

import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;
import com.netctoss.entity.Service;

public class PauseAccountAction {
    // 输入id
    private int id;

    // 输出ok
    private boolean ok; //是否暂停成功

    private IAccountDAO accountDAO;


    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String pauseAccount() {
//  关闭Account账务账号，还要关闭Service业务
//  账号
//  JDBC：分别编写两条sql语句
//  hibernate：使用级联
        try {
            Account account = accountDAO.findById(id);
//          修改account：
//          statuts  “1”
            account.setStatus("1");
//            暂停时间
            account.setPauseDate(new Date(System.currentTimeMillis()));
//            利用关联关系找到该账务账号对应
//            所有的业务账号
            for (Service service : account.getServices()) {
//            使用级联
                service.setStatus("1");
            }
            accountDAO.updateAccount(account);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return "success";
    }

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


}
