package com.netctoss.dao.account.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.account.IAccountDAO;
import com.netctoss.entity.Account;

public class AccountDaoImpl implements IAccountDAO {
    private HibernateTemplate hibernateTemplate;
    public void setSessionFactory(SessionFactory sessionFactory){
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    @Override
    public List<Account> findByCondition(String idcardNo, String realName,
                                         String loginName, String status,final int page,final int pageSize)
            throws DAOException {
//多条件查询：拼凑sql/hql语句
//	在where 追加and 条件(1=1)
//		where  永远都为true条件
//		and status = ?   -1 ,查询所有
//		and idcardNo = ？
//		and loginName like ?
//		and realName like ?
        final StringBuffer  hql=
                new StringBuffer("select new Account(id, loginName,  status, createDate, realName,  idcardNo, lastLoginTime) from Account where 1=1 ");
        final List params = new ArrayList();
        if(status!=null && !"-1".equals(status) && status.trim().length()>0){
            hql.append("and status = ? ");
            params.add(status);
        }
        if(idcardNo!=null && idcardNo.trim().length()>0){
            hql.append(" and idcardNo = ? ");
            params.add(idcardNo);
        }
        if(loginName!=null && loginName.trim().length()>0){
            hql.append("and loginName like ? ");
            params.add("%"+loginName+"%");
        }
        if(realName!=null && realName.trim().length()>0){
            hql.append("and realName like ? ");
            params.add("%"+realName+"%");
        }
//		排序
        hql.append(" order by id");
        System.out.println(hql.toString());
        List<Account> accounts =
                (List<Account>) hibernateTemplate.execute(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException,
                            SQLException {
                        Query query = session.createQuery(hql.toString());
                        query.setFirstResult((page-1)*pageSize);
                        query.setMaxResults(pageSize);
                        for(int i=0;i<params.size();i++){
                            query.setParameter(i, params.get(i));
                        }
                        return query.list();
                    }
                });
        return accounts;
    }

    @Override
    public Account findById(int id) throws DAOException {
        // TODO Auto-generated method stub
        return (Account) hibernateTemplate.get(Account.class, id);
    }

    @Override
    public Account findByIdcardNo(String idcardNo) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account findByService(int serviceId) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }




    @Override
    public int findTotalPage(String idcardNo, String realName,
                             String loginName, String status, int pageSize) throws DAOException {
//	多条件：跟findByCondition条件一致
        StringBuffer hql =
                new StringBuffer("select count(*) from Account where 1=1 ");
        List params = new ArrayList();
        if(status!=null && !"-1".equals(status) && status.trim().length()>0){
            hql.append(" and status = ? ");
            params.add(status);
        }
        if(realName!=null && realName.trim().length()>0){
            hql.append(" and realName like ? ");
            params.add("%"+realName+"%");
        }
        if(loginName!=null && loginName.trim().length()>0){
            hql.append(" and loginName like ? ");
            params.add("%"+loginName+"%");
        }
        if(idcardNo!=null && idcardNo.trim().length()>0){
            hql.append(" and idcardNo = ? ");
            params.add(idcardNo);
        }
        long totalRows =
                (Long)hibernateTemplate.find(hql.toString(),params.toArray()).get(0);
        if(totalRows%pageSize == 0){
            return (int)totalRows/pageSize;
        }else{
            return (int)totalRows/pageSize+1;
        }
    }
    @Override
    public void updateAccount(Account account) {
        hibernateTemplate.update(account);
    }

}
