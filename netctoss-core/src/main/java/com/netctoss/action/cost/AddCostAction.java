package com.netctoss.action.cost;

import java.sql.Date;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class AddCostAction {

    // 表单提交的请求，一般都包含参数
    // 首要任务，截获表单中的所有的参数
    private Cost cost;

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


    public String addCost() throws DAOException {
//        cost对象保存到数据库中
//        添加一个创建时间
        cost.setCreateTime(new Date(System.currentTimeMillis()));
//        刚创建的Cost状态为“1”：暂停状态
        cost.setStatus("1");
        costDAO.saveCost(cost);
        return "success";
    }
}
