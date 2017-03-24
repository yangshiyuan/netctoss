package com.netctoss.dao.role.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.role.IRoleDAO;
import com.netctoss.entity.Role;

public class RoleDAOImpl implements IRoleDAO {

    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    @Override
    public Role findById(final int id) throws DAOException {
        return (Role) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Role role = (Role) session.get(Role.class, id);
                session.clear();
                return role;
            }
        });
    }

    @Override
    public List<Role> findByPage(final int page, final int pageSize) throws DAOException {
        return (List<Role>) hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                return session.createQuery("from Role")
                        .setFirstResult((page - 1) * pageSize)
                        .setMaxResults(pageSize).list();
            }
        });
    }

    @Override
    public int findTotalPage(int pageSize) {
        String hql = "select count(*) from Role";
        long totalRows = (Long) hibernateTemplate.find(hql).get(0);
        if (totalRows % pageSize == 0) {
            return (int) totalRows / pageSize;
        } else {
            return (int) totalRows / pageSize + 1;
        }
    }


    @Override
    public void saveRole(Role role) throws DAOException {
        // TODO Auto-generated method stub
        hibernateTemplate.save(role);
    }


    @Override
    public void updateRole(Role role) throws DAOException {
        // TODO Auto-generated method stub
        hibernateTemplate.update(role);
    }


    @Override
    public void deleteRole(int id) {
//        hibernateTemplate.delete(role);
//使用hibernateTemplate.delete(对象):
//    如果配置级联操作（all）：把当前表的数据
//    和与之关联的数据，一起删除

        hibernateTemplate.bulkUpdate("delete from Role r where r.id = ? ", new Object[]{id});
//    只使用hql语句，只把当前的表中的数据删除
    }


    @Override
    public void deleteByRoleId(int roleId) {
        // TODO Auto-generated method stub
        String hql = "delete from RolePrivilege rp where rp.id.roleId = ?";
        hibernateTemplate.bulkUpdate(hql, new Object[]{roleId});
    }


}
