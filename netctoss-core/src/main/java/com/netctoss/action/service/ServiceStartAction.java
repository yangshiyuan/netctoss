package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.entity.Account;
import com.netctoss.entity.Service;

public class ServiceStartAction {
    //    输入参数id：
    private int id;

    private boolean ok;

    public boolean isOk() {
        return ok;
    }


    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    private IServiceDAO serviceDAO;


    public void setServiceDAO(IServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    public String startService() {
//    账务账号是暂停或是删除，业务账号不能开通
//        service ------》account -----》status为0开通
//        才可以开通该业务账号
        try {
            Service service = serviceDAO.findById(id);
            Account account = service.getAccount();
            String accountStauts = account.getStatus();
            if ("0".equals(accountStauts)) {
                service.setStatus("0");
                service.setPauseDate(null);
                serviceDAO.updateService(service);
                System.out.println(service.getStatus());
                ok = true;
            } else {
                ok = false;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
