package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.entity.Service;

public class ServiceDetailAction {
    private int id;
    //    在service_detail.jsp中显示service对象的信息
    private Service service;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Service getService() {
        return service;
    }


    public void setService(Service service) {
        this.service = service;
    }

    private IServiceDAO serviceDAO;


    public void setServiceDAO(IServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    public String findService() throws DAOException {
        service = serviceDAO.findById(id);
        return "success";
    }
}
