package com.netctoss.dao.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.service.IServiceDAO;
import com.netctoss.entity.Cost;
import com.netctoss.entity.Service;
import com.netctoss.vo.ServiceVO;

public class ServiceDAOImpl implements IServiceDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    @Override
    public List<ServiceVO> findByCondition(String osUserName, String unixHost,
                                           String idcardNo, String status, final int page, final int pageSize)
            throws DAOException {
        // String
        // hql="select s.id,s.account.id,s.account.idcardNo,s.account.realName,s.osUsername,s.status,s.unixHost, s.cost.name,s.cost.descr from Service s";
        final StringBuffer hql = new StringBuffer(
                "select s.id,s.account.id,s.account.idcardNo,s.account.realName,s.osUsername,s.status,s.unixHost, s.cost.name,s.cost.descr from Service s where 1=1 ");
        final List params = new ArrayList();
        if (status != null && !"-1".equals(status)
                && status.trim().length() > 0) {
            hql.append(" and s.status = ? ");
            params.add(status);
        }
        if (osUserName != null && osUserName.trim().length() > 0) {
            hql.append(" and s.osUsername = ? ");
            params.add(osUserName);
        }
        if (unixHost != null && unixHost.trim().length() > 0) {
            hql.append(" and s.unixHost = ? ");
            params.add(unixHost);
        }
        if (idcardNo != null && idcardNo.trim().length() > 0) {
            hql.append(" and s.account.idcardNo = ?");
            params.add(idcardNo);
        }
        hql.append(" order by  s.id");
        List<Object[]> list =
                (List<Object[]>) hibernateTemplate.execute(new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException,
                            SQLException {
                        Query query = session.createQuery(hql.toString());
                        query.setFirstResult((page - 1) * pageSize);
                        query.setMaxResults(pageSize);
                        for (int i = 0; i < params.size(); i++) {
                            query.setParameter(i, params.get(i));
                        }
                        return query.list();
                    }
                });
//	查询List<Object[]>  ------> List<ServiceVO>
//	每一个Object[]都会转化为ServiceVo的对象
//	切记，顺序
//		s.id,s.account.id,s.account.idcardNo,
        //s.account.realName,s.osUsername,s.status,
        //s.unixHost, s.cost.name,s.cost.descr
        List<ServiceVO> serviceVos = new ArrayList<ServiceVO>();
        for (Object[] objs : list) {
            ServiceVO serviceVO = new ServiceVO();
            serviceVO.setId(Integer.parseInt(objs[0].toString()));
            serviceVO.setAccountId(Integer.parseInt(objs[1].toString()));
            serviceVO.setIdcardNo(objs[2].toString());
            serviceVO.setRealName(objs[3].toString());
            serviceVO.setOsUserName(objs[4].toString());
            serviceVO.setStatus(objs[5].toString());
//s.unixHost, s.cost.name,s.cost.descr
            serviceVO.setUnixHost(objs[6].toString());
            serviceVO.setCostName(objs[7].toString());
            serviceVO.setDescr(objs[8].toString());
            serviceVos.add(serviceVO);
        }
        return serviceVos;
    }

    @Override
    public Service findById(final int id) throws DAOException {

        return (Service) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Service service = (Service) session.get(Service.class, id);
                session.clear();
                return service;
            }
        });
    }


    @Override
    public int findTotalPage(String osUserName, String unixHost,
                             String idcardNo, String status, int page, int pageSize) {
        StringBuffer hql = new StringBuffer(
                "select count(*) from Service s where 1=1 ");
        List params = new ArrayList();
        if (status != null && !"-1".equals(status)
                && status.trim().length() > 0) {
            hql.append(" and s.status = ? ");
            params.add(status);
        }
        if (osUserName != null && osUserName.trim().length() > 0) {
            hql.append(" and s.osUsername = ? ");
            params.add(osUserName);
        }
        if (unixHost != null && unixHost.trim().length() > 0) {
            hql.append(" and s.unixHost = ? ");
            params.add(unixHost);
        }
        if (idcardNo != null && idcardNo.trim().length() > 0) {
            hql.append(" and s.account.idcardNo = ? ");
            params.add(idcardNo);
        }
        long totalRows = (Long) hibernateTemplate.find(hql.toString(),
                params.toArray()).get(0);
        if (totalRows % pageSize == 0) {
            return (int) totalRows / pageSize;
        } else {
            return (int) totalRows / pageSize + 1;
        }

    }

    @Override
    public void updateService(Service service) throws DAOException {
        // TODO Auto-generated method stub
        hibernateTemplate.update(service);
    }

}
