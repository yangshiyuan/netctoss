package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.vo.ServiceVO;

public class ServiceListAction {
    //    输入的参数：
    private String osUsername;
    private String unixHost;
    private String idcardNo;
    private String status = "-1";

    private int page = 1;
    private int pageSize = 3;


    //    输出的信息；
//    分页：serviceVos，totalPage
    private List<ServiceVO> serviceVos;
    private int totalPage;


    public String getOsUsername() {
        return osUsername;
    }


    public void setOsUsername(String osUsername) {
        this.osUsername = osUsername;
    }


    public String getUnixHost() {
        return unixHost;
    }


    public void setUnixHost(String unixHost) {
        this.unixHost = unixHost;
    }


    public String getIdcardNo() {
        return idcardNo;
    }


    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
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


    public List<ServiceVO> getServiceVos() {
        return serviceVos;
    }


    public void setServiceVos(List<ServiceVO> serviceVos) {
        this.serviceVos = serviceVos;
    }


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    //    注入serviceDAO
    private IServiceDAO serviceDAO;

    public void setServiceDAO(IServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    public String findServices() throws DAOException {
        serviceVos = serviceDAO.findByCondition(osUsername, unixHost, idcardNo, status, page, pageSize);
        totalPage = serviceDAO.findTotalPage(osUsername, unixHost, idcardNo, status, page, pageSize);
        return "success";
    }
}
