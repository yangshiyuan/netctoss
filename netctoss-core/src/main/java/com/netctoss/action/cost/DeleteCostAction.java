package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;

public class DeleteCostAction {
    //	输入参数id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //	注入CostDao对象
    private ICostDAO costDAO;


    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }

    public String deleteCost() throws DAOException {
        costDAO.delete(id);
        return "success";
    }
}
