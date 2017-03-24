package com.netctoss.dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Account;
import com.netctoss.util.DBUtil;

public class AccountDAOImpl implements IAccountDAO {

    public List<Account> findByCondition(
            String idcardNo, String realName, String loginName, String status, int page, int pageSize)
            throws DAOException {
        List<Account> list = new ArrayList<Account>();
        //拼SQL
        String sql = "select * from (" +
                "    select a.*,rownum r from account a where 1=1 ";
        List<Object> params = new ArrayList<Object>();
        if (idcardNo != null
                && idcardNo.length() > 0) {
            sql += "and idcard_no=? ";
            params.add(idcardNo);
        }
        if (realName != null
                && realName.length() > 0) {
            sql += "and real_name=? ";
            params.add(realName);
        }
        if (loginName != null
                && loginName.length() > 0) {
            sql += "and login_name=? ";
            params.add(loginName);
        }
        if (status != null
                && status.length() > 0
                && !status.equals("-1")) {
            sql += "and status=? ";
            params.add(status);
        }

        sql += ") where r<? and r>? ";
        //小于下一页的最小行
        params.add(page * pageSize + 1);
        //大于上一页的最大行
        params.add((page - 1) * pageSize);

        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            //给参数赋值
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = createAccount(rs);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询账务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return list;
    }

    private Account createAccount(ResultSet rs) throws SQLException {
        Account a = new Account();
        a.setId(rs.getInt("id"));
        /*
         * rs.getInt("recommender_id")返回的是int类型
         * 的值，如果数据库中该字段为null，则会自动
         * 转换为0。那么如果这个0发送到修改页面放于
         *    hidden中的话，提交时将会以0来保存，由于该
         *    列设置了外键约束，会检查account表中是否
         * 存在保存的值，那么0显然没有存在，就导致
         * 保存出错
         * */
        a.setRecommenderId(rs.getObject("recommender_id") == null ? null : rs.getInt("recommender_id"));
        a.setLoginName(rs.getString("login_name"));
        a.setLoginPasswd(rs.getString("login_passwd"));
        a.setStatus(rs.getString("status"));
        a.setCreateDate(rs.getDate("create_date"));
        a.setPauseDate(rs.getDate("pause_date"));
        a.setCloseDate(rs.getDate("close_date"));
        a.setRealName(rs.getString("real_name"));
        a.setIdcardNo(rs.getString("idcard_no"));
        a.setBirthdate(rs.getDate("birthdate"));
        a.setGender(rs.getString("gender"));
        a.setOccupation(rs.getString("occupation"));
        a.setTelephone(rs.getString("telephone"));
        a.setEmail(rs.getString("email"));
        a.setMailaddress(rs.getString("mailaddress"));
        a.setZipcode(rs.getString("zipcode"));
        a.setQq(rs.getString("qq"));
        a.setLastLoginTime(rs.getDate("last_login_time"));
        a.setLastLoginIp(rs.getString("last_login_ip"));
        return a;
    }

    public static void main(String[] args) throws Exception {
        IAccountDAO dao = new AccountDAOImpl();
        Account a = dao.findByService(2001);
        System.out.println(a.getId());
        System.out.println(a.getIdcardNo());
        System.out.println(a.getRecommenderId());
    }

    public int findTotalPage(
            String idcardNo, String realName, String loginName, String status, int pageSize) throws DAOException {
        //查询总行数
        List<Object> params = new ArrayList<Object>();
        String sql = "select count(*) from account where 1=1 ";
        if (idcardNo != null
                && idcardNo.length() > 0) {
            sql += "and idcard_no=? ";
            params.add(idcardNo);
        }
        if (realName != null
                && realName.length() > 0) {
            sql += "and real_name=? ";
            params.add(realName);
        }
        if (loginName != null
                && loginName.length() > 0) {
            sql += "and login_name=? ";
            params.add(loginName);
        }
        if (status != null
                && status.length() > 0
                && !status.equals("-1")) {
            sql += "and status=? ";
            params.add(status);
        }

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
                //计算总页面
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

    public void start(int id) throws DAOException {
        String sql = "update account set status='0'," +
                "pause_date=null where id=? ";
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
                    "开通账务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public Account findByIdcardNo(String idcardNo) throws DAOException {
        if (idcardNo == null
                || idcardNo.length() == 0) {
            return null;
        }

        String sql = "select * from account " +
                "where idcard_no=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setString(1, idcardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = createAccount(rs);
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "根据身份证查询账务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return null;
    }

    public Account findById(int id) throws DAOException {
        //查账务账号数据
        String sql = "select * from account where id=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = createAccount(rs);
                //查询推荐人身份证
                if (a.getRecommenderId() != null) {
                    String sql2 = "select idcard_no from account " +
                            "where id=?";
                    PreparedStatement ps2 =
                            con.prepareStatement(sql2);
                    ps2.setInt(1, a.getRecommenderId());
                    ResultSet rs2 = ps2.executeQuery();
                    if (rs2.next()) {
                        String idcardNo = rs2.getString(1);
                    }
                }
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "根据ID查询账务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return null;
    }

    public Account findByService(int serviceId)
            throws DAOException {
        String sql = "select * from account where id in (" +
                "select account_id from service where id=?" +
                ")";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, serviceId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = createAccount(rs);
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "根据业务账号查询账务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    public void delete(int id) throws DAOException {
        // TODO 同学们自行完成

    }

}
