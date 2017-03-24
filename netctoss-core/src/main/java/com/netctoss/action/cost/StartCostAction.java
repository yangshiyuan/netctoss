package com.netctoss.action.cost;

import java.sql.Date;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class StartCostAction {
    //	输入参数：id
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


    public String startCost() throws DAOException {
        Cost cost = costDAO.findById(id);
//		0  开通
        cost.setStatus("0");
//		开通的时间
        cost.setStartTime(new Date(System.currentTimeMillis()));
        costDAO.updateCost(cost);
        return "success";
    }
}
