package com.netctoss.action.account;

import java.util.Date;

import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;
import com.netctoss.entity.Service;

public class DeleteAccountAction {
    // 输入参数： id
    private int id;
    // 输出：ok
    private boolean ok;//是否删除成功


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


    public String deleteAccount() {
        try {
//          1） 根据id找到对应Account对象
            Account account = accountDAO.findById(id);
//          2）设置Account对象：status=2
//               设置关闭的时间
            account.setStatus("2");
            account.setCloseDate(new Date(System.currentTimeMillis()));
//          3） 找到账务账号Account下属的所有的
//          业务账号Serivce
            for (Service service : account.getServices()) {
//          4） 设置Service对象：status=2
//                 设置关闭的时间
                service.setStatus("2");
                service.setCloseDate(new Date(System.currentTimeMillis()));
            }
//          5） update：通过级联操作，关闭对应
//                 service
            accountDAO.updateAccount(account);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return "success";
    }
}
