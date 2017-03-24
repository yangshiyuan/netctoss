package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.entity.Cost;
import com.netctoss.entity.Service;

public class ServiceLoadAction {
    //    输入的参数： id
    private int id;
    //    输出到service_modi.jsp中的service的信息
    private Service service;
    //    所有的资费的信息
    private List<Cost> costs;

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

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    private ICostDAO costDAO;
    private IServiceDAO serviceDAO;


    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }

    public void setServiceDAO(IServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    public String findService() throws DAOException {
        costs = costDAO.findAll();
        service = serviceDAO.findById(id);
        return "success";
    }


}
