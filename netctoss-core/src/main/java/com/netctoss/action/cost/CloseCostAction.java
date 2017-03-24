package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class CloseCostAction {
    //    输入参数
    private int id;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    private ICostDAO costDAO;

    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }

    public String closeCost() throws DAOException {
        Cost cost = costDAO.findById(id);
//        status : 1,                开启的时间设为null
        cost.setStatus("1");
        cost.setStartTime(null);
        costDAO.updateCost(cost);
        return "success";
    }
}
