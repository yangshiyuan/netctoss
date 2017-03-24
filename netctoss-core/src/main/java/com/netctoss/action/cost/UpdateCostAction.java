package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class UpdateCostAction {
    //	输入参数：Cost
    private Cost cost;
//	依赖CostDAO

    private ICostDAO costDAO;


    public Cost getCost() {
        return cost;
    }


    public void setCost(Cost cost) {
        this.cost = cost;
    }


    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }


    public String updateCost() throws DAOException {
//		添加：状态，开通的时间，创建时间
//		获得要修改的Cost对象的id
        int id = cost.getId();
        Cost cost1 = costDAO.findById(id);
//		把未添加的状态，开通时间，创建时间添加
//		cost对象中
        cost.setStatus(cost1.getStatus());
        cost.setStartTime(cost1.getStartTime());
        cost.setCreateTime(cost1.getCreateTime());
        costDAO.updateCost(cost);
        return "success";
    }
}
