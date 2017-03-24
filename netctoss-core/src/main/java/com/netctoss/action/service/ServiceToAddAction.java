package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class ServiceToAddAction {
    private List<Cost> costs;


    public List<Cost> getCosts() {
        return costs;
    }


    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    private ICostDAO costDAO;

    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }


    public String findToAdd() throws DAOException {
        costs = costDAO.findAll();
        return "success";
    }
}
