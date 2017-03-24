package com.netctoss.dao.cost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Cost;
import com.netctoss.util.DBUtil;

public class CostDAOImpl implements ICostDAO {

    public List<Cost> findAll()
            throws DAOException {
        List<Cost> list = new ArrayList<Cost>();
        String sql = "select * from cost";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cost c = createCost(rs);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询全部的资费数据发生异常！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    private Cost createCost(ResultSet rs) throws SQLException {
        Cost c = new Cost();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setBaseDuration(rs.getInt("base_duration"));
        c.setBaseCost(rs.getDouble("base_cost"));
        c.setUnitCost(rs.getDouble("unit_cost"));
        c.setStatus(rs.getString("status"));
        c.setDescr(rs.getString("descr"));
        c.setCreateTime(rs.getDate("creatime"));
        c.setStartTime(rs.getDate("startime"));
        c.setCostType(rs.getString("cost_type"));
        return c;
    }

    public static void main(String[] args)
            throws Exception {
        ICostDAO dao = new CostDAOImpl();
        Cost c = dao.findById(104);
        c.setName("yyy");
        c.setBaseDuration(77);
        c.setBaseCost(7.0);
        c.setUnitCost(0.7);
        c.setDescr("7元套餐");
        c.setCostType("3");
        //	dao.update(c);
    }

    public List<Cost> findByPage(
            int page, int pageSize)
            throws DAOException {
        List<Cost> list = new ArrayList<Cost>();
        String sql = "select * from (" +
                " select c.*,rownum r from cost c" +
                ") where r<? and r>? ";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            //小于下一页最小行
            int nextMin = page * pageSize + 1;
            ps.setInt(1, nextMin);
            //大于上一页最大行
            int lastMax = (page - 1) * pageSize;
            ps.setInt(2, lastMax);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cost c = createCost(rs);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "分页查询资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return list;
    }

    public int findTotalPage(int pageSize)
            throws DAOException {
        String sql = "select count(*) from cost";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //查询总行数
                int rows = rs.getInt(1);
                //根据总行数，计算总页数
                if (rows % pageSize == 0) {
                    return rows / pageSize;
                } else {
                    return rows / pageSize + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询总行数失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return 0;
    }

    public void delete(int id) throws DAOException {
        String sql = "delete from cost " +
                "where id=?";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, id);
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
                    "删除资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public void add(Cost cost)
            throws DAOException {
        if (cost == null)
            return;
        String sql = "insert into cost " +
                "values(cost_seq.nextval,?,?,?,?,'1',?,sysdate,null,?)";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setObject(1, cost.getName());
            ps.setObject(2, cost.getBaseDuration());
            ps.setObject(3, cost.getBaseCost());
            ps.setObject(4, cost.getUnitCost());
            ps.setObject(5, cost.getDescr());
            ps.setObject(6, cost.getCostType());
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
                    "新增资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public Cost findByName(String name)
            throws DAOException {
        if (name == null) {
            return null;
        }

        String sql = "select * from cost where name=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cost c = createCost(rs);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "按名称查询资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return null;
    }

    public Cost findById(int id)
            throws DAOException {
        String sql = "select * from cost where id=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cost cost = createCost(rs);
                return cost;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "根据ID查询资费数据失败！", e);
        }
        return null;
    }

    public void update(Cost cost)
            throws DAOException {
        if (cost == null)
            return;
        String sql = "update cost set name=?," +
                "base_duration=?,base_cost=?," +
                "unit_cost=?,descr=?,cost_type=? " +
                "where id=?";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setObject(1, cost.getName());
            ps.setObject(2, cost.getBaseDuration());
            ps.setObject(3, cost.getBaseCost());
            ps.setObject(4, cost.getUnitCost());
            ps.setObject(5, cost.getDescr());
            ps.setObject(6, cost.getCostType());
            ps.setObject(7, cost.getId());
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
                    "修改资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    @Override
    public void saveCost(Cost cost) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateCost(Cost cost) throws DAOException {
        // TODO Auto-generated method stub

    }

}
