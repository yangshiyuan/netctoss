package com.netctoss.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Admin;
import com.netctoss.util.DBUtil;
import com.netctoss.vo.AdminVO;

public class AdminDAOImpl implements IAdminDAO {

    public List<AdminVO> findByCondition(
            Integer roleId,
            Integer privilegeId,
            int page, int pageSize)
            throws DAOException {
        List<AdminVO> list = new ArrayList<AdminVO>();
        //查询管理员
        List<Object> params = new ArrayList<Object>();
        String sql = "select * from (" +
                "    select a.*,rownum r from admin_info a " +
                "    where id in ( " +
                "        select admin_id from admin_role ar " +
                "        inner join role_info ri on ri.id=ar.role_id " +
                "        inner join role_privilege rp on rp.role_id=ri.id " +
                "        where 1=1 ";
        if (roleId != null) {
            sql += " and ri.id=? ";
            params.add(roleId);
        }
        if (privilegeId != null) {
            sql += " and rp.privilege_id=? ";
            params.add(privilegeId);
        }
        sql += "    ) " +
                ") where r<? and r>?";
        params.add(page * pageSize + 1);
        params.add((page - 1) * pageSize);

        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdminVO vo = createAdminVO(rs);
                //查询管理员对应的角色
                String sql2 = "select name from role_info " +
                        "where id in (" +
                        "    select role_id from admin_role " +
                        "    where admin_id=? )";
                PreparedStatement ps2 =
                        con.prepareStatement(sql2);
                ps2.setInt(1, vo.getId());
                ResultSet rs2 = ps2.executeQuery();
                String names = "";
                while (rs2.next()) {
                    String name = rs2.getString(1);
                    names += "," + name;
                }
                if (names.length() > 0)
                    names = names.replaceFirst(",", "");

                vo.setRolesName(names);

                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询管理员失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return list;
    }

    public int findTotalPage(
            Integer roleId,
            Integer privilegeId, int pageSize)
            throws DAOException {
        List<Object> params = new ArrayList<Object>();
        String sql = "select count(*) from admin_info " +
                "where id in (" +
                "        select admin_id from admin_role ar " +
                "        inner join role_info ri on ri.id=ar.role_id " +
                "        inner join role_privilege rp on rp.role_id=ri.id " +
                "        where 1=1 ";
        if (roleId != null) {
            sql += " and ri.id=? ";
            params.add(roleId);
        }
        if (privilegeId != null) {
            sql += " and rp.privilege_id=? ";
            params.add(privilegeId);
        }
        sql += ")";

        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int rows = rs.getInt(1);
                if (rows % pageSize == 0) {
                    return rows / pageSize;
                } else {
                    return rows / pageSize + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询总页数失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return 0;
    }

    private AdminVO createAdminVO(ResultSet rs)
            throws SQLException {
        AdminVO a = new AdminVO();
        a.setId(rs.getInt("id"));
        a.setAdminCode(rs.getString("admin_code"));
        a.setPassword(rs.getString("password"));
        a.setName(rs.getString("name"));
        a.setTelephone(rs.getString("telephone"));
        a.setEmail(rs.getString("email"));
        a.setEnrollDate(rs.getDate("enrolldate"));
        return a;
    }

    public static void main(String[] args)
            throws Exception {
        IAdminDAO dao = new AdminDAOImpl();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(2000);
        ids.add(3000);
        dao.resetPassword(ids);
    }

    public void resetPassword(List<Integer> ids)
            throws DAOException {
        if (ids == null || ids.size() == 0) {
            return;
        }

        String sql = "update admin_info " +
                "set password='123456' where id in (";
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            if (i == 0)
                sql += id;
            else
                sql += "," + id;
        }
        sql += ")";

        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException(
                    "重置密码失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

}
