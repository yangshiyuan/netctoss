package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;

public class ShowAccountAction {
    //	输入：id
    private int id;
    //	输出：account   ，推荐人的身份证号码
    private Account account;
    private String recommenderIdcardNo;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Account getAccount() {
        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }


    public String getRecommenderIdcardNo() {
        return recommenderIdcardNo;
    }


    public void setRecommenderIdcardNo(String recommenderIdcardNo) {
        this.recommenderIdcardNo = recommenderIdcardNo;
    }

    private IAccountDAO accountDAO;

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String findAcount() throws DAOException {
//		1 根据id找到对应的account对象
        account = accountDAO.findById(id);
//		2 判断account对象是否有推荐人
        if (account.getRecommenderId() != null) {
//		3 根据account对象的推荐人的id，找到
//			推荐人
//			1) 找到推荐人的id
            int recommenderId =
                    account.getRecommenderId();
//			2）根据推荐人的id，找到推荐人，找到
//			推荐人的身份证号码
            recommenderIdcardNo =
                    accountDAO
                            .findById(recommenderId) //推荐人对象
                            .getIdcardNo();            //推荐人的身份证号

        }

        return "success";
    }
}
