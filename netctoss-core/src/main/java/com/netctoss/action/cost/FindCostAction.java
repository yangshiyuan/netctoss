package com.netctoss.action.cost;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;

public class FindCostAction {

    //	分页：当前页pge，页容量pageSize
    private int page = 1; //默认是第1页
    private int pageSize = 3;

    //	跳转到findCost.jsp以后，在页面显示信息
//	总页数：totalPage
//	显示内容：costs
    private int totalPage;
    private List<Cost> costs;


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public List<Cost> getCosts() {
        return costs;
    }


    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }


    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    //	spring的事务配置到Action层，多次调用DAO（每个对数据库一次操作）
//	struts2中的方法，目前最好都符合原则，
//	查询操作：都以find开头
//	修改，保存，删除：任意
    private ICostDAO costDAO;

    public void setCostDAO(ICostDAO costDAO) {
        this.costDAO = costDAO;
    }

    public String findCosts() throws DAOException {
        costs = costDAO.findByPage(page, pageSize);
        totalPage = costDAO.findTotalPage(pageSize);
        return "success";
    }
}
