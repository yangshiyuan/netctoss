package com.netctoss.action.service;

import java.util.Date;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.entity.Service;

public class ServiceDelAction {
    //	输入参数：id
    private int id;


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


    public String deleteService() throws DAOException {
        Service service = serviceDAO.findById(id);
        service.setStatus("2");
        service.setCloseDate(new Date(System.currentTimeMillis()));
        serviceDAO.updateService(service);
        return "success";
    }
}
