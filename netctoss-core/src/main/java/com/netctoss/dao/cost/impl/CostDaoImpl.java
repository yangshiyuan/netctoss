package com.netctoss.dao.cost.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.cost.ICostDAO;
import com.netctoss.entity.Cost;


public class CostDaoImpl implements ICostDAO {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveCost(Cost cost) throws DAOException {
        hibernateTemplate.save(cost);
    }

    @Override
    public void delete(int id) throws DAOException {
        Cost cost = new Cost();
        cost.setId(id);
        hibernateTemplate.delete(cost);
    }

    @Override
    public Cost findById(final int id) throws DAOException {

//        return (Cost) hibernateTemplate.get(Cost.class, id);
        return (Cost) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Cost cost = (Cost) session.get(Cost.class, id);
                session.clear();
                return cost;
            }
        });

    }


    @Override
    public List<Cost> findByPage(final int page, final int pageSize) throws DAOException {
//    分页：使用回调，调用session，使用hibernate的
//    专有分页
        return (List<Cost>) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String hql = "from Cost";
                return session.createQuery(hql)
                        .setFirstResult((page - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
            }
        });
    }

    @Override
    public int findTotalPage(int pageSize) throws DAOException {
// 总页数：求出数据库中总的信息条数/每页容量
        String hql = "select count(*) from Cost";
        long totalRows = (Long) hibernateTemplate.find(hql).get(0);
        if (totalRows % pageSize == 0) {
            return (int) (totalRows / pageSize);
        } else {
            return (int) (totalRows / pageSize + 1);
        }
    }

    @Override
    public void updateCost(Cost cost) throws DAOException {
        hibernateTemplate.update(cost);
    }

    @Override
    public List<Cost> findAll() {
        String hql = "from Cost";
        return hibernateTemplate.find(hql);
    }


}
