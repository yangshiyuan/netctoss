package com.netctoss.dao.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Service;
import com.netctoss.util.DBUtil;
import com.netctoss.vo.ServiceVO;

public class ServiceDAOImpl implements IServiceDAO {

    public List<ServiceVO> findByCondition(
            String osUserName,
            String unixHost,
            String idcardNo,
            String status,
            int page, int pageSize)
            throws DAOException {
        //拼查询SQL
        List<Object> params = new ArrayList<Object>();
        String sql = "select * from (" +
                "    select s.*,a.idcard_no,a.real_name," +
                "    c.name,c.descr,rownum r " +
                "    from service s " +
                "    inner join account a on s.account_id=a.id " +
                "    inner join cost c on s.cost_id=c.id " +
                "    where 1=1 ";
        if (osUserName != null
                && osUserName.length() > 0) {
            sql += " and s.os_username=? ";
            params.add(osUserName);
        }
        if (unixHost != null
                && unixHost.length() > 0) {
            sql += " and s.unix_host=? ";
            params.add(unixHost);
        }
        if (idcardNo != null
                && idcardNo.length() > 0) {
            sql += " and a.idcardNo=? ";
            params.add(idcardNo);
        }
        if (status != null
                && status.length() > 0
                && !status.equals("-1")) {
            sql += " and s.status=? ";
            params.add(status);
        }
        sql += ") where r<? and r>? ";
        params.add(page * pageSize + 1);
        params.add((page - 1) * pageSize);

        Connection con = DBUtil.getConnection();
        List<ServiceVO> vos =
                new ArrayList<ServiceVO>();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ServiceVO vo = createServiceVO(rs);
                vos.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询业务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return vos;
    }

    @Override
    public int findTotalPage(String osUserName, String unixHost, String idcardNo, String status, int page, int pageSize) {
        return 0;
    }

    private ServiceVO createServiceVO(ResultSet rs) throws SQLException {
        ServiceVO vo = new ServiceVO();
        vo.setId(rs.getInt("id"));
        vo.setAccountId(rs.getInt("account_id"));
        vo.setUnixHost(rs.getString("unix_host"));
        vo.setOsUserName(rs.getString("os_username"));
//        vo.setLoginPassword(rs.getString("login_passwd"));
        vo.setStatus(rs.getString("status"));
//        vo.setCreateDate(rs.getDate("create_date"));
//        vo.setPauseDate(rs.getDate("pause_date"));
//        vo.setCloseDate(rs.getDate("close_date"));
//        vo.setCostId(rs.getInt("cost_id"));
        vo.setIdcardNo(rs.getString("idcard_no"));
        vo.setRealName(rs.getString("real_name"));
        vo.setCostName(rs.getString("name"));
//        vo.setCostDescr(rs.getString("descr"));
        return vo;
    }

    private Service createService(ResultSet rs)
            throws SQLException {
        Service vo = new Service();
        vo.setId(rs.getInt("id"));
//        vo.setAccountId(rs.getInt("account_id"));
        vo.setUnixHost(rs.getString("unix_host"));
//        vo.setOsUserName(rs.getString("os_username"));
        vo.setLoginPasswd(rs.getString("login_passwd"));
        vo.setStatus(rs.getString("status"));
        vo.setCreateDate(rs.getDate("create_date"));
        vo.setPauseDate(rs.getDate("pause_date"));
        vo.setCloseDate(rs.getDate("close_date"));
//        vo.setCostId(rs.getInt("cost_id"));
        return vo;
    }

    public static void main(String[] args)
            throws Exception {
        IServiceDAO dao = new ServiceDAOImpl();
        Service s = dao.findById(2001);
//        System.out.println(s.getOsUserName());
        System.out.println(s.getUnixHost());
    }

    public void start(int id) throws DAOException {
        // TODO 同学们来完成

    }

    public void delete(int id) throws DAOException {
        // TODO 参考账务账号删除来实现

    }

    public void deleteByAccount(int accountId)
            throws DAOException {
        String sql = "update service set status='2'," +
                "close_date=sysdate where account_id=?";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, accountId);
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
                    "删除业务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public Service findById(int id)
            throws DAOException {
        String sql = "select * from service " +
                "where id=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Service s = createService(rs);
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "根据ID查询业务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return null;
    }

    @Override
    public void updateService(Service service) throws DAOException {

    }

    public void update(Service service)
            throws DAOException {
        if (service == null)
            return;
        String sql = "insert into SERVICE_UPDATE_BAK " +
                "values(service_bak_seq.nextval,?,?)";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, service.getId());
//            ps.setInt(2, service.getCostId());
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
                    "更新业务账号失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
    }

}
