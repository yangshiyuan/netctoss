package com.netctoss.dao.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Role;
import com.netctoss.util.DBUtil;
import com.netctoss.util.PrivilegeReader;
import com.netctoss.vo.RoleVO;

public class RoleDAOImpl implements IRoleDAO {

    public List<Role> findByPage(
            int page, int pageSize)
            throws DAOException {
        //先查询角色
        String sql = "select * from (" +
                "    select i.*,rownum r from role_info i " +
                ") where r<? and r>? ";
        Connection con = DBUtil.getConnection();
        List<Role> list = new ArrayList<Role>();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, page * pageSize + 1);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RoleVO vo = new RoleVO();
                vo.setId(rs.getInt("id"));
                vo.setName(rs.getString("name"));
                //    再查询角色对应的权限(即一组模块)
                String sql2 = "select privilege_id " +
                        "from role_privilege where role_id=?";
                PreparedStatement ps2 =
                        con.prepareStatement(sql2);
                ps2.setInt(1, vo.getId());
                ResultSet rs2 = ps2.executeQuery();
                String names = "";
                while (rs2.next()) {
                    int pid = rs2.getInt(1);
                    String pname = PrivilegeReader
                            .getPrivilegeNameById(pid+"");
                    names += "," + pname;
                }
                if (names.length() > 0)
                    names = names.replaceFirst(",", "");

                vo.setModulesName(names);

                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询角色失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return list;
    }

    @Override
    public int findTotalPage(int pageSize) {
        return 0;
    }

    @Override
    public void saveRole(Role role) throws DAOException {

    }

    public static void main(String[] args)
            throws Exception {
        IRoleDAO dao = new RoleDAOImpl();
        Role role = dao.findById(101);
        role.setName("天蓬元帅");
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(4);
        list.add(6);
        list.add(7);
    }

    public void insert(Role role)
            throws DAOException {
        if (role == null)
            return;
        //新增角色表
        String sql = "insert into role_info " +
                "values(role_seq.nextval,?)";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            //指定要返回的列名
            String[] columns = {"id"};
            PreparedStatement ps =
                    con.prepareStatement(sql, columns);
            ps.setString(1, role.getName());
            ps.executeUpdate();

            //获取指定返回列的值
            ResultSet rs = ps.getGeneratedKeys();
            Integer roleId = null;
            if (rs.next()) {
                roleId = rs.getInt(1);
            }

            //插入角色权限中间表
            List<Integer> pids = null;
            if (pids != null
                    && pids.size() > 0) {
                String sql2 = "insert into role_privilege " +
                        "values(?,?)";
                PreparedStatement ps2 =
                        con.prepareStatement(sql2);
                for (Integer pid : pids) {
                    ps2.setInt(1, roleId);
                    ps2.setInt(2, pid);
                    ps2.addBatch();
                }
                ps2.executeBatch();
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException(
                    "新增角色失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public Role findById(int id)
            throws DAOException {
        //先查询角色
        String sql = "select * from role_info " +
                "where id=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));

                //根据角色查询中间表中的权限ID
                String sql2 = "select privilege_id " +
                        "from role_privilege where role_id=?";
                PreparedStatement ps2 =
                        con.prepareStatement(sql2);
                ps2.setInt(1, id);
                ResultSet rs2 = ps2.executeQuery();
                List<Integer> pids = new ArrayList<Integer>();
                while (rs2.next()) {
                    pids.add(rs2.getInt(1));
                }

//                role.setPrivilegeIds(pids);
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询角色失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return null;
    }

    @Override
    public void updateRole(Role role) throws DAOException {

    }

    @Override
    public void deleteRole(int id) {

    }

    @Override
    public void deleteByRoleId(int roleId) {

    }

    public void update(Role role)
            throws DAOException {
        if (role == null)
            return;

        // 1.修改角色表
        String sql1 = "update role_info " +
                "set name=? where id=?";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps1 =
                    con.prepareStatement(sql1);
            ps1.setString(1, role.getName());
            ps1.setInt(2, role.getId());
            ps1.executeUpdate();

            //2.删除中间表中当前角色对应的记录
            String sql2 = "delete from role_privilege " +
                    "where role_id=?";
            PreparedStatement ps2 =
                    con.prepareStatement(sql2);
            ps2.setInt(1, role.getId());
            ps2.executeUpdate();

            //3.重新向中间表插入当前角色对应的权限数据
            List<Integer> pids = null;
            if (pids != null && pids.size() > 0) {
                String sql3 = "insert into role_privilege " +
                        "values(?,?)";
                PreparedStatement ps3 =
                        con.prepareStatement(sql3);
                for (Integer pid : pids) {
                    ps3.setInt(1, role.getId());
                    ps3.setInt(2, pid);
                    ps3.addBatch();
                }
                ps3.executeBatch();
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DAOException(
                    "更新角色失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

    }

}
