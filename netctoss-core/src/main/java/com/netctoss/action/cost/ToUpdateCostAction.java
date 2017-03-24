package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class ToUpdateCostAction {
    //	输入的参数：id
    private int id;
    //	输出的参数：cost
    private Cost cost;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Cost getCost() {
        return cost;
    }


    public void setCost(Cost cost) {
        this.cost = cost;
    }

    private ICostDAO costDAO;


    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }


    public String findCostID() throws DAOException {
        cost = costDAO.findById(id);
        return "success";
    }
}
